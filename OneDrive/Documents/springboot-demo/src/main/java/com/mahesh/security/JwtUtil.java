package com.mahesh.security;

public class JwtUtil {

    public String generateToken(String username) {
        return "TOKEN_" + username;
    }

    public String extractUsername(String token) {
        return token.replace("TOKEN_", "");
    }
}
