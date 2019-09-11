package com.pierre.mareu.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pierre.mareu.model.Meeting;

import java.util.List;

/**
 * Created by Pierre Chigot
 */
public interface MeetingAPIService {
    /**
     * Get all my Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    void addMeeting(Meeting meeting);
    //MutableLiveData<List<Meeting>> getMeetingsLiveData();

    /**
     * Deletes a meeting
     * @param meeting to delete
     */
    void deleteMeeting(Meeting meeting);
}

