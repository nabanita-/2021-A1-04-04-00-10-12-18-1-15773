package com.example.myassignmentbynabanita;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.speech.RecognizerIntent;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    private static final String stringToBetyped = "This is a test";

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void test1a() {
        Intents.init();
        onView(withId(R.id.editText))
                .perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        intended(hasComponent(SecondActivity.class.getName()));
        intended(hasExtra(MainActivity.EXTRA_MESSAGE, stringToBetyped));
        Intents.release();
    }

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule1
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void test1b() {
        onView(withId(R.id.editText))
                .perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        pressBack();
        onView(withId(R.id.editText)).check(matches(withText(stringToBetyped)));
    }

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule2
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void test1c() {
        Intents.init();
        Intent speech_intent = new Intent();
        speech_intent.putExtra(RecognizerIntent.EXTRA_RESULTS, new ArrayList<String>(Arrays.asList(stringToBetyped)));
        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, speech_intent);
        // Set up result stubbing when an intent sent to "voice input" is seen.
        intending(hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)).respondWith(result);
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText(stringToBetyped)));
        Intents.release();
    }

}
