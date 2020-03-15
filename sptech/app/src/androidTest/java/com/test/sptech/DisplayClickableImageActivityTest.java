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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DisplayClickableImageActivityTest {

    @Rule
    public ActivityTestRule<DisplayClickableImageActivity> mActivityTestRule =
            new ActivityTestRule<>(DisplayClickableImageActivity.class);

    @Test
    public void clickTheArrowImage_PromptPopUp(){

        onView(withId(R.id.rvYearlyWithImageList))
                .perform(RecyclerViewActions.actionOnHolderItem(atPosition(View.VISIBLE), click()));
    }

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
