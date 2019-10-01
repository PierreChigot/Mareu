package com.pierre.mareu.Utils;

import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.service.MeetingAPIService;
import java.util.List;

/**
 * Created by Pierre Chigot
 */
public class IdUtils {

    public static int SetId(MeetingAPIService meetingAPIService){
        int id = -1;
        List<Meeting> meetings = meetingAPIService.getMeetings();

        for (Meeting meeting :meetings) {
            if (id < meeting.getId())
                id = meeting.getId();
        }


        return id + 1;
    }
}