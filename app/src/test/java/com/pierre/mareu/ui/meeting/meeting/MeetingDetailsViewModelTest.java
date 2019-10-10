package com.pierre.mareu.ui.meeting.meeting;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.pierre.mareu.di.DI;
import com.pierre.mareu.model.Meeting;

import com.pierre.mareu.service.MeetingAPIService;


import org.junit.Rule;
import org.junit.Test;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;


public class MeetingDetailsViewModelTest {


    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();



    @Test
    public void saveMeetingWithSuccess() {

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

    }
    @Test
    public void shouldNotSaveMeetingIfNoMeetingName() {

        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel = new MeetingDetailsViewModel(service);
        List<String> participants = new ArrayList<>();
        participants.add("tao@citedor.com");
        participants.add("vincent@team.fr");

        meetingDetailsViewModel.saveMeeting("", "le palace",
                LocalDateTime.of(2019, 12, 25, 12, 0),
                LocalDateTime.of(2019, 12, 25, 12, 30), participants, 50);


        assertEquals(ViewAction.DISPLAY_ERROR,meetingDetailsViewModel.getViewActionMutableLiveData().getValue());
    }
    @Test
    public void shouldNotSaveMeetingIfNoMeetingBeginDate() {

        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel = new MeetingDetailsViewModel(service);
        List<String> participants = new ArrayList<>();
        participants.add("tao@citedor.com");
        participants.add("vincent@team.fr");

        meetingDetailsViewModel.saveMeeting("Test name", "le palace",
                null,
                LocalDateTime.of(2019, 12, 25, 12, 30), participants, 50);


        assertEquals(ViewAction.DISPLAY_ERROR,meetingDetailsViewModel.getViewActionMutableLiveData().getValue());
    }
    @Test
    public void shouldNotSaveMeetingIfNoMeetingEndDate() {

        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel = new MeetingDetailsViewModel(service);
        List<String> participants = new ArrayList<>();
        participants.add("tao@citedor.com");
        participants.add("vincent@team.fr");

        meetingDetailsViewModel.saveMeeting("Test name", "le palace",
                LocalDateTime.of(2019, 12, 25, 12, 0),
               null, participants, 50);


        assertEquals(ViewAction.DISPLAY_ERROR,meetingDetailsViewModel.getViewActionMutableLiveData().getValue());
    }
    @Test
    public void shouldNotSaveMeetingIfNoParticipant() {

        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel = new MeetingDetailsViewModel(service);
        List<String> participants = new ArrayList<>();


        meetingDetailsViewModel.saveMeeting("Test name", "le palace",
                LocalDateTime.of(2019, 12, 25, 12, 0),
                LocalDateTime.of(2019, 12, 25, 12, 30), participants, 50);


        assertEquals(ViewAction.DISPLAY_ERROR,meetingDetailsViewModel.getViewActionMutableLiveData().getValue());
    }
    @Test
    public void shouldNotSaveMeetingIfTheEndTimeIsBeforeTheBeginTime() {

        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel = new MeetingDetailsViewModel(service);
        List<String> participants = new ArrayList<>();
        participants.add("tao@citedor.com");
        participants.add("vincent@team.fr");


        meetingDetailsViewModel.saveMeeting("Test name", "le palace",
                LocalDateTime.of(2019, 12, 25, 12, 0),
                LocalDateTime.of(2019, 12, 25, 11, 30), participants, 50);


        assertEquals(ViewAction.DISPLAY_ERROR_TIME,meetingDetailsViewModel.getViewActionMutableLiveData().getValue());
    }
    @Test
    public void shouldNotSaveMeetingIfTheMeetingRoomIsAlreadyReserved() {

        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel = new MeetingDetailsViewModel(service);
        List<String> participants = new ArrayList<>();
        participants.add("tao@citedor.com");
        participants.add("vincent@team.fr");


        meetingDetailsViewModel.saveMeeting("Test name", "Salle 6",
                LocalDateTime.of(2019, 10, 29, 14, 30),
                LocalDateTime.of(2019, 10, 29, 15, 0), participants, -1);


        assertEquals(ViewAction.DISPLAY_ERROR_MEETING_ROOM,meetingDetailsViewModel.getViewActionMutableLiveData().getValue());
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