package com.authentication;

import com.userregistration.User;

public class AuthContext {
    private Authentication strategy;

    public void setStrategy(String str) {
        if (str.equalsIgnoreCase("basic")) {
            strategy = new BasicAuthStrategy();
        } else if (str.equalsIgnoreCase("oauth")) {
            strategy = new OAuthStrategy();
        }
    }

    // Now returns User instead of boolean
    public User login(String email, String password) {
        if (strategy == null) {
            System.out.println("No authentication strategy selected!");
            return null;
        }
        return strategy.authenticate(email, password);
    }
}