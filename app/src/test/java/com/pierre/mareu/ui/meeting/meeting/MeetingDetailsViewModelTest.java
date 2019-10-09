package com.pierre.mareu.ui.meeting.meeting;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;

import com.pierre.mareu.di.DI;
import com.pierre.mareu.model.Meeting;

import com.pierre.mareu.service.MeetingAPIService;
import com.pierre.mareu.utils.SingleLiveEvent;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MeetingDetailsViewModelTest {


    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);


    }


    @Test
    public void saveMeeting() {

        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel = new MeetingDetailsViewModel(service);
        List<String> participants = new ArrayList<>();
        participants.add("tao@citedor.com");
        participants.add("vincent@team.fr");
        LocalDateTime begin = LocalDateTime.of(2019, 12, 25, 12, 0);
        LocalDateTime end = LocalDateTime.of(2019, 12, 25, 12, 30);

        meetingDetailsViewModel.saveMeeting("Test name", "le palace",
                LocalDateTime.of(2019, 12, 25, 12, 0),
                LocalDateTime.of(2019, 12, 25, 12, 30), participants, 50);
        int index = service.getMeetings().size() - 1;

        assertEquals(50, service.getMeetings().get(index).getId().intValue());
        assertEquals("Test name", service.getMeetings().get(index).getName());
        assertEquals("le palace", service.getMeetings().get(index).getMeetingRoom());
        assertEquals("tao@citedor.com, vincent@team.fr", service.getMeetings().get(index).getParticipants());
        assertEquals(begin, service.getMeetings().get(index).getDateTimeBegin());
        assertEquals(end, service.getMeetings().get(index).getDateTimeEnd());

        assertEquals(ViewAction.OK,meetingDetailsViewModel.getViewActionMutableLiveData().getValue());

        //TODO 3 ??? view action test dans UI test ???


    }
    @Test
    public void shouldNotSaveMeetingIfNoMeetingName() {

        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel = new MeetingDetailsViewModel(service);
        List<String> participants = new ArrayList<>();
        participants.add("tao@citedor.com");
        participants.add("vincent@team.fr");
        LocalDateTime begin = LocalDateTime.of(2019, 12, 25, 12, 0);
        LocalDateTime end = LocalDateTime.of(2019, 12, 25, 12, 30);

        meetingDetailsViewModel.saveMeeting("", "le palace",
                LocalDateTime.of(2019, 12, 25, 12, 0),
                LocalDateTime.of(2019, 12, 25, 12, 30), participants, 50);



        assertEquals(ViewAction.DISPLAY_ERROR,meetingDetailsViewModel.getViewActionMutableLiveData().getValue());

        //TODO 3 ??? view action test dans UI test ???


    }
    @Test
    public void formatDate() {
        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel = new MeetingDetailsViewModel(service);
        LocalDate date = LocalDate.of(2019, 12, 25);
        String formattedDate = meetingDetailsViewModel.formatDate(date);
        assertEquals("mer. 25 décembre 2019", formattedDate);
    }

    @Test
    public void formatTime() {
        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel = new MeetingDetailsViewModel(service);
        LocalTime time = LocalTime.of(14, 20);
        String formattedTime = meetingDetailsViewModel.formatTime(time);
        assertEquals("14:20", formattedTime);
    }

    @Test
    public void getMeetingFromId() {
        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel = new MeetingDetailsViewModel(service);
        Meeting meeting = meetingDetailsViewModel.getMeetingFromId(0);
        assertEquals(0, meeting.getId().intValue());
    }
}