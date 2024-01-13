package com.example.jiraffeserver.user.controller;

import com.example.jiraffeserver.common.util.ServerResponse;
import com.example.jiraffeserver.common.util.ServerResponseRunner;
import com.example.jiraffeserver.user.model.User;
import com.example.jiraffeserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ServerResponse<Optional<User>> findUserByEmail(@RequestParam String email) {
        return ServerResponseRunner.safeRun(userService::findByEmail, email);
    }
}
