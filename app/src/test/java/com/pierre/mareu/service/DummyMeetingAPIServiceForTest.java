package com.pierre.mareu.service;

import com.pierre.mareu.model.Meeting;

import java.util.Iterator;
import java.util.List;

public class DummyMeetingAPIServiceForTest implements MeetingAPIService {

    private final List<Meeting> mMeetings = DummyMeetingGeneratorForTest.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }



    @Override
    public void deleteMeeting(int meetingId) {

        for (Iterator<Meeting> iterator = mMeetings.iterator(); iterator.hasNext(); ) {
            Meeting meeting = iterator.next();
            if (meeting.getId() == meetingId) {
                iterator.remove();
            }
        }

    }
}
