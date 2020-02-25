package com.example.espressotestingui;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private String mName = "Tony";

    @Test
    public void testUserInputScenario(){
        //input some text in the edit text
        Espresso.onView(withId(R.id.editText)).perform(typeText(mName));
        // close soft keyboard
        Espresso.closeSoftKeyboard();
        // perform button click
        Espresso.onView(withId(R.id.button));
        //checking the text in the textview
        Espresso.onView(withId(R.id.changedTextView)).check(matches(withText(mName)));

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}