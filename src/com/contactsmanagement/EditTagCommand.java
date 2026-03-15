package com.contactsmanagement;


import java.util.Set;
import java.util.HashSet;
import com.contacts.Contact;
import com.tags.Tag;

public class EditTagCommand implements Command {
    private Contact contact;
    private Set<Tag> newTags;
    private Set<Tag> oldTags;

    public EditTagCommand(Contact contact, Set<Tag> newTags) {
        this.contact = contact;
        this.newTags = newTags;
        this.oldTags = new HashSet<>(contact.getTags());
    }

    @Override
    public void execute() {
        contact.getTags().clear();
        contact.getTags().addAll(newTags);
    }

    @Override
    public void undo() {
        contact.getTags().clear();
        contact.getTags().addAll(oldTags);
    }
}
