package com.vivek.panchal.mybakingapp;

import android.os.Handler;
import android.os.Looper;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vivek.panchal.mybakingapp.UI.Activities.StepsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsAnything.anything;

@RunWith(AndroidJUnit4.class)
public class StepsTest {

    @Rule
    public ActivityTestRule<StepsActivity> selectStepActivityActivityTestRule =
            new ActivityTestRule<>(StepsActivity.class);


    @Test
    public void clickRecyclerViewItem_opensViewStepActivity() {
        new Thread(() -> {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                onData(anything()).inAdapterView(withId(R.id.step_Card)).atPosition(0).perform(click());
            }, 10000);
        }).run();
    }

}
