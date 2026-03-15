package com.contacts;
import java.util.*;

import com.exception.InvalidInputException;
import com.tags.Tag;
import com.validation.EmailValidator;
import com.validation.NameValidator;
import com.validation.PhoneNoValidator;

import java.time.*;


public class Contact {
	private String name;
	private List<String> emailList = new ArrayList<>();
	private List<String> phoneNoList = new ArrayList<>();
	private LocalDate date;
	private String contactId;
	private Set<Tag> tags = new HashSet<>();

	private Contact(Builder builder) {
		this.name = builder.name;
		this.emailList = builder.emailList;
		this.phoneNoList = builder.phoneNoList;
		this.date = builder.date;
		this.contactId = builder.contactId;
	}
	
    public Set<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }
	
	  public void setName(String name) throws InvalidInputException {
	        NameValidator.validateName(name);
	        this.name = name;
	    }

	    public void setEmailList(List<String> emails) throws InvalidInputException {
	        for (String email : emails) {
	            EmailValidator.validateEmail(email);
	        }
	        this.emailList = emails;
	    }

	    public void setPhoneNoList(List<String> phones) throws InvalidInputException {
	        for (String phone : phones) {
	            PhoneNoValidator.validatePhoneNo(phone);
	        }
	        this.phoneNoList = phones;
	    }

	    public void setDate(LocalDate date) {
	        this.date = date;
	    }

	public String getName() {return name;}

	public List<String> getEmailList() { return emailList;}

	public List<String> getPhoneNoList() {return phoneNoList;}

	public LocalDate getDate() {return date;}

	public String getContactId() {return contactId;}
	
	@Override
	public String toString() {
		return "\nName : "+name+" Emails : "+emailList+" Phone No : "+phoneNoList +" Tags : "+tags+" Date Added : "+date+"\n";
	}
	
	
	public static class Builder{
		private String name;
		private List<String> emailList = new ArrayList<>();
		private List<String> phoneNoList = new ArrayList<>();
		private LocalDate date;
		private String contactId;
		private Set<Tag> tags = new HashSet<>();
		
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
		public Builder tags(Set<Tag> tags) {
			this.tags = tags;
			return this;
		}
		
		public Contact build() {
			return new Contact(this);
		}
	}

}