package com.example.activityunittesting;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestResult = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(SecondActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestResult.getActivity();
    }
    @Test
    public void testLaunchOfSecondActivityOnButtonClick(){
        assertNotNull(mActivity.findViewById(R.id.button));
        onView(withId(R.id.button)).perform(click());
        Activity secondActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(secondActivity);
        secondActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}