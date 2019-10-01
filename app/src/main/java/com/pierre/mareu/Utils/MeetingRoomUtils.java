package com.pierre.mareu.Utils;

import com.pierre.mareu.model.Meeting;

import org.threeten.bp.LocalDateTime;

import java.util.List;

/**
 * Created by Pierre Chigot
 */
public class MeetingRoomUtils {
    public static boolean MeetingRoomIsAlreadyReserved(List<Meeting> meetings,
                                                       LocalDateTime dateTimeBegin,
                                                       LocalDateTime dateTimeEnd,
                                                       String meetingRoom){
        boolean isAlreadyReserved = false;

        for (Meeting meeting :meetings) {
            if (meeting.getMeetingRoom().equals(meetingRoom) && !(dateTimeEnd.isBefore(meeting.getDateTimeBegin()) ||
                    meeting.getDateTimeEnd().isBefore(dateTimeBegin))){
                isAlreadyReserved = true;
                break;
            }

        }

        return isAlreadyReserved;
    }
}