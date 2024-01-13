package com.example.jiraffeserver.auth.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ValidateRequest {
    private String token;
}
