package com.pierre.mareu.model;

import org.threeten.bp.LocalDateTime;

/**
 * Created by Pierre Chigot
 */
public class Meeting {

    /** Identifier */
    private Integer id;

    /** Name */
    private String name;

    /** Date and time of meeting's beginning*/
    private LocalDateTime dateTimeBegin;

    /** time of meeting's end */
    private LocalDateTime dateTimeEnd;


    /** List participants */

    private String participants;


    /** Meeting room*/

    private String meetingRoom;


    public Meeting(Integer id, String name, LocalDateTime dateTimeBegin, LocalDateTime dateTimeEnd, String meetingRoom, String participants) {
        this.id = id;
        this.name = name;
        this.dateTimeBegin = dateTimeBegin;
        this.dateTimeEnd = dateTimeEnd;
        this.participants = participants;
        this.meetingRoom = meetingRoom;


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTimeBegin() {
        return dateTimeBegin;
    }

    public void setDateTimeBegin(LocalDateTime dateTime) {
        this.dateTimeBegin = dateTime;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }


    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }
}
