package com.pierre.mareu.ui.meeting.meeting_list;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pierre.mareu.R;
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.ui.meeting.meeting_list.MeetingFragment.OnListFragmentInteractionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Meeting} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;
    private final OnListFragmentInteractionListener mListener;

    public MyMeetingRecyclerViewAdapter(List<Meeting> meetings, OnListFragmentInteractionListener listener) {
        mMeetings = meetings;
        mListener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        assert mMeetings != null;
        Meeting meeting = mMeetings.get(position);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        String date = dateFormat.format(calendar.getTime());



        holder.mMeetingTextView.setText(meeting.getName() + " - " + date + " - " + meeting.getMeetingRoom());
        holder.mParticipantsTextView.setText(meeting.getParticipants());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mMeeting);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mMeetingTextView;
        private final TextView mParticipantsTextView;

        private Meeting mMeeting;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mMeetingTextView = view.findViewById(R.id.meeting_name_meeting_list);
            mParticipantsTextView = view.findViewById(R.id.participants_email_meeting_list);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mMeetingTextView.getText() + "'";
        }
    }
}
