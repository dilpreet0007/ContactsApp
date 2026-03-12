package com.userregistration;

public class FactoryUser {
    public UserType createUserType(String userType) {
        if (userType == null) return null;

        if (userType.equalsIgnoreCase("PREMIUM")) {
            return UserType.PREMIUM;
        } else if (userType.equalsIgnoreCase("FREE")) {
            return UserType.FREE;
        }
        return null;
    }
}