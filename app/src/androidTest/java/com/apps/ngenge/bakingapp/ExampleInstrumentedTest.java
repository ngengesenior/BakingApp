package com.apps.ngenge.bakingapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import com.apps.ngenge.bakingapp.ui.activities.RecipeListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private int position = 1;
    @Rule
    public ActivityTestRule<RecipeListActivity> activityRule = new ActivityTestRule<>(RecipeListActivity.class);
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.apps.ngenge.bakingapp", appContext.getPackageName());
    }


    @Test
    public void testThatRecipeListIsDisplayed() {
        onView(withId(R.id.recipesList)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecipeClickedAndIngredientsAndStepsViewShown() {

        onView(withId(R.id.recipesList)).perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));
        onView(withId(R.id.ingredientsCard)).check(matches(isDisplayed()));
        onView(withId(R.id.stepsRecyclerView)).check(matches(isDisplayed()));
    }


}
