package com.graniteexpo.demo.dtos;

import com.graniteexpo.demo.enums.Role;

import java.util.UUID;

public class AuthResponseDTO {
    private String token;
    private UUID userId;
    private String email;
    private Role role;

    public AuthResponseDTO() {}

    public AuthResponseDTO(String token, UUID userId, String email,Role role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }



    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
