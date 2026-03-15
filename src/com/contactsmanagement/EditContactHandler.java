package com.contactsmanagement;


import java.util.*;

import com.contacts.Contact;
import com.tags.Tag;
import com.tags.TagRepository;


public class EditContactHandler {
    private ContactManager manager = new ContactManager();

    public void editContact(Scanner sc, Map<String, Contact> contactList) {
        // List contacts with serial numbers
        int index = 1;
        System.out.println("");
        List<String> ids = new ArrayList<>(contactList.keySet());
        for (String id : ids) {
            System.out.println(index + ". " + contactList.get(id).getName());
            index++;
        }

        System.out.print("Select contact number to edit: ");
        int selected = sc.nextInt();
        sc.nextLine();

        if (selected < 1 || selected > ids.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        String contactId = ids.get(selected - 1);
        Contact contact = contactList.get(contactId);

        System.out.println("\nEditing Contact: " + contact.getName());
        System.out.println("Choose field to edit: \n 1. Name \n 2. Emails \n 3. Phone Numbers \n : ");
        int fieldChoice = sc.nextInt();
        sc.nextLine();

        switch (fieldChoice) {
            case 1: {
                System.out.print("\nEnter new name: ");
                String newName = sc.nextLine();
                Command cmd = new EditNameCommand(contact, newName);
                manager.executeCommand(cmd);
                break;
            }
            case 2: {
                System.out.print("Enter new emails (comma separated): ");
                String[] emails = sc.nextLine().split(",");
                List<String> newEmailList = new ArrayList<>(Arrays.asList(emails));
                Command cmd = new EditEmailCommand(contact, newEmailList);
                manager.executeCommand(cmd);
                break;
            }
            case 3: {
                System.out.print("Enter new phone numbers (comma separated): ");
                String[] phones = sc.nextLine().split(",");
                List<String> newPhoneList = new ArrayList<>(Arrays.asList(phones));
                Command cmd = new EditPhoneCommand(contact, newPhoneList);
                manager.executeCommand(cmd);
                break;
            }
            case 4: {
                System.out.print("Enter new tags (comma separated): ");
                String[] tagNames = sc.nextLine().split(",");
                Set<Tag> newTags = new HashSet<>();
                for (String t : tagNames) {
                    if (!t.trim().isEmpty()) {
                    	newTags.add(TagRepository.getTag(t.trim()));

                    }
                }
            }
            default:
                System.out.println("Invalid choice.");
        }

        System.out.print("Undo last change? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            manager.undoLastCommand();
        }

        System.out.print("Redo last change? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            manager.redoLastCommand();
        }

        System.out.println("\nFinal contact: " + contact);
    }
}