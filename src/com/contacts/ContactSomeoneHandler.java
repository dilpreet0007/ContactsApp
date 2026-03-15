package com.contacts;

import java.util.*;

public class ContactSomeoneHandler {
    public void contactSomeone(Scanner sc, Map<String, Contact> contactList) {
        int index = 1;
        List<String> ids = new ArrayList<>(contactList.keySet());

        System.out.println("\nChoose a contact to contact:");
        for (String id : ids) {
            System.out.println(index + ". " + contactList.get(id).getName());
            index++;
        }

        System.out.print("Enter contact number: ");
        int selected = sc.nextInt();
        sc.nextLine();

        if (selected < 1 || selected > ids.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        String contactId = ids.get(selected - 1);
        Contact contact = contactList.get(contactId);

        System.out.println("\nContacting " + contact.getName() + "...");
        System.out.println("Successfully contacted " + contact.getName());
        System.out.println("\n");

        contact.incrementContactCount();
    }
}