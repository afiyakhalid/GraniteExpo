package com.graniteexpo.demo.configs;

import com.graniteexpo.demo.security.JwtUtil;
import com.graniteexpo.demo.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // Read the secret from application.properties:
    // app.jwt.secret=...
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Bean
    public JwtUtil jwtUtil() {
        // Create JwtUtil once as a Spring bean.
        // Token TTL = 7 days.
        return new JwtUtil(jwtSecret, 60 * 60 * 24 * 7);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // PasswordEncoder is used by AuthService to hash passwords safely.
        // BCrypt automatically salts + slows down hashing to resist brute-force attacks.
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtUtil jwtUtil) throws Exception {
        return http
                // JWT is stateless; we typically disable CSRF for pure JSON APIs.
                .csrf(csrf -> csrf.disable())

                // Enable CORS using our CorsConfig bean.
                .cors(Customizer.withDefaults())

                // Authorization rules:
                // - allow auth endpoints without a token
                // - everything else requires a valid token
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .anyRequest().authenticated()
                )

                // Add our JWT filter BEFORE the built-in UsernamePasswordAuthenticationFilter.
                // This ensures Spring Security sees the user as authenticated before it checks rules.
                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)

                .build();
    }
}
