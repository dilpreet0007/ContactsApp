package com.filter;

import com.contacts.Contact;
import java.util.List;

public class CompositeFilter implements Filter {
    private List<Filter> filters;

    public CompositeFilter(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean matches(Contact contact) {
        return filters.stream().allMatch(f -> f.matches(contact));
    }
}