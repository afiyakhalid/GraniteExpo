package com.graniteexpo.demo.controllers;

import com.graniteexpo.demo.dtos.AuthResponseDTO;
import com.graniteexpo.demo.dtos.LoginRequestDTO;
import com.graniteexpo.demo.dtos.RegisterRequestDTO;
import com.graniteexpo.demo.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    // Controller is thin: it only accepts HTTP requests and delegates logic to AuthService.
    private final AuthService authService;

    public AuthController(AuthService authService) {
        // Spring injects the AuthService implementation here.
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto) {
        // @RequestBody => JSON -> RegisterRequestDTO
        // @Valid => runs validation annotations (like @NotBlank on email/password)
        // Service does: check email, hash password, store user, return JWT
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        // Service does: find user, verify password, return JWT
        return ResponseEntity.ok(authService.login(dto));
    }
}
