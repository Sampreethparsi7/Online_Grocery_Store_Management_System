package com.grocery.service;

import org.springframework.stereotype.Service;

import com.grocery.entity.User;
import com.grocery.exception.DuplicateResourceException;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService  {
	 private final UserRepository userRepository;
	    public AuthServiceImpl(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @Override
	    public User register(User user) {
	        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
	            throw new DuplicateResourceException("Email already exists");
	        }
	        user.setStatus("ACTIVE");
	        return userRepository.save(user);
	    }

	    @Override
	    public User login(String email, String password) {
	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
	        if (!user.getPassword().equals(password)) {
	            throw new RuntimeException("Invalid credentials");
	        }
	        return user;
	    }

	    @Override
	    public void resetPassword(String email, String newPassword) {
	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
	        user.setPassword(newPassword);
	        userRepository.save(user);
	    }
	
	

}
