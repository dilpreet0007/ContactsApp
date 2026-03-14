package com.contactsmanagement;
import com.contacts.Contact;
import com.exception.InvalidInputException;

public class EditNameCommand implements Command {
    private Contact contact;
    private String newName;
    private String oldName;

    public EditNameCommand(Contact contact, String newName) {
        this.contact = contact;
        this.newName = newName;
        this.oldName = contact.getName();
    }

    @Override
    public void execute() {
        try {
            contact.setName(newName);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undo() {
        try {
            contact.setName(oldName);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
