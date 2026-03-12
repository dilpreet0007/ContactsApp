package com.userregistration;

import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;


public class UserBuilder {
    String name;
    String email;
    String password;
    User userType;

    public UserBuilder(String name, User userType) {
        this.name = name;
        this.userType = userType;
    }

    public UserBuilder email(String email) {
    	if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) { 
    		throw new IllegalArgumentException("Invalid email format");
    	} 
    	this.email = email; 
    	return this;
    }

    public UserBuilder password(String password) {
    	this.password = hashPassword(password);
    	return this;
    }

    public CreateUser build() {
        return new CreateUser(this);
    }
    
    
    private String hashPassword(String password) { 
    	try { 
    		MessageDigest md = MessageDigest.getInstance("SHA-256"); 
    		byte[] hash = md.digest(password.getBytes()); 
    		StringBuilder hexString = new StringBuilder(); 
    		for (byte b : hash) { 
    			hexString.append(String.format("%02x", b)); 
    		} 
    		return hexString.toString(); 
    		} 
    	catch (NoSuchAlgorithmException e) { 
    		throw new RuntimeException("Error hashing password", e); 
    		} 
    	}
}

