package com.contacts;
import java.time.LocalDate;
import java.util.*;
import com.exception.InvalidInputException;
import com.tags.Tag;
import com.main.Main;
import com.validation.EmailValidator;
import com.validation.NameValidator;
import com.validation.PhoneNoValidator;

public class CreateContact {
    public static Contact createContact(Scanner sc) throws InvalidInputException {
        System.out.println("");
        Contact contact = null;
        try {
            String contactId = UUID.randomUUID().toString();

            System.out.print("Enter Contact Name : ");
            String name = sc.nextLine();
            NameValidator.validateName(name);

            System.out.print("Enter Email Id's of the contact :(separated by ',') : ");
            String[] emails = sc.nextLine().split(",");
            for (String email : emails) {
                EmailValidator.validateEmail(email);
            }
            List<String> emailList = new ArrayList<>(Arrays.asList(emails));

            System.out.print("Enter Phone No's of the contact :(separated by ',') : ");
            String[] phoneNos = sc.nextLine().split(",");
            for (String phoneNo : phoneNos) {
                PhoneNoValidator.validatePhoneNo(phoneNo);
            }
            List<String> phoneNoList = new ArrayList<>(Arrays.asList(phoneNos));

            LocalDate date = LocalDate.now();

            // Build contact first
            contact = new Contact.Builder(name)
                        .contactId(contactId)
                        .date(date)
                        .email(emailList)
                        .phoneNo(phoneNoList)
                        .build();

            // Now add tags
            System.out.print("Enter tags (comma separated, e.g., Family, Work): ");
            String tagInput = sc.nextLine();
            if (!tagInput.trim().isEmpty()) {
                String[] tagNames = tagInput.split(",");
                for (String t : tagNames) {
                    contact.addTag(new Tag(t.trim()));
                }
            }

            // Save contact
            Main.contactList.put(contactId, contact);

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }

        return contact;
    }
}