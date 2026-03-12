package com.display;

import com.contacts.Contact;

public abstract class ContactViewDecorator implements ContactView {
    protected ContactView wrappedView;

    public ContactViewDecorator(ContactView view) {
        this.wrappedView = view;
    }

    @Override
    public String display(Contact contact) {
        return wrappedView.display(contact);
    }
}