package com.search;

import com.contacts.Contact;

public class EmailSearch implements SearchCriteria {
    private String keyword;
    public EmailSearch(String keyword) { this.keyword = keyword.toLowerCase(); }

    @Override
    public boolean matches(Contact contact) {
        return contact.getEmailList().stream()
                .anyMatch(e -> e.toLowerCase().contains(keyword));
    }
}