package com.pierre.mareu.Utils;

import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.ui.meeting.MeetingUIModel;

import java.util.Comparator;

public class MeetingSort {
    public static Comparator<MeetingUIModel> ComparatorRoom = new Comparator<MeetingUIModel>() {

        @Override
        public int compare(MeetingUIModel meeting1, MeetingUIModel meeting2) {

            return meeting1.getMeetingRoom().replaceAll("[^\\p{ASCII}]", "").
                    compareTo(meeting2.getMeetingRoom().replaceAll("[^\\p{ASCII}]", ""));
        }
    };

    public static Comparator<MeetingUIModel> ComparatorDate = new Comparator<MeetingUIModel>() {

        @Override
        public int compare(MeetingUIModel meeting1, MeetingUIModel meeting2) {

            return meeting1.getDate().compareTo(meeting2.getDate());
        }


    };
}
