package com.grocery.service;

import com.grocery.entity.User;

public interface AuthService {
	
	User register(User user);
	
	User login(String email, String password);
	
	void resetPassword(String email, String newPassword);

}
