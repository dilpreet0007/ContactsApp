package com.filter;

import java.time.LocalDate;

import com.contacts.Contact;

public class DateFilter implements Filter {

	private LocalDate afterDate;
    public DateFilter(LocalDate afterDate) { this.afterDate = afterDate; }
	@Override
	public boolean matches(Contact contact) {
		  return contact.getDate().isAfter(afterDate);
	}

}