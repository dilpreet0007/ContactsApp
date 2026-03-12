package com.validation;

import com.exception.InvalidInputException;

public class NameValidator {
	public static boolean validateName(String name) throws InvalidInputException{
		if(name.length() >20 || name.length()<2) {
			throw new InvalidInputException("Name is Invalid !.");
		}
		else return true;
	}
}