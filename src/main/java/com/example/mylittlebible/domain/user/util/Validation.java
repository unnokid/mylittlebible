package com.example.mylittlebible.domain.user.util;

public class Validation {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\\-_=+]).{8,15}$";

    public static boolean validatePassword(String password) {
        if (!password.matches(PASSWORD_PATTERN)) {
            throw new RuntimeException();
        }
        return true;
    }

    public static boolean validateEmail(String email) {
        if (!email.matches(EMAIL_PATTERN)) {
            throw new RuntimeException();
        }
        return true;
    }
}
