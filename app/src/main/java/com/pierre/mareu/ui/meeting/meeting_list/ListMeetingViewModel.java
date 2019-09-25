package com.pierre.mareu.ui.meeting.meeting_list;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.pierre.mareu.di.DI;
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.service.MeetingAPIService;
import com.pierre.mareu.ui.meeting.MeetingUIModel;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Pierre Chigot
 */
public class ListMeetingViewModel extends ViewModel {


    private MeetingAPIService mMeetingAPIService;
    private MutableLiveData<List<MeetingUIModel>> mUiModelsLiveData = new MutableLiveData<>();

    public ListMeetingViewModel (MeetingAPIService meetingAPIService){
       mMeetingAPIService = meetingAPIService;
       refresh();
    }

    LiveData<List<MeetingUIModel>> getUiModelsLiveData() {

        return mUiModelsLiveData;
    }
    public void addMeeting(Meeting meeting) {

        LocalDateTime dateEssais = LocalDateTime.of(2019,01,01,00,00);
        Meeting meetingEssais = new Meeting(4, "Essai",dateEssais,"essai@essai.fr", "salle d'essai");

        mMeetingAPIService.addMeeting(meetingEssais);
        refresh();
    }

    public void refresh() {
        List<Meeting> updatedMeetings = mMeetingAPIService.getMeetings();
        List<MeetingUIModel> uiModels = new ArrayList<>();

        for (Meeting updatedMeeting : updatedMeetings) {

            uiModels.add(new MeetingUIModel(
                    updatedMeeting.getId(),
                    updatedMeeting.getName(),
                    updatedMeeting.getDate() == null? "date nul" : updatedMeeting.getDate().toString(),
                    updatedMeeting.getParticipants(),
                    updatedMeeting.getMeetingRoom()));
        }
        mUiModelsLiveData.setValue(uiModels);
    }


}
