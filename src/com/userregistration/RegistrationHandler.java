package com.userregistration;
import java.util.Scanner;

public class RegistrationHandler {
    public static User handleRegistration(Scanner sc) {
        FactoryUser factory = new FactoryUser();
        System.out.print("What type of subscription are you opting for : (Premium | Free) : ");
        String userTypeStr = sc.next();

        UserType userType = factory.createUserType(userTypeStr);

        System.out.print("Enter your name: ");
        String name = sc.next();

        System.out.print("Enter your email: ");
        String email = sc.next();

        System.out.print("Enter your password: ");
        String password = sc.next();

        // Builder usage
        User user = new User.Builder(name, userType)
                        .email(email)
                        .password(password)
                        .build();

        System.out.println("User created: " + user);
        return user;
    }
}