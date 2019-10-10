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


    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();


    @Test
    public void getUiModelsLiveData() {
        MeetingAPIService service = DI.getNewInstanceApiService();

        ListMeetingViewModel viewModel = new ListMeetingViewModel(service);

        assertNotNull(viewModel.getUiModelsLiveData().getValue());
        assertEquals(2,viewModel.getUiModelsLiveData().getValue().get(0).getId());
    }

    @Test
    public void refresh() {
        MeetingAPIService service = DI.getNewInstanceApiService();
        ListMeetingViewModel viewModel = new ListMeetingViewModel(service);

        //viewModel.getUiModelsLiveData().observeForever(observer);
        LocalDateTime dateTimeMeeting0 = LocalDateTime.of(2019, 11, 25, 10, 0);
        LocalDateTime dateTimeEndMeeting0 = LocalDateTime.of(2019, 11, 25, 10, 45);
        Meeting meetingToAdd = new Meeting(12,"meetingToAdd",
                dateTimeMeeting0, dateTimeEndMeeting0,"Salle", "meetingTestRoom");
        service.addMeeting(meetingToAdd);
        viewModel.refresh();
        assertNotNull(viewModel.getUiModelsLiveData().getValue());
        assertEquals(12, viewModel.getUiModelsLiveData().getValue().get(5).getId());

    }

    @Test
    public void sortByPlace() {
        MeetingAPIService service = DI.getNewInstanceApiService();
        ListMeetingViewModel viewModel = new ListMeetingViewModel(service);
        viewModel.sortByPlace();
        assertNotNull(viewModel.getUiModelsLiveData().getValue());
        assertEquals("Salle 2", viewModel.getUiModelsLiveData().getValue().get(0).getMeetingRoom());

    }

    @Test
    public void sortByDate() {
        MeetingAPIService service = DI.getNewInstanceApiService();
        ListMeetingViewModel viewModel = new ListMeetingViewModel(service);
        assertNotNull(viewModel.getUiModelsLiveData().getValue());
        assertEquals("24/10 09:00", viewModel.getUiModelsLiveData().getValue().get(0).getDate());
    }

    @Test
    public void deleteMeeting() {
        MeetingAPIService service = DI.getNewInstanceApiService();
        ListMeetingViewModel viewModel = new ListMeetingViewModel(service);
        Meeting meetingToDelete = service.getMeetings().get(0);
        assertNotNull(viewModel.getUiModelsLiveData().getValue());
        MeetingUIModel meetingUIModel = viewModel.getUiModelsLiveData().getValue().get(0);
        viewModel.deleteMeeting(meetingToDelete.getId());
        assertFalse(service.getMeetings().contains(meetingToDelete));
        assertFalse(viewModel.getUiModelsLiveData().getValue().contains(meetingUIModel));

    }
}