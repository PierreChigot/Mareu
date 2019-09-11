package com.pierre.mareu.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pierre.mareu.model.Meeting;

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
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }
}
