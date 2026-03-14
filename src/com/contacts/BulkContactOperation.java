package com.contacts;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.main.Main;

public class BulkContactOperation {
    private List<Contact> contacts;

    public BulkContactOperation(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void deleteAll() {
        contacts.forEach(c -> Main.contactList.remove(c.getContactId()));
        System.out.println("Deleted " + contacts.size() + " contacts.\n");
    }

    public void exportAll(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Contact c : contacts) {
                writer.write(c.toString() + System.lineSeparator());
            }
            System.out.println("Exported " + contacts.size() + " contacts to " + fileName);
        } catch (IOException e) {
            System.out.println("Error exporting contacts: " + e.getMessage());
        }
    }
}