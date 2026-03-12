package com.validation;

public class NameValidator {
	public static boolean validateName(String name) {
		if(name.length() >20 || name.length()<2) {
			return false;
		}
		return true;
	}
}