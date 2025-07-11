package com.example.mougreen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.fail;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import android.content.Intent;
import android.content.Context;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AdminLoginToLogoutFlowTest {

    private UiDevice device;

    @Rule
    public ActivityTestRule<Login> activityRule =
            new ActivityTestRule<>(Login.class);

    @Before
    public void setUp() {
        // Pastikan pengguna logout sebelum test
        activityRule.getActivity().runOnUiThread(() -> {
            activityRule.getActivity().startActivity(new Intent(activityRule.getActivity(), Login.class));
        });
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void fullLoginLogoutFlowTest() throws InterruptedException {


        // Step 1: Login
        onView(withId(R.id.usernameoremail)).perform(typeText("angga123843@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("shinigamikira159"), closeSoftKeyboard());
        onView(withId(R.id.loginbutton)).perform(click());
        Thread.sleep(3000);

        device.pressHome();

        // Tunggu pindah ke MainMenu
        Thread.sleep(5000);

        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage("com.example.mougreen");
        if (intent == null) {
            fail("Intent aplikasi tidak ditemukan!");
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); // Reset stack
        context.startActivity(intent);

        Thread.sleep(5000); // Tunggu aplikas


        onView(withId(R.id.tombolCekLaporan)).perform(click());


        Thread.sleep(3000);


        onView(withId(R.id.laporanKembali)).perform(click());

        Thread.sleep(3000);

        onView(withId(R.id.tombolCekKritik)).perform(click());


        Thread.sleep(3000);


        onView(withId(R.id.kritikKembali)).perform(click());

        Thread.sleep(3000);

        onView(withId(R.id.tombolUserAccounts)).perform(click());


        Thread.sleep(3000);


        onView(withId(R.id.userAccountKembali)).perform(click());

        Thread.sleep(3000);

        onView(withId(R.id.tombolLogoutAdmin)).check(matches(isDisplayed()));

        Thread.sleep(5000);
    }
}
