package com.pierre.mareu.service;

import com.pierre.mareu.model.Meeting;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Pierre Chigot
 */
abstract class DummyMeetingGenerator {
    private static int id;
    private static String name;
    private static LocalDateTime dateMeeting;
    private static String participant;
    private static String meetingRoom;
    private static List<String> meetingRooms = new ArrayList<String>() {{
        add("Salle 1");
        add("Salle 2");
        add("Salle 3");
        add("Salle 4");
        add("Salle 5");
        add("Salle 6");
        add("Salle 7");
        add("Salle 8");
        add("Salle 9");
        add("Salle 10");

    }};
    private static List<String> meetingName = new ArrayList<String>() {{
        add("Réunion A");
        add("Réunion B");
        add("Réunion C");
        add("Réunion D");
        add("Réunion E");
        add("Réunion F");
        add("Réunion G");
        add("Réunion H");
        add("Réunion I");
        add("Réunion J");
        add("Réunion K");
    }};
    private static List<String> participants = new ArrayList<String>() {{
        add("toto@smiley.fr");
        add("dark.vador@starwars.gl");
        add("zaphod@beltegeuse.com");
        add("sarah.connor@nofate.us");
        add("claudi@dikenek.be");
        add("alain.terieur@carambar.fr");
        add("alex.terieur@carambar.fr");
        add("vincent.team@wallet.biz");

    }};
    public static String getMeetingRoom() {
        return meetingRooms.get(new Random().nextInt(meetingRooms.size()));
    }
    public static String getMeetingName() {
        return meetingName.get(new Random().nextInt(meetingName.size()));

    }public static String getParticipants() {
        return participants.get(new Random().nextInt(participants.size()));
    }
    public static LocalDateTime getDateTimeMeeting(){
        Random randomYear = new Random();
        Random randomMonth = new Random();
        Random randomDay = new Random();
        Random randomHour = new Random();
        Random randomMinutes = new Random();
        int year = randomYear.nextInt(2019 - 2025);
        int month = randomMonth.nextInt(1 - 12);
        int day = randomDay.nextInt(1 - 28);
        int hour = randomHour.nextInt(8- 18);
        int minutes = randomMinutes.nextInt(0 - 59);
        dateMeeting = LocalDateTime.of(2019,01,01,00,00);
        dateMeeting.of(year,month,day,hour,minutes);
        return dateMeeting;
    }
    public  static int getid(){
        Random idRandom = new Random();
        id = idRandom.nextInt(1 - 99);
        return id;
    }
    public static final List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(id, meetingName.get(new Random().nextInt(meetingName.size())),
                    dateMeeting, participants.get(new Random().nextInt(participants.size())) ,
                    meetingRooms.get(new Random().nextInt(meetingRooms.size()))),
            new Meeting(id, meetingName.get(new Random().nextInt(meetingName.size())),
                    dateMeeting, participants.get(new Random().nextInt(participants.size())) ,
                    meetingRooms.get(new Random().nextInt(meetingRooms.size()))),
            new Meeting(id, meetingName.get(new Random().nextInt(meetingName.size())),
                    dateMeeting, participants.get(new Random().nextInt(participants.size())) ,
                    meetingRooms.get(new Random().nextInt(meetingRooms.size()))),
            new Meeting(id, meetingName.get(new Random().nextInt(meetingName.size())),
                    dateMeeting, participants.get(new Random().nextInt(participants.size())) + " "+ participants.get(new Random().nextInt(participants.size())),
                    meetingRooms.get(new Random().nextInt(meetingRooms.size())))

    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
    static Meeting generateMeeting(){
        return new Meeting(id, meetingName.get(new Random().nextInt(meetingName.size())),
                dateMeeting, participants.get(new Random().nextInt(participants.size())) ,
                meetingRooms.get(new Random().nextInt(meetingRooms.size())));
    }
}
