package com.pierre.mareu.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pierre.mareu.model.Meeting;

import java.util.List;

public class MeetingUtils {
        @Nullable
        public static Meeting getMeetingFromId(@NonNull List<Meeting> meetings, int id){
            for (Meeting meeting : meetings) {
                if (meeting.getId() == id){
                    return meeting;
                }
            }
            return null;

        }

}
