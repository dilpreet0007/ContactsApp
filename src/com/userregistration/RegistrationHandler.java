package com.userregistration;
import java.util.Scanner;

import com.exception.InvalidInputException;
import com.validation.EmailValidator;
import com.validation.NameValidator;
import com.validation.PhoneNoValidator;

public class RegistrationHandler {
    public static User handleRegistration(Scanner sc) throws InvalidInputException{
        FactoryUser factory = new FactoryUser();
        System.out.print("What type of subscription are you opting for : (Premium | Free) : ");
        String userTypeStr = sc.nextLine();

        UserType userType = factory.createUserType(userTypeStr);

        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        if(!NameValidator.validateName(name)) throw new InvalidInputException("Name is Not Valid");

        System.out.print("Enter your email: ");
        String email = sc.nextLine();
        if(!EmailValidator.validateEmail(name)) throw new InvalidInputException("Name is Not Valid");
        
        System.out.print("Enter your Phone No : ");
        String phoneNo = sc.nextLine();
        if(!PhoneNoValidator.validatePhoneNo(name)) throw new InvalidInputException("Name is Not Valid");

        System.out.print("Enter your password: ");
        String password = sc.next();

        User user = new User.Builder(name, userType)
                        .email(email)
                        .password(password)
                        .password(password)
                        .build();

        System.out.println("User created: " + user);
        return user;
    }
}