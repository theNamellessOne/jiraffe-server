package com.example.jiraffeserver.auth.service;

import com.example.jiraffeserver.auth.dto.AuthResponse;
import com.example.jiraffeserver.auth.dto.SignInRequest;
import com.example.jiraffeserver.auth.dto.SignUpRequest;
import com.example.jiraffeserver.auth.dto.ValidateRequest;
import com.example.jiraffeserver.jwt.service.JwtService;
import com.example.jiraffeserver.user.dto.UserRequestDto;
import com.example.jiraffeserver.user.mapper.UserResponseMapper;
import com.example.jiraffeserver.user.model.User;
import com.example.jiraffeserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserService userService;
    private final UserResponseMapper userResponseMapper;
    private final AuthenticationManager authenticationManager;

    public AuthResponse signUp(SignUpRequest request) {
        var userRecorded = userService.save(UserRequestDto.builder()
                .email(request.getEmail())
                .name(request.getName())
                .surname(request.getSurname())
                .password(request.getPassword())
                .imageUrl(request.getImageUrl())
                .build());

        if (userRecorded == null) {
            return new AuthResponse("Persistence failed", null);
        }

        return new AuthResponse(
                jwtService.generateJwt(userResponseMapper.toEntity(userRecorded)),
                userRecorded
        );
    }

    public AuthResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User userExtracted = userService.findByEmail(request.getEmail()).orElseThrow();
        return new AuthResponse(
                jwtService.generateJwt(userExtracted),
                userResponseMapper.toDto(userExtracted)
        );
    }

    public AuthResponse validate(ValidateRequest request) {
        var username = jwtService.extractUserName(request.getToken());
        User userExtracted = userService.findByEmail(username).orElseThrow();
        return new AuthResponse(
                jwtService.generateJwt(userExtracted),
                userResponseMapper.toDto(userExtracted)
        );
    }
}
