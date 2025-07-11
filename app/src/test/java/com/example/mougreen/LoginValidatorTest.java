package com.example.mougreen;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginValidatorTest {

    @Test
    public void validInput_returnsTrue() {
        assertTrue(LoginValidator.isInputValid("user@email.com", "password123"));
    }

    @Test
    public void emptyEmail_returnsFalse() {
        assertFalse(LoginValidator.isInputValid("", "password123"));
    }

    @Test
    public void emptyPassword_returnsFalse() {
        assertFalse(LoginValidator.isInputValid("user@email.com", ""));
    }

    @Test
    public void nullValues_returnFalse() {
        assertFalse(LoginValidator.isInputValid(null, null));
    }
}
