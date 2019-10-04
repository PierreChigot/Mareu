package com.pierre.mareu.Utils;

import com.pierre.mareu.model.Meeting;


import org.junit.Test;
import org.threeten.bp.LocalDateTime;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MeetingRoomUtilsTest {

    @Test
    public void meetingRoomIsAlreadyReserved() {

        LocalDateTime dateTimeMeeting0 = LocalDateTime.of(2019, 10, 25, 10, 0);
        LocalDateTime dateTimeEndMeeting0 = LocalDateTime.of(2019, 10, 25, 10, 45);
        LocalDateTime dateTimeMeeting1 = LocalDateTime.of(2019, 10, 26, 18, 0);
        LocalDateTime dateTimeEndMeeting1 = LocalDateTime.of(2019, 10, 26, 19, 0);
        LocalDateTime dateTimeMeeting2 = LocalDateTime.of(2019, 10, 24, 9, 0);
        LocalDateTime dateTimeEndMeeting2 = LocalDateTime.of(2019, 10, 24, 9, 30);
        LocalDateTime dateTimeMeeting3 = LocalDateTime.of(2019, 10, 28, 15, 0);
        LocalDateTime dateTimeEndMeeting3 = LocalDateTime.of(2019, 10, 28, 15, 45);
        LocalDateTime dateTimeMeeting4 = LocalDateTime.of(2019, 10, 29, 14, 30);
        LocalDateTime dateTimeEndMeeting4 = LocalDateTime.of(2019, 10, 29, 15, 30);

        final List<Meeting> meetings = Arrays.asList(
                new Meeting(0, "Réunion A", dateTimeMeeting0, dateTimeEndMeeting0,
                        "Salle 2", "toto@smiley.fr"),
                new Meeting(1, "Réunion B", dateTimeMeeting1, dateTimeEndMeeting1,
                        "Salle 3", "zaphod@beltegeuse.com" + "dark.vador@starwars.gl" + "toto@smiley.fr"),
                new Meeting(2, "Réunion C", dateTimeMeeting2, dateTimeEndMeeting2,
                        "Salle 4", "zaphod@beltegeuse.com"),
                new Meeting(3, "Réunion D", dateTimeMeeting3, dateTimeEndMeeting3,
                        "Salle 5", "sarah.connor@nofate.us"),
                new Meeting(4, "Réunion E", dateTimeMeeting4, dateTimeEndMeeting4,
                        "Salle 6", "vincent.team@wallet.biz")
        );
        //1.the both meetings are in the same time in the same room so "isReserved" must be true :
        LocalDateTime dateTimeBegin = LocalDateTime.of(2019, 10, 25, 10, 0);
        LocalDateTime dateTimeEnd = LocalDateTime.of(2019, 10, 25, 11, 45);
        boolean isReserved = MeetingRoomUtils.MeetingRoomIsAlreadyReserved(meetings, dateTimeBegin, dateTimeEnd, "Salle 2");
        assertTrue(isReserved);
        //2.the meeting begin during another meeting so "isReserved2" must be true :
        LocalDateTime dateTimeBegin2 = LocalDateTime.of(2019, 10, 26, 18, 30);
        LocalDateTime dateTimeEnd2 = LocalDateTime.of(2019, 10, 26, 19, 32);
        boolean isReserved2 = MeetingRoomUtils.MeetingRoomIsAlreadyReserved(meetings, dateTimeBegin2, dateTimeEnd2, "Salle 3");
        assertTrue(isReserved2);
        //3.the meeting is not at the same time but in the same meetingroom so "isReserved2" must be false :
        LocalDateTime dateTimeBegin3 = LocalDateTime.of(2019, 10, 24, 18, 30);
        LocalDateTime dateTimeEnd3 = LocalDateTime.of(2019, 10, 24, 19, 0);
        boolean isReserved3 = MeetingRoomUtils.MeetingRoomIsAlreadyReserved(meetings, dateTimeBegin3, dateTimeEnd3, "Salle 4");
        assertFalse(isReserved3);
        //4.the meeting is at the same time but not in the same room so "isReserved2" must be false :
        LocalDateTime dateTimeBegin4 = LocalDateTime.of(2019, 10, 28, 15, 5);
        LocalDateTime dateTimeEnd4 = LocalDateTime.of(2019, 10, 28, 15, 30);
        boolean isReserved4 = MeetingRoomUtils.MeetingRoomIsAlreadyReserved(meetings, dateTimeBegin4, dateTimeEnd4, "Salle 4");
        assertFalse(isReserved4);
    }
}