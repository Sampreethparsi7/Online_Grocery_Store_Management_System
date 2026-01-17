package com.grocery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.entity.User;
import com.grocery.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	 private final AuthService authService;
	    public AuthController(AuthService authService) {
	        this.authService = authService;
	    }

	    @PostMapping("/register")
	    public ResponseEntity<User> register(@RequestBody User user) {
	        User savedUser = authService.register(user);
	        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	    }

	    @PostMapping("/login")
	    public ResponseEntity<User> login(@RequestParam String email,
	                                      @RequestParam String password) {
	        User user = authService.login(email, password);
	        return ResponseEntity.ok(user);
	    }
	    @PutMapping("/forgot-password")
	    public ResponseEntity<String> resetPassword(@RequestParam String email,
	                                                @RequestParam String newPassword) {
	        authService.resetPassword(email, newPassword);
	        return ResponseEntity.ok("Password updated successfully");
	    }
	

}
