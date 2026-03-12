package com.main;
import java.util.*;

import com.userregistration.CreateUser;
import com.userregistration.FactoryUser;
import com.userregistration.RegistrationHandler;
import com.userregistration.User;
import com.userregistration.UserBuilder;
import com.userregistration.UserType;

import java.util.Scanner;
import java.util.UUID;
import java.util.*;
import com.authentication.AuthContext;
import com.authentication.SessionManager;
import com.exception.InvalidInputException;

public class Main {
    public static List<User> userList = new ArrayList<>();

    // Static block with demo users
    static {
        User user01 = new User.Builder("Vivek", UserType.FREE)
                .email("vivek@gmail.com")
                .password("vivek1010")
                .build();
        User user02 = new User.Builder("Shreyas", UserType.PREMIUM)
                .email("shreyas@gmail.com")
                .password("shreyas1010")
                .build();
        userList.add(user01);
        userList.add(user02);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("---------------------------------------------");
            System.out.println("----- Welcome to MyContacts Application -----");
            System.out.println("---------------------------------------------");
            System.out.print("\n 1. Register \n 2. Login \n : ");
            String choiceStr = sc.nextLine();

            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Invalid choice. Please enter 1 for Register or 2 for Login.");
            }

            if (choice == 1) {
                User user = RegistrationHandler.handleRegistration(sc);
                userList.add(user); // add new user to list
                System.out.println("Registration successful for: " + user.getEmail());
            } else if (choice == 2) {
                System.out.println("Choose login type: basic / oauth");
                String loginType = sc.nextLine();

                if (!loginType.equalsIgnoreCase("basic") && !loginType.equalsIgnoreCase("oauth")) {
                    throw new InvalidInputException("Invalid login type entered. Please choose 'basic' or 'oauth'.");
                }

                AuthContext context = new AuthContext();
                context.setStrategy(loginType);

                System.out.print("Enter email: ");
                String email = sc.nextLine();
                if (email == null || email.isEmpty()) {
                    throw new InvalidInputException("Email cannot be empty.");
                }

                System.out.print("Enter password: ");
                String password = sc.nextLine();
                if (password == null || password.isEmpty()) {
                    throw new InvalidInputException("Password cannot be empty.");
                }

                
                User loggedInUser = context.login(email, password);
                if (loggedInUser != null) {
                    if (loginType.equalsIgnoreCase("oauth")) {
                        String token = UUID.randomUUID().toString();
                        SessionManager sm = SessionManager.startSession();
                        sm.createSession(token, loggedInUser);
                        System.out.println("Login successful! Session ID: " + token);
                    } else {
                        System.out.println("Login successful with Basic Auth!");
                    }
                } else {
                    System.out.println("Login failed. Invalid credentials.");
                }
            } else {
                throw new InvalidInputException("Invalid choice. Please enter 1 for Register or 2 for Login.");
            }
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}