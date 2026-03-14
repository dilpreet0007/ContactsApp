package com.contactsmanagement;
import java.util.List;

import com.contacts.Contact;
import com.exception.InvalidInputException;

public class EditEmailCommand implements Command {
    private Contact contact;
    private List<String> newEmails;
    private List<String> oldEmails;

    public EditEmailCommand(Contact contact, List<String> newEmails) {
        this.contact = contact;
        this.newEmails = newEmails;
        this.oldEmails = contact.getEmailList();
    }

    @Override
    public void execute() {
        try {
            contact.setEmailList(newEmails);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undo() {
        try {
            contact.setEmailList(oldEmails);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
}