package com.example.jiraffeserver.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpRequest {
    private String name;
    private String surname;

    private String email;
    private String imageUrl;
    private String password;
}
