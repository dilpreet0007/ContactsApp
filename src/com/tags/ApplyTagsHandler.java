package com.tags;

import java.util.*;
import com.contacts.Contact;
import com.tags.TagRepository;

public class ApplyTagsHandler {
    public void applyTags(Scanner sc, Map<String, Contact> contactList) {
        if (contactList.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        // Show contacts
        List<String> ids = new ArrayList<>(contactList.keySet());
        for (int i = 0; i < ids.size(); i++) {
            System.out.println((i+1) + ". " + contactList.get(ids.get(i)).getName());
        }

        System.out.print("Choose contact number: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > ids.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Contact contact = contactList.get(ids.get(choice-1));

        // Show available tags
        System.out.println("Available tags: " + TagRepository.getAllTags());
        System.out.print("Enter tags to assign (comma separated): ");
        String tagInput = sc.nextLine();

        if (!tagInput.trim().isEmpty()) {
            String[] tagNames = tagInput.split(",");
            for (String t : tagNames) {
                contact.addTag(TagRepository.getTag(t.trim()));
            }
        }

        System.out.println("Tags applied to " + contact.getName() + ": " + contact.getTags());
    }
}