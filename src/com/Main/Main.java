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
import com.authentication.AuthContext;
import com.authentication.SessionManager;
import com.exception.InvalidInputException;
import com.profilemanagement.ProfileHandler;

/*
 *  UC3 :: User Profile Management
 * 	Logged-in user updates profile, password, or preferences.

	Create a User class with private fields.
	
	Use setters with validation for safe updates.
	
	Follow JavaBeans conventions (getters/setters).
	
	Apply Command Pattern for each update operation.
	
	Ensure security: hash passwords, validate inputs, sanitize data.

	@author Dilpreet
	@version 3.0
 */

public class Main {
	public static List<User> userList = new ArrayList<>();

	// Static block with demo users
	static {
		User user01 = new User.Builder("David", UserType.FREE)
				.email("david@gmail.com")
				.password("David@123")
				.build();
		User user02 = new User.Builder("Soe", UserType.PREMIUM)
				.email("Soe@gmail.com")
				.password("Soe@123")
				.build();
		userList.add(user01);
		userList.add(user02);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean isLoggedIn = false;
		User loggedInUser=null;
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
				userList.add(user); 
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


				loggedInUser = context.login(email, password);
				if (loggedInUser != null) {
					isLoggedIn = true;
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
		}


		if(isLoggedIn==false)return;

		System.out.println("\n\n----Welcome "+loggedInUser.getName()+"----");
		System.out.println("-----------------------------");
		boolean end = false;
		do {
			System.out.println(" 1. View Profile \n 2. Update Profile Info \n 3. End ");
			System.out.print(" : ");
			int ch = sc.nextInt();
			sc.nextLine();

			switch(ch) {
			case 1:{
				System.out.println("Your Profile Info : ");
				System.out.println(loggedInUser);
				break;
			}
			case 2:{
				try {
					ProfileHandler.updateProfile(loggedInUser, sc);
				}catch(InvalidInputException e) {
					System.out.println(e.getMessage());
				}
			}
			default :{
				end = true;
				break;
			}
			}
		}while(end==false);

	}
}