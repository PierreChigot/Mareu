package com.pierre.mareu.ui.meeting;

import androidx.annotation.Nullable;

import org.threeten.bp.LocalDateTime;

/**
 * Created by Pierre Chigot
 */
public class MeetingUIModel {
    private final int id;
    private final String name;
    private final String date;
    private final String participants;
    private final String meetingRoom;

    public MeetingUIModel(int id, String name, String dateTime, String participants, String meetingRoom) {
        this.id = id;
        this.name = name;
        this.date = dateTime;
        this.participants = participants;
        this.meetingRoom = meetingRoom;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getParticipants() {
        return participants;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingUIModel that = (MeetingUIModel) o;
        return id == that.id &&
                name.equals(that.name) && date.equals(that.date)
                && participants.equals(that.participants) && meetingRoom.equals(that.meetingRoom);

    }
}
