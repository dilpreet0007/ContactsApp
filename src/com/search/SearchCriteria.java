package com.search;


import com.contacts.Contact;

public interface SearchCriteria {
    boolean matches(Contact contact);
}
