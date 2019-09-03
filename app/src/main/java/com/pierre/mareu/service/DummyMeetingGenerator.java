package com.pierre.mareu.service;

import com.pierre.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Pierre Chigot
 */
public abstract class DummyMeetingGenerator {
    public static final List<String> participants = new ArrayList<String>();
    public static final Date date = new Date();


    public static final List<Meeting> DUMMY_MEETINGS = Arrays.asList(
          new Meeting(1,"Zaphod's meeting",date, participants, "Sujet"),
          new Meeting(2,"Zapho's meeting",date, participants, "Sujet"),
          new Meeting(3,"Zaph's meeting",date, participants, "Sujet"),
          new Meeting(4,"Zap's meeting",date, participants, "Sujet"),
          new Meeting(5,"Za's meeting",date, participants, "Sujet"),
          new Meeting(6,"Z's meeting",date, participants, "Sujet")
    );
    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
