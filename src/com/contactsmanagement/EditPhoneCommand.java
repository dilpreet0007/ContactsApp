package com.contactsmanagement;
import java.util.List;

import com.contacts.Contact;
import com.exception.InvalidInputException;

public class EditPhoneCommand implements Command {
    private Contact contact;
    private List<String> newPhones;
    private List<String> oldPhones;

    public EditPhoneCommand(Contact contact, List<String> newPhones) {
        this.contact = contact;
        this.newPhones = newPhones;
        this.oldPhones = contact.getPhoneNoList();
    }

    @Override
    public void execute() {
        try {
            contact.setPhoneNoList(newPhones);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undo() {
        try {
            contact.setPhoneNoList(oldPhones);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
