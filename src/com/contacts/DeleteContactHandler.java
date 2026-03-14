package com.contacts;

import java.util.*;

public class DeleteContactHandler {

    public void deleteContact(Scanner sc, Map<String, Contact> contactList) {
        if (contactList.isEmpty()) {
            System.out.println("No contacts available to delete.");
            return;
        }

        int index = 1;
        List<String> ids = new ArrayList<>(contactList.keySet());
        for (String id : ids) {
            System.out.println(index + ". " + contactList.get(id).getName());
            index++;
        }

        System.out.print("Select contact number to delete: ");
        int selected = sc.nextInt();
        sc.nextLine();

        if (selected < 1 || selected > ids.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        String contactId = ids.get(selected - 1);
        Contact contact = contactList.get(contactId);

        System.out.println("Are you sure you want to delete contact: " + contact.getName() + " ? (y/n)");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            contactList.remove(contactId);
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
}