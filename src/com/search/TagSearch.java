package com.search;

import com.contacts.Contact;
import com.tags.Tag;

public class TagSearch implements SearchCriteria {
    private Tag tag;
    public TagSearch(Tag tag) { this.tag = tag; }

    @Override
    public boolean matches(Contact contact) {
        return contact.getTags().contains(tag);
    }
}