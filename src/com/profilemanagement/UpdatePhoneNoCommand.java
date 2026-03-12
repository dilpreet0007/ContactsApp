package com.profilemanagement;
import com.exception.InvalidInputException;
import com.userregistration.User;

public class UpdatePhoneNoCommand implements Command{
	private User userProfile;
    private String newPhone;
    private String oldPhone;

    public UpdatePhoneNoCommand(User userProfile, String newPhone) {
        this.userProfile = userProfile;
        this.newPhone = newPhone;
        this.oldPhone = userProfile.getPhoneNo();
    }

    @Override
    public void execute() {
    	try {
    		 userProfile.updatePhoneNo(newPhone);
    	}catch(InvalidInputException e) {
    		System.out.println(e.getMessage());
    	}
       
    }
}