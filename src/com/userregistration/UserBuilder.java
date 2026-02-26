package com.userregistration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserBuilder {
	public String name;
	public String userName;
	public String email;
	public String password;
	public String type = "free";
	
	public UserBuilder setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public UserBuilder setName(String name) {
		this.name = name;
		return this;
	}
	public UserBuilder setEmail(String email) {
		if(ValidationLogic.isValidEmail(email)) {
			this.email = email;
			return this;
		}
		else {
			throw new IllegalArgumentException("Invalid email format");
		}
	}
	public UserBuilder setPassword(String password) {
		if(ValidationLogic.isValidPassword(password)) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] hash = md.digest(password.getBytes());
				StringBuilder hexString = new StringBuilder();
				for (byte b : hash) {
					hexString.append(String.format("%02x", b));
				}
				this.password = hexString.toString();
				return this;
			}
			catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("Error hashing password", e);
			}
		}
		else {
			throw new IllegalArgumentException("Use Strong Password");
		}
	}
 
	
	
	public UserBuilder setType(String type) {
		this.type = type;
		return this;
	}
	
	public UserRegistration create() {
		return UserFactory.createUser(type, this);
	}
	
	
}
