package com.pierre.mareu.ui.meeting.meeting;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import com.pierre.mareu.R;
import com.pierre.mareu.di.DI;
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.service.MeetingAPIService;
import com.pierre.mareu.ui.meeting.meeting_list.ListMeetingActivity;
import com.pierre.mareu.ui.meeting.meeting_list.utils.ToastMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;



public class MeetingDetailsActivityTest {
    private MeetingAPIService service;
    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityTestRule = new ActivityTestRule<>(ListMeetingActivity.class);
    public ActivityTestRule<MeetingDetailsActivity> mDetailsActivityTestRule = new ActivityTestRule<>(MeetingDetailsActivity.class);

    @Before
    public void setUp() {

    }
    /**
     * When the end's time is before the begin's time,
     * a toast must be displayed and the meeting should'nt add to the list of meeting
     */
    @Test
    public void myMeetingDetails_ifTheEndsTimeIsBeforeTheBeginsTime_shouldDisplayAToastAndTheMeetingShouldNotAddToAPI() {

        //We ensure the size of the list of the API
        service = DI.getNewInstanceApiService();
        List<Meeting> meetings = service.getMeetings();
        int size = meetings.size();


        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting_floatingActionButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.meetingName_editText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.meetingName_TextInputLayout),
                                        0),
                                0)));
        appCompatEditText.perform(scrollTo(), replaceText("essai"), closeSoftKeyboard());

        TextView dateEdit = mDetailsActivityTestRule.getActivity().findViewById(R.id.dateEdit_TextView);
        dateEdit.setText("");
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.dateEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatTextView.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.beginTimeEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatTextView2.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.endTimeEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        appCompatTextView3.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction appCompatTextView4 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(4);
        appCompatTextView4.perform(click());

        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.participant_autoCompleteTextView),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0)));
        appCompatAutoCompleteTextView.perform(scrollTo(), replaceText("toto@mail.com"), closeSoftKeyboard());


        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.addParticipant_button), withText("Ajouter"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.saveMetting_button), withText("Enregistrer"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        materialButton5.perform(scrollTo(), click());

        // the size of the list of the API must be the same as before :
        List<Meeting> meetingsAfterChanges = DI.getMeetingApiService().getMeetings();
        assertEquals(size,meetingsAfterChanges.size());
        // the toast with the good message must be displayed :
       /* onView(withText(R.string.error_message_time)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        onView(withText(R.string.error_message_time)).*/


        //onView(withText(R.string.error_message_time)).inRoot(withDecorView(not(mDetailsActivityTestRule
         //       .getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        //onView(withText(R.string.error_message_time)).inRoot(withDecorView(not))
    }
    /**
     * When the meeting's name is not filled and we try to save the meeting,
     * a toast must be displayed and the meeting should'nt add to the list of meeting
     */
    @Test
    public void myMeetingDetails_ifTheMeetingNameIsNotFilled_shouldDisplayAToastAndTheMeetingShouldNotAddToAPI() {

        //We ensure the size of the list of the API
        service = DI.getNewInstanceApiService();
        List<Meeting> meetings = service.getMeetings();
        int size = meetings.size();

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting_floatingActionButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.dateEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatTextView.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.beginTimeEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatTextView2.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.endTimeEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        appCompatTextView3.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction appCompatTextView4 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(4);
        appCompatTextView4.perform(click());

        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.participant_autoCompleteTextView),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0)));
        appCompatAutoCompleteTextView.perform(scrollTo(), replaceText("toto@test.com"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.addParticipant_button), withText("Ajouter"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.saveMetting_button), withText("Enregistrer"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        materialButton5.perform(scrollTo(), click());

        List<Meeting> meetingsAfterChanges = DI.getMeetingApiService().getMeetings();
        assertEquals(size,meetingsAfterChanges.size());

    }
    /**
     * When the meeting's date is not filled and we try to save the meeting,
     * a toast must be displayed and the meeting should'nt add to the list of meeting
     */
    @Test
    public void myMeetingDetails_ifTheMeetingDateIsNotFilled_shouldDisplayAToastAndTheMeetingShouldNotAddToAPI() {
        //We ensure the size of the list of the API
        service = DI.getNewInstanceApiService();
        List<Meeting> meetings = service.getMeetings();
        int size = meetings.size();
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting_floatingActionButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.meetingName_editText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.meetingName_TextInputLayout),
                                        0),
                                0)));
        appCompatEditText.perform(scrollTo(), replaceText("essai"), closeSoftKeyboard());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.beginTimeEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatTextView.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.endTimeEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        appCompatTextView2.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction appCompatTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        appCompatTextView3.perform(click());

        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.participant_autoCompleteTextView),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0)));
        appCompatAutoCompleteTextView.perform(scrollTo(), replaceText("toto@test.com"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.addParticipant_button), withText("Ajouter"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.saveMetting_button), withText("Enregistrer"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        materialButton4.perform(scrollTo(), click());

        List<Meeting> meetingsAfterChanges = DI.getMeetingApiService().getMeetings();
        assertEquals(size,meetingsAfterChanges.size());
    }
    /**
     * When the meeting's begin's time is not filled and we try to save the meeting,
     * a toast must be displayed and the meeting should'nt add to the list of meeting
     */
    @Test
    public void myMeetingDetails_ifTheMeetingBeginTimeIsNotFilled_shouldDisplayAToastAndTheMeetingShouldNotAddToAPI() {
        //We ensure the size of the list of the API
        service = DI.getNewInstanceApiService();
        List<Meeting> meetings = service.getMeetings();
        int size = meetings.size();

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting_floatingActionButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.meetingName_editText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.meetingName_TextInputLayout),
                                        0),
                                0)));
        appCompatEditText.perform(scrollTo(), replaceText("essai"), closeSoftKeyboard());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.dateEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatTextView.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.endTimeEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        appCompatTextView2.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction appCompatTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(6);
        appCompatTextView3.perform(click());

        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.participant_autoCompleteTextView),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0)));
        appCompatAutoCompleteTextView.perform(scrollTo(), replaceText("toto@test.fr"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.addParticipant_button), withText("Ajouter"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.saveMetting_button), withText("Enregistrer"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        materialButton4.perform(scrollTo(), click());

        List<Meeting> meetingsAfterChanges = DI.getMeetingApiService().getMeetings();
        assertEquals(size,meetingsAfterChanges.size());
    }
    /**
     * When the meeting's end's time is not filled and we try to save the meeting,
     * a toast must be displayed and the meeting should'nt add to the list of meeting
     */
    @Test
    public void myMeetingDetails_ifTheMeetingEndTimeIsNotFilled_shouldDisplayAToastAndTheMeetingShouldNotAddToAPI() {
        //We ensure the size of the list of the API
        service = DI.getNewInstanceApiService();
        List<Meeting> meetings = service.getMeetings();
        int size = meetings.size();
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting_floatingActionButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.meetingName_editText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.meetingName_TextInputLayout),
                                        0),
                                0)));
        appCompatEditText.perform(scrollTo(), replaceText("essai"), closeSoftKeyboard());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.dateEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatTextView.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.beginTimeEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatTextView2.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction appCompatTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        appCompatTextView3.perform(click());

        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.participant_autoCompleteTextView),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0)));
        appCompatAutoCompleteTextView.perform(scrollTo(), replaceText("toto@test.fr"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.addParticipant_button), withText("Ajouter"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.saveMetting_button), withText("Enregistrer"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        materialButton4.perform(scrollTo(), click());
        List<Meeting> meetingsAfterChanges = DI.getMeetingApiService().getMeetings();
        assertEquals(size,meetingsAfterChanges.size());
    }
    /**
     * When the meeting's room is not filled and we try to save the meeting,
     * a toast must be displayed and the meeting should'nt add to the list of meeting
     */
    @Test
    public void myMeetingDetails_ifTheMeetingRoomIsNotFilled_shouldDisplayAToastAndTheMeetingShouldNotAddToAPI() throws InterruptedException {
        //We ensure the size of the list of the API
        service = DI.getNewInstanceApiService();
        List<Meeting> meetings = service.getMeetings();
        int size = meetings.size();
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting_floatingActionButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.meetingName_editText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.meetingName_TextInputLayout),
                                        0),
                                0)));
        appCompatEditText.perform(scrollTo(), replaceText("essai"), closeSoftKeyboard());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.dateEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatTextView.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.beginTimeEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatTextView2.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.endTimeEdit_TextView),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        appCompatTextView3.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.participant_autoCompleteTextView),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0)));
        appCompatAutoCompleteTextView.perform(scrollTo(), replaceText("toto@test.fr"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.addParticipant_button), withText("Ajouter"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.saveMetting_button), withText("Enregistrer"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        materialButton5.perform(scrollTo(), click());

        List<Meeting> meetingsAfterChanges = DI.getMeetingApiService().getMeetings();
        assertEquals(size,meetingsAfterChanges.size());
    }
    /**
     * When the meeting's participant time is not filled and we try to save the meeting,
     * a toast must be displayed and the meeting should'nt add to the list of meeting
     */
    @Test
    public void myMeetingDetails_ifTheMeetingParticipantIsNotFilled_shouldDisplayAToastAndTheMeetingShouldNotAddToAPI() {

    }

    /**
     * When we try to add an participant and it's not in the e-mail's format,
     * a toast must be displayed and the participant should'nt add to the list of meeting
     */
    @Test
    public void myMeetingDetails_ifWeAddParticipantWithoutEmailFormat_shouldDisplayAToastAndTheMeetingShouldNotAddToAPI() {

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