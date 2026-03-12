package com.validation;

import com.exception.InvalidInputException;

public class PhoneNoValidator {
	public static boolean validatePhoneNo(String phoneNo) throws InvalidInputException{
		String regex = "^[6-9]\\d{9}$";
		if(phoneNo.matches(regex)) return true;
		else throw new InvalidInputException("Phone No is Invalid");
	}
}