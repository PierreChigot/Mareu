package com.pierre.mareu.ui.meeting.meeting_list;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pierre.mareu.R;
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.ui.meeting.meeting.MeetingActivity;


public class ListMeetingActivity extends AppCompatActivity implements MeetingFragment.OnListFragmentInteractionListener {

    private FloatingActionButton mAddMeetingFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        mAddMeetingFloatingActionButton = findViewById(R.id.add_meeting_floatingActionButton);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MeetingFragment.newInstance();

        mAddMeetingFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rankingActivityIntent = new Intent(ListMeetingActivity.this, MeetingActivity.class);
                startActivity(rankingActivityIntent);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public void onListFragmentInteraction(Meeting meeting) {

    }
}
