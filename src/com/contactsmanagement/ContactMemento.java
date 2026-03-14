package com.contactsmanagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.contacts.Contact;
import com.exception.InvalidInputException;

public class ContactMemento {
    private final String name;
    private final List<String> emailList;
    private final List<String> phoneNoList;
    private final LocalDate date;

    public ContactMemento(Contact contact) {
        this.name = contact.getName();
        this.emailList = new ArrayList<>(contact.getEmailList());
        this.phoneNoList = new ArrayList<>(contact.getPhoneNoList());
        this.date = contact.getDate();
    }

    public void restore(Contact contact) {
    	try {
    		 contact.setName(name);
    	        contact.setEmailList(new ArrayList<>(emailList));
    	        contact.setPhoneNoList(new ArrayList<>(phoneNoList));
    	        contact.setDate(date);
    	}catch(InvalidInputException e) {
    		System.out.println(e.getMessage());
    	}
    }
}