package com.example.mougreen;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginLogoutFlowTest {

    @Rule
    public ActivityTestRule<Login> activityRule =
            new ActivityTestRule<>(Login.class);

    @Before
    public void setUp() {
        // Pastikan pengguna logout sebelum test
        activityRule.getActivity().runOnUiThread(() -> {
            activityRule.getActivity().startActivity(new Intent(activityRule.getActivity(), Login.class));
        });
    }

    @Test
    public void fullLoginLogoutFlowTest() throws InterruptedException {
        // Step 1: Login
        onView(withId(R.id.usernameoremail)).perform(typeText("angga123843@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.loginbutton)).perform(click());

        // Tunggu pindah ke MainMenu
        Thread.sleep(5000);

        // Step 2: Navigasi ke Profil
        onView(withId(R.id.fotoProfil)).perform(click());

        // Tunggu ProfilUser terbuka
        Thread.sleep(5000);

        // Step 3: Logout
        onView(withId(R.id.tombolLogout)).perform(click());

        // Step 4: Pastikan kembali ke Login
        Thread.sleep(5000);
        onView(withId(R.id.loginbutton)).check(matches(isDisplayed()));
    }
}
