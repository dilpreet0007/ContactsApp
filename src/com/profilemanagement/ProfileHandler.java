package com.profilemanagement;
import java.util.*;

import com.exception.InvalidInputException;
import com.userregistration.User;
import com.validation.EmailValidator;
import com.validation.NameValidator;
import com.validation.PhoneNoValidator;

public class ProfileHandler {
	public static void updateProfile(User user,Scanner sc) throws InvalidInputException{
		Command command;
		ProfileManager manageProfile = new ProfileManager();
		System.out.println("\n What do you want to Update ?");
    	System.out.println(" 1. Name \n 2. Email \n 3. PhoneNo \n : ");
    	int ch02 = sc.nextInt();
    	sc.nextLine();
    	
    	switch(ch02) {
    	case 1:{
    		System.out.print("Enter new name : ");
    		String name = sc.nextLine();
    		command = new UpdateNameCommand(user,name);
//    		if(!NameValidator.validateName(name)) throw new InvalidInputException("Name is Invalid(It should have more than 2 characters).");
//    		user.updateName(name);
    		manageProfile.executeCommand(command);
    		break;
    	}
    	case 2:{
    		System.out.print("Enter new Email : ");
    		String email = sc.nextLine();
    		command = new UpdateEmailCommand(user,email) ;
    		manageProfile.executeCommand(command);
//    		if(!EmailValidator.validateEmail(email)) throw new InvalidInputException("Email is Invalid.");
//    		user.updateEmail(email);
    		break;
    	}
    	case 3:{
    		System.out.print("Enter new Phone No : ");
    		String phoneNo = sc.nextLine();
    		command = new UpdatePhoneNoCommand(user,phoneNo) ;
    		manageProfile.executeCommand(command);
//    		if(!PhoneNoValidator.validatePhoneNo(phoneNo)) throw new InvalidInputException("Phone Number is Invalid.");
//    		user.updatePhoneNo(phoneNo);
    		break;
    	}
    	}
	}

}