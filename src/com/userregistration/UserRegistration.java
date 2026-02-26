package com.userregistration;

public class UserRegistration {
	private String name;
	private String userName;
	private String email;
	private String password;
	
	public UserRegistration(UserBuilder user) {
		this.name = user.name;
		this.userName = user.userName;
		this.email = user.email;
		this.password = user.password;
	}

	public String getName() {
		return name;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	
	
}
