package com.example.mougreen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FiturKritikFlowTest {

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
        onView(withId(R.id.usernameoremail)).perform(typeText("testuser1752172385954@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("test1234"), closeSoftKeyboard());
        onView(withId(R.id.loginbutton)).perform(click());

        // Tunggu pindah ke MainMenu
        Thread.sleep(5000);

        // Step 2: Navigasi ke Profil
        onView(withId(R.id.bottom_kritik)).perform(click());

        // Tunggu ProfilUser terbuka
        Thread.sleep(5000);

        onView(withId(R.id.inputNomorTeleponKritik)).perform(typeText("08696969696969"), closeSoftKeyboard());
        onView(withId(R.id.inputTextKomentarKritik)).perform(typeText("testing fitur kritik"), closeSoftKeyboard());

        // Step 3: Logout
        onView(withId(R.id.tombolKirimKritik)).perform(click());
    }
}
