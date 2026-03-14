package com.contactsmanagement;
import java.util.Stack;

public class ContactManager {
    private Stack<Command> history = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
        redoStack.clear(); 
    }

    public void undoLastCommand() {
        if (!history.isEmpty()) {
            Command last = history.pop();
            last.undo();
            redoStack.push(last);
        }
    }

    public void redoLastCommand() {
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            history.push(cmd);
        }
    }
}
