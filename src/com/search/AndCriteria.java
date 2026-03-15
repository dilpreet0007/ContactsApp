package com.search;

import com.contacts.Contact;

public class AndCriteria implements SearchCriteria {
    private SearchCriteria c1, c2;
    public AndCriteria(SearchCriteria c1, SearchCriteria c2) {
        this.c1 = c1; this.c2 = c2;
    }
    @Override
    public boolean matches(Contact contact) {
        return c1.matches(contact) && c2.matches(contact);
    }
}