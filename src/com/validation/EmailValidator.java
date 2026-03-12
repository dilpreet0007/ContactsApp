package com.validation;

public class EmailValidator {
	public static boolean validateEmail(String email) {
		if(email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			return true;
		}
		return false;
	}
}