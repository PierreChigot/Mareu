package com.pierre.mareu.ui.meeting;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;


/**
 * Created by Pierre Chigot
 */
public class MeetingUIModel {
    private final int id;
    private final String name;
    private final String date;
    private final String participants;
    private final String meetingRoom;
    @ColorRes
    private final int drawableRes;


    public MeetingUIModel(int id, String name, String dateTime, String participants, String meetingRoom, @DrawableRes int drawableRes) {

        this.id = id;
        this.name = name;
        this.date = dateTime;
        this.participants = participants;
        this.meetingRoom = meetingRoom;
        this.drawableRes = drawableRes;
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

    @ColorRes
    public int getDrawableRes() {
        return drawableRes;
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
