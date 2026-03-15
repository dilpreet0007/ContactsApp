package com.filter;

import com.contacts.Contact;

public class FrequencyFilter implements Filter {
	 private int minCount;
	    public FrequencyFilter(int minCount) { this.minCount = minCount; }
	@Override
	public boolean matches(Contact contact) {
		 return contact.getContactCount() >= minCount;
	}

}