package com.contacts;
import java.util.*;
import java.time.*;


public class Contact {
	private String name;
	private List<String> emailList = new ArrayList<>();
	private List<String> phoneNoList = new ArrayList<>();
	private LocalDate date;
	private String contactId;
	
	private Contact(Builder builder) {
		this.name = builder.name;
		this.emailList = builder.emailList;
		this.phoneNoList = builder.phoneNoList;
		this.date = builder.date;
		this.contactId = builder.contactId;
	}
	
	
	public static class Builder{
		private String name;
		private List<String> emailList = new ArrayList<>();
		private List<String> phoneNoList = new ArrayList<>();
		private LocalDate date;
		private String contactId;
		
		public Builder(String name) {
			this.name = name;
		}
		
		public Builder email(List<String> emails) {
			emailList = emails;
			return this;
		}
		
		public Builder phoneNo(List<String> phoneNos) {
			phoneNoList = phoneNos;
			return this;
		}
		
		public Builder date(LocalDate date) {
			this.date = date;
			return this;
		}
		
		public Builder contactId(String contactId) {
			this.contactId = contactId;
			return this;
		}
		
		public Contact build() {
			return new Contact(this);
		}
	}


	public String getName() {return name;}

	public List<String> getEmailList() { return emailList;}

	public List<String> getPhoneNoList() {return phoneNoList;}

	public LocalDate getDate() {return date;}

	public String getContactId() {return contactId;}
	
	@Override
	public String toString() {
		return "\nName : "+name+" Emails : "+emailList+" Phone No : "+phoneNoList + " Date Added : "+date+"\n";
	}
}