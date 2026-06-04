package com.mahesh.service;

import com.mahesh.dto.LoginRequest;
import com.mahesh.dto.LoginResponse;
import com.mahesh.dto.RegisterRequest;
import com.mahesh.model.User;
import com.mahesh.repository.UserRepository;
import com.mahesh.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public User register(RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        user.setRole(request.getRole());

        return repository.save(user);
    }

    public LoginResponse login(LoginRequest request) {

        User user = repository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        boolean passwordMatch =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!passwordMatch) {
            throw new RuntimeException("Invalid Password");
        }

        String token =
                jwtService.generateToken(
                        user.getUsername());

        return new LoginResponse(token);
    }
}
