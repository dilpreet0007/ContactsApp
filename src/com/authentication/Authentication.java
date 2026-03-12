package com.authentication;

import com.userregistration.User;
import com.userregistration.UserType;

public interface Authentication {
	User authenticate(String username, String password);
}