package com.validation;

public class PhoneNoValidator {
	public static boolean validatePhoneNo(String phoneNo) {
		String regex = "^[6-9]\\d{9}$";
		if(phoneNo.matches(regex)) return true;
		else return false;
	}
}