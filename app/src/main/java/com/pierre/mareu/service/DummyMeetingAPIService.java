package com.pierre.mareu.service;

import com.pierre.mareu.model.Meeting;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Pierre Chigot
 */
public class DummyMeetingAPIService implements MeetingAPIService{
    private final List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();

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
       /* for (Meeting meeting : mMeetings) {
            if (meeting.getId() == meetingId){
                mMeetings.remove(meeting);
            }

        }*/
        for (Iterator<Meeting> iterator = mMeetings.iterator(); iterator.hasNext(); ) {
            Meeting meeting = iterator.next();
            if (meeting.getId() == meetingId) {
                iterator.remove();
            }
        }

    }
}
