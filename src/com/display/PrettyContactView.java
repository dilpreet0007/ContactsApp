package com.display;
import com.contacts.Contact;

public class PrettyContactView extends ContactViewDecorator {
    public PrettyContactView(ContactView view) {
        super(view);
    }

    @Override
    public String display(Contact contact) {
        return 
             "\nName: " + contact.getName() + "\n"
             + "Emails: " + contact.getEmailList() + "\n"
             + "Phones: " + contact.getPhoneNoList() + "\n"
             + "Date Added: " + contact.getDate() + "\n"
             + "-------------------------";
    }
}