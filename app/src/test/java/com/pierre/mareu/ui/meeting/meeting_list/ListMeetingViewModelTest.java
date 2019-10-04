package com.pierre.mareu.ui.meeting.meeting_list;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.pierre.mareu.service.MeetingAPIService;
import com.pierre.mareu.ui.meeting.MeetingUIModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ListMeetingViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Before
    public void setUp() throws Exception {

        MeetingAPIService mMeetingAPIService;
        MutableLiveData<List<MeetingUIModel>> mUiModelsLiveData = new MutableLiveData<>();
        boolean mSortByDate = true;


    }


    @Test
    public void getUiModelsLiveData() {
    }

    @Test
    public void refresh() {
    }

    @Test
    public void sortByPlace() {
    }

    @Test
    public void sortByDate() {
    }

    @Test
    public void deleteMeeting() {
    }
}