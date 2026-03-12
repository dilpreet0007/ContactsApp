package com.authentication;

import com.main.Main;
import com.userregistration.User;

public class BasicAuthStrategy implements Authentication {
    @Override
    public User authenticate(String email, String password) {
    	String pass = User.hashPassword(password);
    	for(User user : Main.userList) {
    		if(user.getEmail().equals(email) && user.getPassword().equals(pass)) {
    			return user;
    		}
    	}
    	return null;
    }
}