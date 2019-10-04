package com.pierre.mareu.Utils;


import com.pierre.mareu.ui.meeting.MeetingUIModel;

import java.util.Comparator;

public class MeetingSort {
    public static final Comparator<MeetingUIModel> COMPARATOR_ROOM = new Comparator<MeetingUIModel>() {

        @Override
        public int compare(MeetingUIModel meeting1, MeetingUIModel meeting2) {

            return meeting1.getMeetingRoom().replaceAll("[^\\p{ASCII}]", "").
                    compareTo(meeting2.getMeetingRoom().replaceAll("[^\\p{ASCII}]", ""));
        }
    };

    public static final Comparator<MeetingUIModel> COMPARATOR_DATE = new Comparator<MeetingUIModel>() {

        @Override
        public int compare(MeetingUIModel meeting1, MeetingUIModel meeting2) {

            return meeting1.getDate().compareTo(meeting2.getDate());
        }


    };
}
