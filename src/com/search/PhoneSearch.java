package com.search;

import com.contacts.Contact;

public class PhoneSearch implements SearchCriteria {
    private String keyword;
    public PhoneSearch(String keyword) { this.keyword = keyword; }

    @Override
    public boolean matches(Contact contact) {
        return contact.getPhoneNoList().stream()
                .anyMatch(p -> p.contains(keyword));
    }
}