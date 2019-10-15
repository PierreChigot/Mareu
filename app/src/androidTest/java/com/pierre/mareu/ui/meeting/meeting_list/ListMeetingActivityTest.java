package com.pierre.mareu.ui.meeting.meeting_list;

import android.app.Instrumentation;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.pierre.mareu.R;
import com.pierre.mareu.ui.meeting.meeting.MeetingDetailsActivity;
import com.pierre.mareu.ui.meeting.meeting_list.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.pierre.mareu.ui.meeting.meeting_list.utils.RecyclerViewItemCountAssertion.withItemCount;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.allOf;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class ListMeetingActivityTest {
    @Rule
    public final ActivityTestRule<ListMeetingActivity> mActivityRule = new ActivityTestRule<>(ListMeetingActivity.class);

    @Before
    public void setUp()  {
        ListMeetingActivity activity = mActivityRule.getActivity();

        assertThat(activity, notNullValue());
    }
    /**
     * We ensure that our recyclerview is displaying all the meeting which are in the meeting API
     */
    @Test
    public void myMeetingList_displayTheMeetings() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list))
                .check(matches(hasMinimumChildCount(5)));
    }
    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myMeetingsList_deleteAction_shouldRemoveItem() throws InterruptedException {
        // Get items count before action
        int ITEMS_COUNT = 5;
        onView(ViewMatchers.withId(R.id.list)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 4
        ITEMS_COUNT = ITEMS_COUNT - 1;
        Thread.sleep(500);
        onView(ViewMatchers.withId(R.id.list)).check(withItemCount(ITEMS_COUNT));
    }


    @Test
    public void myMeetingList_byDefaultIsSortedByDate_shouldDisplayTheRightItemAtTheFirstPlace() {
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.list);
        TextView textView1 = recyclerView.getChildAt(0).findViewById(R.id.time_textView);
        Assert.assertEquals("24/12 09:00",textView1.getText());
        TextView textView2 = recyclerView.getChildAt(1).findViewById(R.id.time_textView);
        Assert.assertEquals("25/12 10:00",textView2.getText());
        TextView textView3 = recyclerView.getChildAt(2).findViewById(R.id.time_textView);
        Assert.assertEquals("26/12 18:00",textView3.getText());
        TextView textView4 = recyclerView.getChildAt(3).findViewById(R.id.time_textView);
        Assert.assertEquals("28/12 15:00",textView4.getText());
        TextView textView5 = recyclerView.getChildAt(4).findViewById(R.id.time_textView);
        Assert.assertEquals("29/12 14:30",textView5.getText());

    }
    @Test
    public void myMeetingList_onOptionsItemSelectedSortByPlace_shouldDisplayTheRightItemAtTheFirstPlace() throws InterruptedException {

        ViewInteraction overflowMenuButton2 = onView(
                allOf(withContentDescription("ActionButtonSorting"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton2.perform(click());
        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title), withText("Par lieu"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());
        Thread.sleep(500);
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.list);
        TextView textView1 = recyclerView.getChildAt(0).findViewById(R.id.room_textView);
        Assert.assertEquals("Salle 2",textView1.getText());
        TextView textView2 = recyclerView.getChildAt(1).findViewById(R.id.room_textView);
        Assert.assertEquals("Salle 3",textView2.getText());
        TextView textView3 = recyclerView.getChildAt(2).findViewById(R.id.room_textView);
        Assert.assertEquals("Salle 4",textView3.getText());
        TextView textView4 = recyclerView.getChildAt(3).findViewById(R.id.room_textView);
        Assert.assertEquals("Salle 5",textView4.getText());
        TextView textView5 = recyclerView.getChildAt(4).findViewById(R.id.room_textView);
        Assert.assertEquals("Salle 6",textView5.getText());
    }


    /**
     * When we click an item, we have the details activity displayed with the right meeting name
     */
    @Test
    public void myMeetingList_onItemClicked_shouldDisplayTheDetailsActivityWithTheRightName() {
        //Given : We are waiting MeetingDetailsActivity
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(MeetingDetailsActivity.class.getName(), null, false);
        //When perform a click on the item at position 2 we save the name of the item in the "expectedText"
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.list);
        TextView textView = recyclerView.getChildAt(2).findViewById(R.id.meeting_name_meeting_list);
        String expectedText = textView.getText().toString();
        onView(ViewMatchers.withId(R.id.list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

        //Then : MeetingDetailsActivity is launched
        MeetingDetailsActivity meetingDetailsActivity = (MeetingDetailsActivity) activityMonitor.waitForActivity();
        assertNotNull(meetingDetailsActivity);
        //and the name in the Edittext is corresponding to the expected text
        EditText editText;
        editText = meetingDetailsActivity.findViewById(R.id.meetingName_editText);
        assertEquals(expectedText,editText.getText().toString());


    }
    @Test
    public void myMeetingDetails_ifWeModifyAMeeting_shouldDisplayTheGoodMeeting() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(MeetingDetailsActivity.class.getName(), null, false);
        //When we click on a meeting
        onView(allOf(withId(R.id.list), isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        //The meeting's name in the meeting's details is corresponding
        ViewInteraction name = onView(allOf(withId(R.id.meetingName_editText),isDisplayed()));
        name.check(matches(withText("Réunion C")));
        //The meeting's date in the meeting's details is corresponding
        ViewInteraction date = onView(allOf(withId(R.id.dateEdit_TextView),isDisplayed()));
        date.check(matches(withText("mar. 24 décembre 2019")));
        //The meeting's begin time in the meeting's details is corresponding
        ViewInteraction beginTime = onView(allOf(withId(R.id.beginTimeEdit_TextView),isDisplayed()));
        beginTime.check(matches(withText("09:00")));
        //The meeting's begin time in the meeting's details is corresponding
        ViewInteraction endTime = onView(allOf(withId(R.id.endTimeEdit_TextView),isDisplayed()));
        endTime.check(matches(withText("09:30")));
        //The meeting's room in the meeting's details is corresponding
        MeetingDetailsActivity meetingDetailsActivity = (MeetingDetailsActivity) activityMonitor.waitForActivity();
        Spinner spinner = meetingDetailsActivity.findViewById(R.id.spinner);
        String room = spinner.getSelectedItem().toString();
        assertEquals("Salle 4",room);
        //The meeting's participants in the meeting's details is corresponding
        ChipGroup chipGroup= meetingDetailsActivity.findViewById(R.id.chipGroup);
        Chip chip = (Chip) chipGroup.getChildAt(0);
        String participant = chip.getText().toString();
        assertEquals("zaphod@beltegeuse.com",participant);

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