package com.example.jiraffeserver.auth.controller;

import com.example.jiraffeserver.auth.dto.AuthResponse;
import com.example.jiraffeserver.auth.dto.SignInRequest;
import com.example.jiraffeserver.auth.dto.SignUpRequest;
import com.example.jiraffeserver.auth.dto.ValidateRequest;
import com.example.jiraffeserver.auth.service.AuthService;
import com.example.jiraffeserver.common.util.ServerResponse;
import com.example.jiraffeserver.common.util.ServerResponseRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ServerResponse<AuthResponse> signUp(@RequestBody SignUpRequest request) {
        return ServerResponseRunner.safeRun(authService::signUp, request, "Account created!");
    }

    @PostMapping("/sign-in")
    public ServerResponse<AuthResponse> signIn(@RequestBody SignInRequest request) {
        return ServerResponseRunner.safeRun(authService::signIn, request, "Logging you in..");
    }

    @PostMapping("/validate")
    public ServerResponse<AuthResponse> validate(@RequestBody ValidateRequest request) {
        return ServerResponseRunner.safeRun(authService::validate, request);
    }
}