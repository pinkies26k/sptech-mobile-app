package com.test.sptech;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.test.sptech.Activities.DisplayClickableImageActivity;
import com.test.sptech.Adapter.ItemDataWIthImageAdapter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class DisplayClickableImageActivityTest {

    @Rule
    public ActivityTestRule<DisplayClickableImageActivity> mActivityTestRule =
            new ActivityTestRule<>(DisplayClickableImageActivity.class);

    @Test
    public void clickTheArrowImage_PromptPopUp(){

        // verify the visibility of recycler view on screen
        onView(withId(R.id.rvYearlyWithImageList)).check(matches(isDisplayed()));

//        // perform click on view at 3rd position in RecyclerView
//        onView(withId(R.id.rvYearlyWithImageList))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

        // select view with text "item 3"
        onData(allOf(is(instanceOf(String.class)), is("2011"))).perform(click());


        // Uses {@link Espresso#onData(org.hamcrest.Matcher)} to get a reference to a specific
        // gridview item and clicks it.
        onData(anything()).inAdapterView(withId(R.id.rvYearlyWithImageList)).atPosition(1).perform(click());

        // Checks that the OrderActivity opens with the correct tea name displayed
        onView(withId(R.id.tvYear)).check(matches(withText("2013")));


        onView(withId(R.id.rvYearlyWithImageList))
                .perform(RecyclerViewActions.actionOnHolderItem(atPosition(View.VISIBLE), click()));
    }

//    public static Matcher<RecyclerView.ViewHolder> withItemSubject(final String subject) {
//        Checks.checkNotNull(subject);
//        return new BoundedMatcher<RecyclerView.ViewHolder, ItemDataWIthImageAdapter.ViewHolder>(
//                ItemDataWIthImageAdapter.ViewHolder.class) {
//
//            @Override
//            protected boolean matchesSafely(ItemDataWIthImageAdapter.ViewHolder viewHolder) {
//                TextView subjectTextView = viewHolder.itemView.findViewById(R.id.tvDescription);
//
//                return subjectTextView.getVisibility() == View.VISIBLE;
//            }
//
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("item with subject: " + subject);
//            }
//        };
//    }

    public static Matcher<RecyclerView.ViewHolder> atPosition(final int visibility) {

        return new BoundedMatcher<RecyclerView.ViewHolder, ItemDataWIthImageAdapter.ViewHolder>(
                ItemDataWIthImageAdapter.ViewHolder.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("has item with the visibility of " + visibility + ": ");
            }

            @Override
            protected boolean matchesSafely(ItemDataWIthImageAdapter.ViewHolder viewHolder) {
                TextView subjectTextView = viewHolder.itemView.findViewById(R.id.tvDescription);

                return subjectTextView.getVisibility() == visibility;
            }
        };
    }

}
