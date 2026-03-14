package com.contactsmanagement;

public interface Command {
    void execute();
    void undo();
}