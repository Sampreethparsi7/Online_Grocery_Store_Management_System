package com.grocery.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.grocery.entity.User;

import com.grocery.security.JwtUtil;

import com.grocery.service.AuthService;

@RestController

@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;

    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {

        this.authService = authService;

        this.jwtUtil = jwtUtil;

    }

    //  REGISTER (NO JWT REQUIRED)

    @PostMapping("/register")

    public ResponseEntity<User> register(@RequestBody User user) {

        User savedUser = authService.register(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

    }

    //  LOGIN → RETURNS JWT TOKEN

    @PostMapping("/login")

    public ResponseEntity<Map<String, String>> login(

            @RequestParam String email,

            @RequestParam String password) {

        User user = authService.login(email, password);

        String token = jwtUtil.generateToken(

                user.getEmail(),

                user.getRole()

        );

        return ResponseEntity.ok(

                Map.of(

                        "token", token,

                        "role", user.getRole(),

                        "email", user.getEmail()

                )

        );

    }

    //  FORGOT / RESET PASSWORD

    @PutMapping("/forgot-password")

    public ResponseEntity<String> resetPassword(

            @RequestParam String email,

            @RequestParam String newPassword) {

        authService.resetPassword(email, newPassword);

        return ResponseEntity.ok("Password updated successfully");

    }

    // LOGOUT (JWT is stateless → frontend deletes token)

    @PostMapping("/logout")

    public ResponseEntity<String> logout() {

        return ResponseEntity.ok("Logged out successfully");

    }

}