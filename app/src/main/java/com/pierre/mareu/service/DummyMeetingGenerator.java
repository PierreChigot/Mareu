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
    public static final List<String> participants = Arrays.asList(
            "arthur.dent@mercure.com",
            "trillian@jupiter.fr",
            "princesse.leila@star.wars"

    );

    public static final Date date = new Date();


    public static final List<Meeting> DUMMY_MEETINGS = Arrays.asList(
          new Meeting(1,"Zaphod's meeting",date, participants, "Sujet","Salle 1" ),
          new Meeting(2,"Zapho's meeting",date, participants, "Sujet", "Salle 2"),
          new Meeting(3,"Zaph's meeting",date, participants, "Sujet","Salle 3" ),
          new Meeting(4,"Zap's meeting",date, participants, "Sujet","Salle 4" ),
          new Meeting(5,"Za's meeting",date, participants, "Sujet","Salle 5" ),
          new Meeting(6,"Z's meeting",date, participants, "Sujet", "Salle 6")
    );
    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
