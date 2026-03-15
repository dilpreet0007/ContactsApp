package com.userregistration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.exception.InvalidInputException;
import com.validation.EmailValidator;
import com.validation.NameValidator;
import com.validation.PhoneNoValidator;

public class User {
	
    private String name;
    private String email;
    private String password;
    private String phoneNo;
    private UserType userType;

   
    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.password = hashPassword(builder.password);
        this.userType = builder.userType;
        this.phoneNo = builder.phoneNo;
    }
    
    public void updateName(String name) throws InvalidInputException {
    	if(!NameValidator.validateName(name)) throw new InvalidInputException("Name is Not Valid");
        this.name = name;
        System.out.println("Name updated to: " + name);
    }

    public void updateEmail(String email) throws InvalidInputException {
    	 if(!EmailValidator.validateEmail(email)) throw new InvalidInputException("Email is Not Valid");
        this.email = email;
        
        System.out.println("Email updated to: " + email);
    }

    public void updatePhoneNo(String phoneNo) throws InvalidInputException{
    	if(!PhoneNoValidator.validatePhoneNo(phoneNo)) throw new InvalidInputException("Phone Number is Not Valid");
        this.phoneNo = phoneNo;
        System.out.println("Phone number updated to: " + phoneNo);
    }

    // Builder class
    public static class Builder {
        private String name;
        private String email;
        private String password;
        private String phoneNo;
        private UserType userType;

        public Builder(String name, UserType userType) {
            this.name = name;
            this.userType = userType;
        }

        public Builder email(String email) {
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new IllegalArgumentException("Invalid email format");
            }
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }
        
        public Builder phoneNo(String phoneNo) {
        	try {
        		String regex = "^[6-9]\\d{9}$";
        		if(!phoneNo.matches(regex))
        		 throw new InvalidInputException("Invalid Phone Number !");
        	}catch(InvalidInputException e) {
        		System.out.println(e.getMessage());
        	}
        	this.phoneNo=phoneNo;
        	return this;
        	
        }

        public User build() {
            return new User(this);
        }
    }


    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public UserType getUserType() { return userType; }
    public String getPhoneNo() {return phoneNo;}

    @Override
    public String toString() {
        return "\nName: " + name + ", Email: " + email + ", Phone No: "+phoneNo+", Type: " + userType+"\n";
    }
}