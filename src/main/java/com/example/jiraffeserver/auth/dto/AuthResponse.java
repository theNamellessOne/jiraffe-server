package com.example.jiraffeserver.auth.dto;

import com.example.jiraffeserver.user.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private UserResponseDto user;
}
