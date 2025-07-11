// File: AuthManager.java
package com.example.mougreen;

import com.google.firebase.auth.FirebaseAuth;

public class AuthManager {
    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }

    public boolean isLoggedOut() {
        return FirebaseAuth.getInstance().getCurrentUser() == null;
    }
}
