package com.profilemanagement;

import com.exception.InvalidInputException;
import com.userregistration.User;

public class UpdateNameCommand implements Command{
	private User userProfile;
    private String newName;
    private String oldName;

    public UpdateNameCommand(User userProfile, String newName) {
        this.userProfile = userProfile;
        this.newName = newName;
        this.oldName = userProfile.getName();
    }

    @Override
    public void execute() {
    	try {
    		userProfile.updateName(newName);
    	}catch(InvalidInputException e) {
    		System.out.println(e.getMessage());
    	}
    }
}