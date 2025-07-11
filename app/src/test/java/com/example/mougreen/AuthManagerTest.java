// File: AuthManagerTest.java (letakkan di: src/test/java/com/example/mougreen/)
package com.example.mougreen;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AuthManagerTest {

    private AuthManager authManager;

    @Before
    public void setUp() {
        authManager = new AuthManager();
    }

    @Test
    public void logout_shouldSignOutUser() {
        // Warning: ini hanya bisa jalan jika FirebaseAuth bisa di-mock
        // atau kamu jalankan di instrumented test (androidTest)

        authManager.logout();
        assertTrue(authManager.isLoggedOut());
    }
}
