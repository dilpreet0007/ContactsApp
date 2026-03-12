package com.profilemanagement;

import com.exception.InvalidInputException;
import com.userregistration.User;

public class UpdateEmailCommand implements Command{
	 private User userProfile;
	    private String newEmail;
	    private String oldEmail;

	    public UpdateEmailCommand(User userProfile, String newEmail) {
	        this.userProfile = userProfile;
	        this.newEmail = newEmail;
	        this.oldEmail = userProfile.getEmail();
	    }

	    @Override
	    public void execute() {
	    	try {
	    		userProfile.updateEmail(newEmail);
	    	}catch(InvalidInputException e) {
	    		System.out.println(e.getMessage());
	    	}
	        
	    }
}