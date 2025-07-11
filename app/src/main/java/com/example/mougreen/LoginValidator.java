package com.example.mougreen;

public class LoginValidator {
    public static boolean isInputValid(String emailOrUsername, String password) {
        return emailOrUsername != null && !emailOrUsername.isEmpty()
                && password != null && !password.isEmpty();
    }
}