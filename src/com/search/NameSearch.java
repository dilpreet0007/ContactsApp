package com.search;


import com.contacts.Contact;

public class NameSearch implements SearchCriteria {
    private String keyword;
    public NameSearch(String keyword) { this.keyword = keyword.toLowerCase(); }

    @Override
    public boolean matches(Contact contact) {
        return contact.getName().toLowerCase().contains(keyword);
    }
}