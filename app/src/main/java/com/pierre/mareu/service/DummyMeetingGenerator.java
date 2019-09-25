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
    private static int id;
    private static String meetingName;
    private static LocalDateTime dateMeeting;
    private static String participant;
    private static String meetingRoom;


    private static String participants;
    public static String getMeetingRoom() {
        return meetingRoom;
    }
    public static String getMeetingName() {
        return meetingName;

    }public static String getParticipants() {
        return participants;
    }
    public static LocalDateTime getDateTimeMeeting(){

        return dateMeeting;
    }
    public  static int getid(){

        return id;
    }
    public static final List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(0, "Réunion A", dateMeeting, "toto@smiley.fr",
                    "Salle 2"),
            new Meeting(2, "Réunion B", dateMeeting, "zaphod@beltegeuse.com" + "dark.vador@starwars.gl" + "toto@smiley.fr",
                    "Salle 3"),
            new Meeting(3, "Réunion C", dateMeeting, "zaphod@beltegeuse.com",
                    "Salle 4"),
            new Meeting(4, "Réunion D", dateMeeting, "sarah.connor@nofate.us",
                    "Salle 5"),
            new Meeting(5, "Réunion E", dateMeeting,"vincent.team@wallet.biz" ,
                    "Salle 6")


    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}
