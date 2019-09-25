package com.pierre.mareu.ui.meeting.meeting;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pierre.mareu.R;
import com.pierre.mareu.Utils.SingleLiveEvent;
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.service.MeetingAPIService;

import org.threeten.bp.LocalDateTime;

import java.util.List;

/**
 * Created by Pierre Chigot
 */
public class MeetingDetailsViewModel extends ViewModel {
    MeetingAPIService mMeetingAPIService;

    public LiveData<ViewAction> getViewActionMutableLiveData() {
        return mViewActionMutableLiveData;
    }

    private SingleLiveEvent<ViewAction> mViewActionMutableLiveData = new SingleLiveEvent<>();

    public MeetingDetailsViewModel(MeetingAPIService meetingAPIService) {
        mMeetingAPIService = meetingAPIService;
    }


    public void addMeeting(String meetingName, String meetingRoom, LocalDateTime dateTime, List<String> participants) {
        if (meetingName.isEmpty() || meetingRoom.isEmpty() || participants.isEmpty() ){
           //TODO
            mViewActionMutableLiveData.setValue(ViewAction.DISPLAY_ERROR);
        }else {
            String listParticipants = "";
            for (String participant : participants) {
                listParticipants += participant;

            }
            Meeting meeting = new Meeting(10, meetingName, dateTime, listParticipants, meetingRoom);

            mMeetingAPIService.addMeeting(meeting);
            mViewActionMutableLiveData.setValue(ViewAction.OK);
        }



    }
}
