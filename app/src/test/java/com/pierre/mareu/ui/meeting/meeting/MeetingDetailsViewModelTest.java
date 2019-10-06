package com.pierre.mareu.ui.meeting.meeting;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;

import com.pierre.mareu.di.DI;
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.service.MeetingAPIService;
import com.pierre.mareu.ui.meeting.MeetingUIModel;
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
import java.util.Objects;

import static com.pierre.mareu.ui.meeting.meeting.ViewAction.OK;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MeetingDetailsViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Mock

    private SingleLiveEvent<Integer> viewActionMutableLiveData = new SingleLiveEvent<>();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);


    }



    @Test
    public void getViewActionMutableLiveData() {
        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel = new MeetingDetailsViewModel(service);
        assertEquals(ViewAction.values().length , meetingDetailsViewModel.getViewActionMutableLiveData().getValue());
    }

    @Test
    public void saveMeeting() {
        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel  = new MeetingDetailsViewModel(service);

        List<String> participants = new ArrayList<>();
        participants.add("tao@citedor.com");
        participants.add("vincent@team.fr");
        LocalDateTime begin = LocalDateTime.of(2019,12,25,12,00);
        LocalDateTime end = LocalDateTime.of(2019,12,25,12,30);
        meetingDetailsViewModel.saveMeeting("Test name", "le palace",LocalDateTime.of(2019,12,25,12,00),
                LocalDateTime.of(2019,12,25,12,30),participants,50);
        int index = service.getMeetings().size() - 1;
        assertEquals(50, service.getMeetings().get(index).getId().intValue());
        assertEquals("Test name", service.getMeetings().get(index).getName());
        assertEquals("le palace", service.getMeetings().get(index).getMeetingRoom());
        assertEquals("tao@citedor.com, vincent@team.fr", service.getMeetings().get(index).getParticipants());
        assertEquals(begin, service.getMeetings().get(index).getDateTimeBegin());
        assertEquals(end,service.getMeetings().get(index).getDateTimeEnd());

        assertEquals(OK,viewActionMutableLiveData.getValue());


    }

    @Test
    public void formatDate() {
        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel  = new MeetingDetailsViewModel(service);
        LocalDate date =  LocalDate.of(2019, 12, 25);
        String formattedDate = meetingDetailsViewModel.formatDate(date);
        assertEquals("mer. 25 décembre 2019",formattedDate);
    }

    @Test
    public void formatTime() {
        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel  = new MeetingDetailsViewModel(service);
        LocalTime time = LocalTime.of (14,20);
        String formattedTime = meetingDetailsViewModel.formatTime(time);
        assertEquals("14:20",formattedTime);
    }

    @Test
    public void getMeetingFromId() {
        MeetingAPIService service = DI.getNewInstanceApiService();
        MeetingDetailsViewModel meetingDetailsViewModel  = new MeetingDetailsViewModel(service);
        Meeting meeting =  meetingDetailsViewModel.getMeetingFromId(0);
        assertEquals(0, meeting.getId().intValue());
    }
}