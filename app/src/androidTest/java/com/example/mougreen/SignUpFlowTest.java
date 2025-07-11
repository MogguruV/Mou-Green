package com.example.mougreen;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class SignUpFlowTest {

    @Rule
    public ActivityTestRule<RegisterActivity> signUpRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void testSignUpSuccess() throws InterruptedException {
        // Input data sign up
        onView(withId(R.id.email)).perform(typeText("testuser" + System.currentTimeMillis() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test1234"), closeSoftKeyboard());
        onView(withId(R.id.confirm_password)).perform(typeText("test1234"), closeSoftKeyboard());

        // Klik tombol sign up
        onView(withId(R.id.signupNext)).perform(click());

        // Tunggu transisi ke halaman login/main
        Thread.sleep(5000);

        // Input Data Warga
        onView(withId(R.id.username)).perform(typeText("TestUser"), closeSoftKeyboard());
        onView(withId(R.id.namaWarga)).perform(typeText("usertest" + System.currentTimeMillis() + "@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.nomorRumah)).perform(typeText("69"), closeSoftKeyboard());

        onView(withId(R.id.signup)).perform(click());

        Thread.sleep(5000);


    }
}
