package com.tags;

import java.util.Scanner;
import com.tags.TagRepository;

public class TagManagementHandler {
    public void manageTags(Scanner sc) {
        System.out.println("Tag Management: \n 1. Create Tag \n 2. View All Tags");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter new tag name: ");
                String tagName = sc.nextLine();
                System.out.println("Tag created: " + TagRepository.getTag(tagName));
                break;
            case 2:
                System.out.println("All Tags: " + TagRepository.getAllTags());
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}