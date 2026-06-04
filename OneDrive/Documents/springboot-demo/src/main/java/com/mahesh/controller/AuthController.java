package com.mahesh.controller;

import com.mahesh.dto.RegisterRequest;
import com.mahesh.model.User;
import com.mahesh.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mahesh.dto.LoginRequest;
import com.mahesh.dto.LoginResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

@PostMapping("/login")
public LoginResponse login(
        @RequestBody LoginRequest request) {

    return authService.login(request);
}

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }
}
