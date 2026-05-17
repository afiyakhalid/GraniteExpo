package com.graniteexpo.demo.services.impl;

import com.graniteexpo.demo.dtos.AuthResponseDTO;
import com.graniteexpo.demo.dtos.LoginRequestDTO;
import com.graniteexpo.demo.dtos.RegisterRequestDTO;
import com.graniteexpo.demo.entities.User;
import com.graniteexpo.demo.repositories.UserRepo;
import com.graniteexpo.demo.security.JwtUtil;
import com.graniteexpo.demo.services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponseDTO register(RegisterRequestDTO dto) {
        userRepo.findByEmail(dto.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Email already registered");
        });

        User u = new User();
//        u.setId(UUID.randomUUID());
        u.setEmail(dto.getEmail());
        u.setRole(dto.getRole());
        u.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        u.setCreatedAt(OffsetDateTime.now());

        userRepo.save(u);

        String token = jwtUtil.createToken(u.getId(), u.getEmail(), u.getRole().name());
        return new AuthResponseDTO(token, u.getId(), u.getEmail(), u.getRole());
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {
        User u = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(dto.getPassword(), u.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.createToken(u.getId(), u.getEmail(), u.getRole().name());
        return new AuthResponseDTO(token, u.getId(), u.getEmail(), u.getRole());
    }
}
