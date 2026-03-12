package com.userregistration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	
    private String name;
    private String email;
    private String password;
    private UserType userType;

    // Private constructor (only Builder can call this)
    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.password = hashPassword(builder.password);
        this.userType = builder.userType;
    }

    // Builder class
    public static class Builder {
        private String name;
        private String email;
        private String password;
        private UserType userType;

        public Builder(String name, UserType userType) {
            this.name = name;
            this.userType = userType;
        }

        public Builder email(String email) {
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new IllegalArgumentException("Invalid email format");
            }
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    // Hashing logic
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public UserType getUserType() { return userType; }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Type: " + userType;
    }
}