package com.validation;

import com.exception.InvalidInputException;

public class EmailValidator {
	public static boolean validateEmail(String email) throws InvalidInputException{
		if(email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			return true;
		}
		else throw new InvalidInputException("Email is Invalid");
	}
}