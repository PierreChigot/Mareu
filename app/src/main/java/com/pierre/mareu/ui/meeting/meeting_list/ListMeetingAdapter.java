package com.pierre.mareu.ui.meeting.meeting_list;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pierre.mareu.R;
import com.pierre.mareu.ui.meeting.MeetingUIModel;


public class ListMeetingAdapter extends ListAdapter<MeetingUIModel, ListMeetingAdapter.ViewHolder> {


    private final OnDeleteButtonListener onDeleteButtonListener;
    private final OnItemClickedListener onItemClickedListener;

    ListMeetingAdapter(OnDeleteButtonListener listener, OnItemClickedListener onItemClickedListener) {
       super(new DiffCallback());
       onDeleteButtonListener = listener;
        this.onItemClickedListener = onItemClickedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bind(getItem(position), onDeleteButtonListener, onItemClickedListener);


    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mMeetingTextView;
        private final TextView mParticipantsTextView;
        private final TextView mTimeTextView;
        private final TextView mRoomTextView;
        private ImageButton mDeleteImageButton;

        public ViewHolder(View view) {
            super(view);

            mMeetingTextView = view.findViewById(R.id.meeting_name_meeting_list);
            mParticipantsTextView = view.findViewById(R.id.participants_email_meeting_list);
            mTimeTextView = view.findViewById(R.id.time_textView);
            mRoomTextView = view.findViewById(R.id.room_textView);
            mDeleteImageButton = view.findViewById(R.id.meeting_delete_button_meeting_list);


        }
        void bind(final MeetingUIModel model, final OnDeleteButtonListener mListener, final OnItemClickedListener onItemClickedListener){

            mMeetingTextView.setText(model.getName());
            mParticipantsTextView.setText(model.getParticipants());
            mTimeTextView.setText(model.getDate());
            mRoomTextView.setText(model.getMeetingRoom());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickedListener.onItemClicked(model.getId());
                }
            });
            mDeleteImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onDeleteMeeting(model.getId());
                }
            });

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
    public interface OnDeleteButtonListener{
        void onDeleteMeeting(int meetingId);
    }
    public interface OnItemClickedListener{
        void onItemClicked(int meetingId);
    }

}
