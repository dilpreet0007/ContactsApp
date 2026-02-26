package com.userregistration;

public class UserFactory {
	

	public static UserRegistration createUser(String type, UserBuilder user) {
		if(type.equalsIgnoreCase("premium")) {
			return new UserRegistrationPremium(user);
		}
		else if(type.equalsIgnoreCase("free")) {
			return new UserRegistrationFree(user);
		}
		return null;
	}

	
}
