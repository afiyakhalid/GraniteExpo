package com.graniteexpo.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthFilter extends OncePerRequestFilter {

    // We use JwtUtil to parse/validate tokens.
    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // JWT is typically sent in:
        // Authorization: Bearer <token>
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring("Bearer ".length()).trim();

            try {
                // Parse token and validate it (signature + expiry).
                Jws<Claims> parsed = jwtUtil.parse(token);
                Claims claims = parsed.getBody();

                // Read claims that we put into the token at login/register time.
                String email = claims.get("email", String.class);
                String role = claims.get("role", String.class);

                // Spring Security uses "authorities" to represent permissions/roles.
                // Convention: roles are stored as ROLE_<role>
                // Example: ROLE_admin, ROLE_buyer, ROLE_staff
                var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

                // Build an authenticated "principal" object:
                // - principal: we store email (you could store userId too)
                // - credentials: null because we already authenticated via token
                // - authorities: roles
                var auth = new UsernamePasswordAuthenticationToken(email, null, authorities);

                // Put this into Spring Security context.
                // After this, Spring considers the request authenticated.
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                // If token is invalid or expired, clear auth.
                // Security will treat the request as unauthenticated and return 401.
                SecurityContextHolder.clearContext();
            }
        }

        // Always continue the filter chain; authorization is handled later by SecurityConfig rules.
        filterChain.doFilter(request, response);
    }
}