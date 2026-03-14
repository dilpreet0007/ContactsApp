package com.main;
import java.time.LocalDate;
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
import com.contacts.Contact;
import com.contacts.CreateContact;
import com.contactsmanagement.EditContactHandler;
import com.display.BasicContactView;
import com.display.PrettyContactView;
import com.exception.InvalidInputException;
import com.profilemanagement.ProfileHandler;

/*
 *  UC6 :: Edit Contact
 * 	Logged-in user edits contact details.
	Setter methods validate updated values.
	Copy constructor ensures safe modifications.
	Command Pattern enables undo/redo.
	Memento Pattern preserves previous states.

	@author Dilpreet
	@version 6.0
 */

public class Main {
	public static List<User> userList = new ArrayList<>();
	public static Map<String,Contact> contactList = new HashMap<>();

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
		
		 // Creating Dummy Contacts
        String contactId1 = UUID.randomUUID().toString();
        List<String> emailList1 = new ArrayList<>(Arrays.asList("soe@gmail.com", "soe.work@gmail.com"));
        List<String> phoneNoList1 = new ArrayList<>(Arrays.asList("9991112223", "9988443322"));
        LocalDate date1 = LocalDate.parse("2026-01-02");
        Contact contact1 = new Contact.Builder("Soe")
                .contactId(contactId1)
                .date(date1)
                .email(emailList1)
                .phoneNo(phoneNoList1)
                .build();
        Main.contactList.put(contactId1, contact1);

        // Contact 02
        String contactId2 = UUID.randomUUID().toString();
        List<String> emailList2 = new ArrayList<>(Arrays.asList("david@gmail.com"));
        List<String> phoneNoList2 = new ArrayList<>(Arrays.asList("8765432198"));
        LocalDate date2 = LocalDate.parse("2026-02-15");
        Contact contact2 = new Contact.Builder("David")
                .contactId(contactId2)
                .date(date2)
                .email(emailList2)
                .phoneNo(phoneNoList2)
                .build();
        Main.contactList.put(contactId2, contact2);
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
	        	System.out.println(" 1. View Profile \n 2. Update Profile Info \n 3. View Contacts \n 4. Add Contacts \n 5. Edit Contacts \n 6. End ");
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
	            case 3:{
	            	System.out.println("\n Your Contact List : ");
	            	
	            	for(Map.Entry<String, Contact> entry : contactList.entrySet()) {
	            		PrettyContactView view = new PrettyContactView(new BasicContactView());   
	            		System.out.println(view.display(entry.getValue()));
	            	}  
	            	System.out.println("\n");
	            	break;
	            }
	            case 4:{
	            	System.out.println("Adding a New Contact \n");
	            	try {
	            		Contact contact = CreateContact.createContact(sc);
	            		System.out.println("\nSuccessfull added contact : "+contact);
	            	}catch(InvalidInputException e) {
	            		System.out.println(e.getMessage());
	            	}
	            	break;
	            }
	            case 5:{
	            	EditContactHandler handler = new EditContactHandler();
	                handler.editContact(sc, contactList);
	                break;
	            }
	            default :{
	            	end = true;
	            	break;
	            }
	            }
	        }while(end==false);
	    }
}