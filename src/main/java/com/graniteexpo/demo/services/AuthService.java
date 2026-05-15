package com.graniteexpo.demo.services;

import com.graniteexpo.demo.dtos.AuthResponseDTO;
import com.graniteexpo.demo.dtos.LoginRequestDTO;
import com.graniteexpo.demo.dtos.RegisterRequestDTO;

public interface AuthService {
    AuthResponseDTO register(RegisterRequestDTO dto);
    AuthResponseDTO login(LoginRequestDTO dto);
}