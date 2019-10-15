package com.pierre.mareu.service;

import com.pierre.mareu.model.Meeting;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pierre Chigot
 */
abstract class DummyMeetingGenerator {
    private static final LocalDateTime dateTimeMeeting0 = LocalDateTime.of(2019,12,25,10,00);
    private static final LocalDateTime dateTimeEndMeeting0 = LocalDateTime.of(2019,12,25,10,45);
    private static final LocalDateTime dateTimeMeeting1 = LocalDateTime.of(2019,12,26,18,00);
    private static final LocalDateTime dateTimeEndMeeting1 = LocalDateTime.of(2019,12,26,19,00);
    private static final LocalDateTime dateTimeMeeting2 = LocalDateTime.of(2019,12,24,9,00);
    private static final LocalDateTime dateTimeEndMeeting2 = LocalDateTime.of(2019,12,24,9,30);
    private static final LocalDateTime dateTimeMeeting3 = LocalDateTime.of(2019,12,28,15,00);
    private static final LocalDateTime dateTimeEndMeeting3 = LocalDateTime.of(2019,12,28,15,45);
    private static final LocalDateTime dateTimeMeeting4 = LocalDateTime.of(2019,12,29,14,30);
    private static final LocalDateTime dateTimeEndMeeting4 = LocalDateTime.of(2019,12,29,15,30);

    public static final List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(0, "Réunion A", dateTimeMeeting0, dateTimeEndMeeting0,
                    "Salle 2", "toto@smiley.fr"),
            new Meeting(1, "Réunion B", dateTimeMeeting1, dateTimeEndMeeting1,
                    "Salle 3", "zaphod@beltegeuse.com, dark.vador@starwars.gl, toto@smiley.fr"),
            new Meeting(2, "Réunion C", dateTimeMeeting2, dateTimeEndMeeting2,
                    "Salle 4", "zaphod@beltegeuse.com"),
            new Meeting(3, "Réunion D", dateTimeMeeting3, dateTimeEndMeeting3,
                    "Salle 5", "sarah.connor@nofate.us"),
            new Meeting(4, "Réunion E", dateTimeMeeting4, dateTimeEndMeeting4,
                    "Salle 6", "vincent.team@wallet.biz")


    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}
