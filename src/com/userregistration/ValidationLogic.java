package com.userregistration;

public class ValidationLogic {
	
	public static boolean isValidPassword(String password) {
		return password != null && 
				password.matches("^(?=.*[0-9])(?=.*[A-Z]).{8,}$");
	}
	public static boolean isValidEmail(String email) {
		if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
    		return false;
    	}
		return true;
	}
}
