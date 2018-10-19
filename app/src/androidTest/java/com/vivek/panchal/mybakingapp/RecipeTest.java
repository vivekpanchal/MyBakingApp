package com.vivek.panchal.mybakingapp;

import android.os.Handler;
import android.os.Looper;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vivek.panchal.mybakingapp.UI.Activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class RecipeTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    //rule activitytestrule provides functional testing fr a specific activity

    @Test
    public void clickRecyclerViewItemOpens_RecipeActivity(){
//       onView(withId(R.id.recyclerviewrecipe))
//               .perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));
//        onView(withId(R.id.recipe_name)).check(matches(withText("Yellow Cake")));

        new Thread(() -> {
            Looper.prepare();
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                onData(anything()).inAdapterView(withId(R.id.recipe_list)).atPosition(0).perform(click());
                onView(withId(R.id.recipe_name_txt)).check(matches(withText("Nutella Pie")));
            }, 10000);
        }).run();
    }

}
