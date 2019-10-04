package com.pierre.mareu.service;

import com.pierre.mareu.di.DI;
import com.pierre.mareu.model.Meeting;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.LocalDateTime;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MeetingAPIServiceTest {

    private MeetingAPIService service;

    @Before
    public void setUp() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetings() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void addMeeting() {
        Meeting meetingToAdd = new Meeting(10,"Potion magique",
                LocalDateTime.of(2019, 12, 25, 10, 0),
                LocalDateTime.of(2019, 10, 25, 11, 0),
                "panoramix's house", "asterix@village.fr");
        service.addMeeting(meetingToAdd);
        assertTrue(service.getMeetings().contains(meetingToAdd));
    }

    @Test
    public void deleteMeeting() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete.getId());
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }
}