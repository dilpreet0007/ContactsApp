package com.filter;

import com.contacts.Contact;
import com.tags.Tag;

public class TagFilter implements Filter {

	 private Tag tag;
	    public TagFilter(Tag tag) { this.tag = tag; }
	@Override
	public boolean matches(Contact contact) {
		 return contact.getTags().contains(tag);
	}

}