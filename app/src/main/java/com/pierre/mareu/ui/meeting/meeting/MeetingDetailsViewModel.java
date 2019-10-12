package com.pierre.mareu.ui.meeting.meeting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pierre.mareu.utils.IdUtils;
import com.pierre.mareu.utils.MeetingRoomUtils;
import com.pierre.mareu.utils.SingleLiveEvent;
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.service.MeetingAPIService;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;
import java.util.Locale;

/**
 * Created by Pierre Chigot
 */
public class MeetingDetailsViewModel extends ViewModel {
    private final MeetingAPIService mMeetingAPIService;

    private final SingleLiveEvent<ViewAction> mViewActionMutableLiveData = new SingleLiveEvent<>();

    LiveData<ViewAction> getViewActionMutableLiveData() {
        return mViewActionMutableLiveData;
    }


    public MeetingDetailsViewModel(MeetingAPIService meetingAPIService) {
        mMeetingAPIService = meetingAPIService;
    }
    void saveMeeting(String meetingName,
                     String meetingRoom,
                     LocalDateTime dateTimeBegin,
                     LocalDateTime dateTimeEnd,
                     List<String> participants,
                     int meetingId) {
        Meeting meeting;

        if (meetingName.isEmpty() || meetingRoom.equals("error")|| participants.isEmpty()
                || dateTimeBegin == null || dateTimeEnd == null){
            mViewActionMutableLiveData.setValue(ViewAction.DISPLAY_ERROR);
        }else if ((meetingId == -1) && MeetingRoomUtils.MeetingRoomIsAlreadyReserved(mMeetingAPIService.getMeetings(),
                dateTimeBegin,dateTimeEnd,meetingRoom)){
            mViewActionMutableLiveData.setValue(ViewAction.DISPLAY_ERROR_MEETING_ROOM);
        } else if (dateTimeBegin.isAfter(dateTimeEnd)){
            mViewActionMutableLiveData.setValue(ViewAction.DISPLAY_ERROR_TIME);
        }
        else {
            String listParticipants;
            StringBuilder stringBuilder = new StringBuilder();
            String prefix = "";
            for (String participant : participants) {
                stringBuilder.append(prefix);
                prefix = ", ";
                stringBuilder.append(participant);
            }
            listParticipants = stringBuilder.toString();
            int id = IdUtils.SetId(mMeetingAPIService);

            if (meetingId == -1){
                meeting = new Meeting(id, meetingName, dateTimeBegin, dateTimeEnd , meetingRoom, listParticipants);
            }else {

                mMeetingAPIService.deleteMeeting(meetingId);
                meeting = new Meeting(meetingId,meetingName, dateTimeBegin, dateTimeEnd , meetingRoom, listParticipants);
            }
            mMeetingAPIService.addMeeting(meeting);
            mViewActionMutableLiveData.setValue(ViewAction.OK);
        }
    }
    String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd MMMM yyyy", Locale.FRANCE);
        return date.format(formatter);
    }

    String formatTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.FRANCE);
        return time.format(formatter);
    }
    Meeting getMeetingFromId(int id){
        List<Meeting> meetings = mMeetingAPIService.getMeetings();
        for (Meeting meeting : meetings) {
            if (meeting.getId() == id){
                return meeting;
            }
        }
        return null;
    }

}
