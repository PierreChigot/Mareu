package com.pierre.mareu.utils;


import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.ui.meeting.MeetingUIModel;

import java.util.Comparator;

public class MeetingSort {
    /*public static final Comparator<MeetingUIModel> COMPARATOR_ROOM = new Comparator<MeetingUIModel>() {

        @Override
        public int compare(MeetingUIModel meeting1, MeetingUIModel meeting2) {

            return meeting1.getMeetingRoom().replaceAll("[^\\p{ASCII}]", "").
                    compareTo(meeting2.getMeetingRoom().replaceAll("[^\\p{ASCII}]", ""));
        }
    };*/

    /*public static final Comparator<MeetingUIModel> COMPARATOR_DATE = new Comparator<MeetingUIModel>() {

        @Override
        public int compare(MeetingUIModel meeting1, MeetingUIModel meeting2) {

            return meeting1.getDate().compareTo(meeting2.getDate());
        }


    };*/
    public static final Comparator<Meeting> COMPARATOR_DATE = new Comparator<Meeting>() {

        @Override
        public int compare(Meeting meeting1, Meeting meeting2) {
            return   meeting1.getDateTimeBegin().compareTo(meeting2.getDateTimeBegin());


        }
    };
    public static final Comparator<Meeting> COMPARATOR_ROOM = new Comparator<Meeting>() {

        @Override
        public int compare(Meeting meeting1, Meeting meeting2) {
            return meeting1.getMeetingRoom().replaceAll("[^\\p{ASCII}]", "").
                    compareTo(meeting2.getMeetingRoom().replaceAll("[^\\p{ASCII}]", ""));

        }
    };
}
