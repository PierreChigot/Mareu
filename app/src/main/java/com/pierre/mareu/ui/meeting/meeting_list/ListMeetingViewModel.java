package com.pierre.mareu.ui.meeting.meeting_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pierre.mareu.R;
import com.pierre.mareu.utils.MeetingSort;
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.service.MeetingAPIService;
import com.pierre.mareu.ui.meeting.MeetingUIModel;

import org.threeten.bp.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


/**
 * Created by Pierre Chigot
 */
class ListMeetingViewModel extends ViewModel {


    private final MeetingAPIService mMeetingAPIService;
    private final MutableLiveData<List<MeetingUIModel>> mUiModelsLiveData = new MutableLiveData<>();
    private boolean mSortByDate = true;

    ListMeetingViewModel(MeetingAPIService meetingAPIService){
       mMeetingAPIService = meetingAPIService;
       refresh();
    }

    LiveData<List<MeetingUIModel>> getUiModelsLiveData() {

        return mUiModelsLiveData;
    }

    void refresh() {
        List<Meeting> updatedMeetings = mMeetingAPIService.getMeetings();
        List<MeetingUIModel> uiModels = new ArrayList<>();
        if (mSortByDate){
            Comparator<Meeting> comparatorDate = MeetingSort.COMPARATOR_DATE;
            Collections.sort(updatedMeetings, comparatorDate);
        }else {
            Comparator<Meeting> comparatorRoom = MeetingSort.COMPARATOR_ROOM;
            Collections.sort(updatedMeetings, comparatorRoom);
        }


        String date = "";
        for (Meeting updatedMeeting : updatedMeetings) {
            if (updatedMeeting.getDateTimeBegin() != null){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm", Locale.FRANCE);
                date = updatedMeeting.getDateTimeBegin().format(formatter);

            }

            int drawableRes = 0;
            switch (updatedMeeting.getMeetingRoom()) {
                case "Salle 1":
                    drawableRes = R.drawable.ic_brightness_1_red_200_24dp;
                    break;
                case "Salle 2":
                    drawableRes = R.drawable.ic_brightness_1_purple_300_24dp;
                    break;
                case "Salle 3":
                    drawableRes = R.drawable.ic_brightness_1_indigo_300_24dp;
                    break;
                case "Salle 4":
                    drawableRes = R.drawable.ic_brightness_1_amber_300_24dp;
                    break;
                case "Salle 5":
                    drawableRes = R.drawable.ic_brightness_1_brown_300_24dp;
                    break;
                case "Salle 6":
                    drawableRes = R.drawable.ic_brightness_1_deep_orange_300_24dp;
                    break;
                case "Salle 7":
                    drawableRes = R.drawable.ic_brightness_1_grey_500_24dp;
                    break;
                case "Salle 8" :
                    drawableRes = R.drawable.ic_brightness_1_light_green_300_24dp;
                    break;
                case "Salle 9" :
                    drawableRes = R.drawable.ic_brightness_1_yellow_300_24dp;
                    break;
                case "Salle 10" :
                    drawableRes = R.drawable.ic_brightness_1_pink_300_24dp;
                    break;
            }
            uiModels.add(new MeetingUIModel(
                    updatedMeeting.getId(),
                    updatedMeeting.getName(),
                    updatedMeeting.getDateTimeBegin() == null? "date nul" : date,
                    updatedMeeting.getParticipants(),
                    updatedMeeting.getMeetingRoom(),
                    drawableRes));
        }



        mUiModelsLiveData.setValue(uiModels);
    }
    void sortByPlace(){
        mSortByDate = false;
        refresh();
    }
    void sortByDate(){
        mSortByDate = true;
        refresh();
    }
    void deleteMeeting(int id) {
        mMeetingAPIService.deleteMeeting(id);
        refresh();
    }


}
