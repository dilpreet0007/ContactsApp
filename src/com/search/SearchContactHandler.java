package com.search;


import java.util.*;
import java.util.stream.Collectors;
import com.contacts.Contact;
import com.exception.InvalidInputException;
import com.tags.Tag;

public class SearchContactHandler {
    public void searchContacts(Scanner sc, Map<String, Contact> contactList) throws InvalidInputException{
    	
    
    		System.out.println("Search by: \n 1. Name \n 2. Email \n 3. Phone \n 4. Tag \n  : ");
            int choice = sc.nextInt();
            sc.nextLine();

            final SearchCriteria criteria;

            switch (choice) {
                case 1:
                    System.out.print("Enter name keyword: ");
                    criteria = new NameSearch(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Enter email keyword: ");
                    criteria = new EmailSearch(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Enter phone keyword: ");
                    criteria = new PhoneSearch(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Enter tag name: ");
                    String tag = sc.nextLine();
                    criteria = new TagSearch(new Tag(tag));
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }
            
            List<Contact> results = contactList.values().stream()
                    .filter(c -> criteria.matches(c))
                    .collect(Collectors.toList());

            if (results.isEmpty()) {
                System.out.println("No contacts found.");
            } else {
                System.out.println("\nSearch Results:");
                results.forEach(System.out::println);
            }         
    	}
        

       
    }