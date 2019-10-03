package com.pierre.mareu.ui.meeting.meeting;



import androidx.lifecycle.LiveData;

import androidx.lifecycle.ViewModel;


import com.pierre.mareu.Utils.IdUtils;
import com.pierre.mareu.Utils.MeetingRoomUtils;
import com.pierre.mareu.Utils.MeetingUtils;
import com.pierre.mareu.Utils.SingleLiveEvent;
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.service.MeetingAPIService;

import org.threeten.bp.LocalDateTime;

import java.util.List;

/**
 * Created by Pierre Chigot
 */
public class MeetingDetailsViewModel extends ViewModel {
    private MeetingAPIService mMeetingAPIService;

    public LiveData<ViewAction> getViewActionMutableLiveData() {
        return mViewActionMutableLiveData;
    }

    private SingleLiveEvent<ViewAction> mViewActionMutableLiveData = new SingleLiveEvent<>();

    public MeetingDetailsViewModel(MeetingAPIService meetingAPIService) {
        mMeetingAPIService = meetingAPIService;
    }

    protected void saveMeeting(String meetingName,
                               String meetingRoom,
                               LocalDateTime dateTimeBegin,
                               LocalDateTime dateTimeEnd,
                               List<String> participants,
                               int meetingId) {
        Meeting meeting;
        if (meetingName.isEmpty() || meetingRoom.isEmpty() || participants.isEmpty() || dateTimeBegin == null){
            mViewActionMutableLiveData.setValue(ViewAction.DISPLAY_ERROR);
        }else if ((meetingId == -1) && MeetingRoomUtils.MeetingRoomIsAlreadyReserved(mMeetingAPIService.getMeetings(),
                dateTimeBegin,dateTimeEnd,meetingRoom)){
            mViewActionMutableLiveData.setValue(ViewAction.DISPLAY_ERROR_MEETING_ROOM);
        } else {
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
                //we remove the meeting and replace with the edited meeting
                //TODO
                MeetingUtils.getMeetingFromId(mMeetingAPIService.getMeetings(),meetingId);

                meeting = new Meeting(meetingId,meetingName, dateTimeBegin, dateTimeEnd , meetingRoom, listParticipants);

            }






            mMeetingAPIService.addMeeting(meeting);
            mViewActionMutableLiveData.setValue(ViewAction.OK);
        }


    }

    public Meeting editMeeting(int meetingId) {

        return MeetingUtils.getMeetingFromId(mMeetingAPIService.getMeetings(),meetingId);

    }
}
