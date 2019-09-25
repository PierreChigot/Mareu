package com.pierre.mareu.ui.meeting.meeting_list;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pierre.mareu.di.DI;
import com.pierre.mareu.service.DummyMeetingAPIService;
import com.pierre.mareu.service.MeetingAPIService;
import com.pierre.mareu.ui.meeting.meeting.MeetingDetailsViewModel;

/**
 * Created by Pierre Chigot
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory sFactory;

    private final MeetingAPIService mMeetingAPIService;

    public ViewModelFactory(MeetingAPIService meetingAPIService) {
        this.mMeetingAPIService = meetingAPIService;
    }
    public static ViewModelFactory getInstance() {
        if (sFactory == null) {
            synchronized (ViewModelFactory.class) {
                if (sFactory == null) {
                    sFactory = new ViewModelFactory(DI.getMeetingApiService());
                }
            }
        }

        return sFactory;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListMeetingViewModel.class)){
            return (T) new ListMeetingViewModel(mMeetingAPIService);
        }
        else if (modelClass.isAssignableFrom(MeetingDetailsViewModel.class)){
            return (T) new MeetingDetailsViewModel(mMeetingAPIService);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
