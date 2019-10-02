package com.pierre.mareu.ui.meeting.meeting_list;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.pierre.mareu.Utils.MeetingSort;
import com.pierre.mareu.di.DI;
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.service.MeetingAPIService;
import com.pierre.mareu.ui.meeting.MeetingUIModel;

import org.threeten.bp.LocalDateTime;

import org.threeten.bp.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


/**
 * Created by Pierre Chigot
 */
public class ListMeetingViewModel extends ViewModel {


    private MeetingAPIService mMeetingAPIService;
    private MutableLiveData<List<MeetingUIModel>> mUiModelsLiveData = new MutableLiveData<>();
    private boolean mSortByDate = true;

    public ListMeetingViewModel (MeetingAPIService meetingAPIService){
       mMeetingAPIService = meetingAPIService;
       refresh();
    }

    LiveData<List<MeetingUIModel>> getUiModelsLiveData() {

        return mUiModelsLiveData;
    }

    public void refresh() {
        List<Meeting> updatedMeetings = mMeetingAPIService.getMeetings();
        List<MeetingUIModel> uiModels = new ArrayList<>();

        String date = "";
        for (Meeting updatedMeeting : updatedMeetings) {
            if (updatedMeeting.getDateTimeBegin() != null){

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm", Locale.FRANCE);
                date = updatedMeeting.getDateTimeBegin().format(formatter);
            }

            uiModels.add(new MeetingUIModel(
                    updatedMeeting.getId(),
                    updatedMeeting.getName(),
                    updatedMeeting.getDateTimeBegin() == null? "date nul" : date,
                    updatedMeeting.getParticipants(),
                    updatedMeeting.getMeetingRoom()));
        }
        if (mSortByDate){
            Comparator comparatorDate = MeetingSort.ComparatorDate;
            Collections.sort(uiModels, comparatorDate);
        }else {
            Comparator comparatorRoom = MeetingSort.ComparatorRoom;
            Collections.sort(uiModels, comparatorRoom);
        }

        mUiModelsLiveData.setValue(uiModels);
    }
    public void sortByPlace(){
        mSortByDate = false;
        refresh();
    }
    public void sortByDate(){

        mSortByDate = true;
        refresh();
    }


}
