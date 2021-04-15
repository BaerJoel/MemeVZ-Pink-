package com.example.memevz;


import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditProfileActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void editProfileActivityTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("fabian.hepke@googlemail.com"), closeSoftKeyboard());
        SystemClock.sleep(500);
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("passwort"), closeSoftKeyboard());
        SystemClock.sleep(500);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login), withText("Sign in"),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatButton.perform(click());
        SystemClock.sleep(500);
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.btn_nav_profile),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        SystemClock.sleep(500);

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.profile_edit_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton2.perform(click());
        SystemClock.sleep(500);
        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("fabian.hepke"), closeSoftKeyboard());
        SystemClock.sleep(500);
        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.new_email),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("fabian@hepke.de"), closeSoftKeyboard());
        SystemClock.sleep(500);
        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.new_password),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("newpassword"), closeSoftKeyboard());
        SystemClock.sleep(500);
        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.new_password_repeat),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                4),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("newpassword"), closeSoftKeyboard());
        SystemClock.sleep(500);
        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.old_password),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                5),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("oldpassword"), closeSoftKeyboard());
        SystemClock.sleep(500);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_save_changes), withText("Save Changes"),
                        childAtPosition(
                                allOf(withId(R.id.main),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                6),
                        isDisplayed()));
        appCompatButton2.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
