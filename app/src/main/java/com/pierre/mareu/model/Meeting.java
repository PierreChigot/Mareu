package com.pierre.mareu.model;

import java.util.Date;
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
    private Date date;

    /** List participants */

    private List<String> participants;

    /** Subject */

    private String subject;


    public Meeting(Integer id, String name, Date date, List participants, String subject) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.participants = participants;
        this.subject = subject;


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
