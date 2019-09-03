package com.pierre.mareu.di;

import com.pierre.mareu.service.DummyMeetingAPIService;
import com.pierre.mareu.service.MeetingAPIService;

/**
 * Created by Pierre Chigot
 */
public class DI {
    private static final MeetingAPIService serviceMeeting = new DummyMeetingAPIService();

    /**
     * Get an instance on @{@link MeetingAPIService}
     */
    public static MeetingAPIService getMeetingApiService() {
        return serviceMeeting;
    }


    /**
     * Get always a new instance on @{@link MeetingAPIService}. Useful for tests, so we ensure the context is clean.
     */
    public static MeetingAPIService getNewInstanceApiService() {
        return new DummyMeetingAPIService();
    }
}
