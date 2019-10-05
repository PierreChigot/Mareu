package com.pierre.mareu.ui.meeting.meeting_list;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.pierre.mareu.di.DI;
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.service.MeetingAPIService;
import com.pierre.mareu.ui.meeting.MeetingUIModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class ListMeetingViewModelTest {

   @Mock Observer<List<MeetingUIModel>> observer;


    private MeetingAPIService service = DI.getMeetingApiService();

    private MutableLiveData<List<MeetingUIModel>> mUiModelsLiveData = new MutableLiveData<>();
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();


    @Before

    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getUiModelsLiveData() {
        ListMeetingViewModel viewModel = new ListMeetingViewModel(service);
        assertEquals(2,viewModel.getUiModelsLiveData().getValue().get(0).getId());
    }

    @Test
    public void refresh() {
        ListMeetingViewModel viewModel = new ListMeetingViewModel(service);

        viewModel.getUiModelsLiveData().observeForever(observer);
        LocalDateTime dateTimeMeeting0 = LocalDateTime.of(2019, 11, 25, 10, 0);
        LocalDateTime dateTimeEndMeeting0 = LocalDateTime.of(2019, 11, 25, 10, 45);
        Meeting meetingToAdd = new Meeting(12,"meetingToAdd",
                dateTimeMeeting0, dateTimeEndMeeting0,"zSalle", "meetingTestRoom");
        service.addMeeting(meetingToAdd);
        viewModel.refresh();
        assertEquals(12, Objects.requireNonNull(viewModel.getUiModelsLiveData().getValue()).get(5).getId());
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