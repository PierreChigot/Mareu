package com.pierre.mareu.service;

import com.pierre.mareu.model.Meeting;

import java.util.List;

/**
 * Created by Pierre Chigot
 */
public class DummyMeetingAPIService implements MeetingAPIService{
    private final List<Meeting> Meetings = DummyMeetingGenerator.generateMeetings();
    @Override
    public List<Meeting> getMeetings() {
        return Meetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        Meetings.remove(meeting);
    }
}
