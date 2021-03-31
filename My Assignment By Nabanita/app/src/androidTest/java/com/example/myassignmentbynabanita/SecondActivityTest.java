package com.example.myassignmentbynabanita;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SecondActivityTest {
    @Rule
    public ActivityScenarioRule<SecondActivity> activityRule3
            = new ActivityScenarioRule<>(SecondActivity.class);

    @Test
    public void test2a() {
        onView(withId(R.id.editText2))
                .perform(typeText("222"), closeSoftKeyboard());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.editText2)).check(matches(withHint("Enter a valid phone number or email address")));
    }

    @Rule
    public ActivityScenarioRule<SecondActivity> activityRule4
            = new ActivityScenarioRule<>(SecondActivity.class);

    @Test
    public void test2b() {
        onView(withId(R.id.editText2))
                .perform(typeText("n@@gm.c"), closeSoftKeyboard());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.editText2)).check(matches(withHint("Enter a valid phone number or email address")));
    }

}
