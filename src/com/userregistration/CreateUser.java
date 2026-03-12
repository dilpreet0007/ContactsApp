package com.userregistration; 

public class CreateUser {
    static int userCount;
    private String name;
    private String email;
    private String password;
    public User userType;

    // Factory constructor
    public CreateUser(User userType) {
        this.userType = userType;
    }

    // Builder constructor
    public CreateUser(UserBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.userType = builder.userType; 
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Password: " + password + ", Type: " + userType;
    }
}