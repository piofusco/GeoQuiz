package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class CheatActivityTests {

    @Rule
    public ActivityTestRule<CheatActivity> subject = new ActivityTestRule<>(CheatActivity.class);

    @Test
    public void testActivityInitializes() {
        onView(withId(R.id.question_text_view)).check(matches(
                withText("Are you sure you want to do this?")));
        onView(withId(R.id.answer_text_view)).check(matches(withText("Answer")));
        onView(withId(R.id.show_answer_button)).check(matches(withText("SHOW ANSWER")));
    }

    @Test
    public void test_givenAnswerHasBeenShown_whenActivityIsRotated_thenUIStatePersists() {
        tapShowAnswerButton();

        rotateScreen();

        onView(withId(R.id.answer_text_view)).check(matches(withText("False")));
    }

    private void tapShowAnswerButton() {
        onView(withId(R.id.show_answer_button)).perform(click());
    }

    private void rotateScreen() {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;

        subject.getActivity().setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}