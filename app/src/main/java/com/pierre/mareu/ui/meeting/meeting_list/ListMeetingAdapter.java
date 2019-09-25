package com.pierre.mareu.ui.meeting.meeting_list;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pierre.mareu.R;
import com.pierre.mareu.ui.meeting.MeetingUIModel;


public class ListMeetingAdapter extends ListAdapter<MeetingUIModel, ListMeetingAdapter.ViewHolder> {


    ListMeetingAdapter() {
       super(new DiffCallback());

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mMeetingTextView;
        private final TextView mParticipantsTextView;
        private final TextView mTimeTextView;
        private final TextView mRoomTextView;

        public ViewHolder(View view) {
            super(view);

            mMeetingTextView = view.findViewById(R.id.meeting_name_meeting_list);
            mParticipantsTextView = view.findViewById(R.id.participants_email_meeting_list);
            mTimeTextView = view.findViewById(R.id.time_textView);
            mRoomTextView = view.findViewById(R.id.room_textView);
        }
        void bind (MeetingUIModel model){

            mMeetingTextView.setText(model.getName());
            mParticipantsTextView.setText(model.getParticipants());
            mTimeTextView.setText(model.getDate());
            mRoomTextView.setText(model.getMeetingRoom());
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mMeetingTextView.getText() + "'";
        }
    }
    private static class DiffCallback extends DiffUtil.ItemCallback<MeetingUIModel> {

        @Override
        public boolean areItemsTheSame(@NonNull MeetingUIModel oldItem, @NonNull MeetingUIModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MeetingUIModel oldItem, @NonNull MeetingUIModel newItem) {
            return oldItem.equals(newItem);
        }
    }
}
