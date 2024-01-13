package com.example.jiraffeserver.auth.util;

import com.example.jiraffeserver.user.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContext {
    public static User getCurrentUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
