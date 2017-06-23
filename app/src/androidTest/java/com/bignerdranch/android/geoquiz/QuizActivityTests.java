package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class QuizActivityTests {

    @Rule
    public ActivityTestRule<QuizActivity> subject = new ActivityTestRule<>(QuizActivity.class);

    @Test
    public void testActivityInitializes() {
        onView(withId(R.id.question_text_view)).check(matches(
                withText("Canberra is the capital of Australia.")));
        onView(withId(R.id.true_button)).check(matches(withText("True")));
        onView(withId(R.id.false_button)).check(matches(withText("False")));
    }

    @Test
    public void testNavigation() {
        onView(withId(R.id.question_text_view)).check(matches(withText(
                R.string.question_australia)));

        tapNextButton();

        onView(withId(R.id.question_text_view)).check(matches(withText(R.string.question_oceans)));

        tapNextButton();

        onView(withId(R.id.question_text_view)).check(matches(withText(
                R.string.question_middle_east)));

        tapNextButton();

        onView(withId(R.id.question_text_view)).check(matches(withText(R.string.question_africa)));

        tapNextButton();

        onView(withId(R.id.question_text_view)).check(matches(withText(
                R.string.question_americas)));

        tapNextButton();

        onView(withId(R.id.question_text_view)).check(matches(withText(R.string.question_asia)));

        tapPreviousButton();

        onView(withId(R.id.question_text_view)).check(matches(withText(
                R.string.question_americas)));

        tapPreviousButton();

        onView(withId(R.id.question_text_view)).check(matches(withText(R.string.question_africa)));

        tapPreviousButton();

        onView(withId(R.id.question_text_view)).check(matches(withText(
                R.string.question_middle_east)));

        tapPreviousButton();

        onView(withId(R.id.question_text_view)).check(matches(withText(R.string.question_oceans)));

        tapPreviousButton();

        onView(withId(R.id.question_text_view)).check(matches(withText(
                R.string.question_australia)));
    }

    @Test
    public void testUserCanOnlyAnswerQuestionOnce() {
        tapTrueButton();

        onView(withId(R.id.true_button)).check(matches(not(isEnabled())));
        onView(withId(R.id.false_button)).check(matches(not(isEnabled())));

        tapNextButton();

        onView(withId(R.id.true_button)).check(matches(isEnabled()));
        onView(withId(R.id.false_button)).check(matches(isEnabled()));

        tapTrueButton();

        onView(withId(R.id.true_button)).check(matches(not(isEnabled())));
        onView(withId(R.id.false_button)).check(matches(not(isEnabled())));
    }

    @Test
    public void test_whenActivityIsRotated_thenUIStatePersists() {
        tapNextButton();
        rotateScreen();

        onView(withId(R.id.question_text_view)).check(matches(
                withText("The Pacific Ocean is larger than the Atlantic Ocean.")));
    }

    private void tapTrueButton() {
        onView(withId(R.id.true_button)).perform(click());
    }

    private void tapNextButton() {
        onView(withId(R.id.next_button)).perform(click());
    }

    private void tapPreviousButton() {
        onView(withId(R.id.prev_button)).perform(click());
    }

    private void rotateScreen() {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;

        subject.getActivity().setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}