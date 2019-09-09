package com.pierre.mareu.model;

import org.threeten.bp.LocalDateTime;

import java.util.List;

/**
 * Created by Pierre Chigot
 */
public class Meeting {

    /** Identifier */
    private Integer id;

    /** Name */
    private String name;

    /** Date */
    private LocalDateTime date;

    /** List participants */

    private String participants;


    /** Meeting room*/

    private String meetingRoom;


    public Meeting(Integer id, String name, LocalDateTime dateTime, String participants, String meetingRoom) {
        this.id = id;
        this.name = name;
        this.date = dateTime;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime dateTime) {
        this.date = dateTime;
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
