package com.contacts;
import java.util.*;
import java.util.stream.Collectors;

public class BulkOperationHandler {
    public void performBulkOperation(Scanner sc, Map<String, Contact> contactList) {
        System.out.println("Choose bulk operation: \n 1. Delete \n 2. Export");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter filter keyword (e.g., gmail): ");
        String keyword = sc.nextLine();

        List<Contact> selected = contactList.values().stream()
                .filter(c -> c.getEmailList().stream().anyMatch(e -> e.contains(keyword)))
                .collect(Collectors.toList());

        if (selected.isEmpty()) {
            System.out.println("No contacts matched filter.");
            return;
        }

        BulkContactOperation bulkOp = new BulkContactOperation(selected);

        switch (choice) {
            case 1: bulkOp.deleteAll(); break;
            case 2:
                System.out.print("Enter file name to export: ");
                String fileName = sc.nextLine();
                bulkOp.exportAll(fileName);
                break;
            default: System.out.println("Invalid choice.");
        }
    }
}