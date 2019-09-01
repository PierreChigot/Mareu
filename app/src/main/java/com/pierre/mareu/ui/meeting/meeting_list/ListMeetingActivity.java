package com.pierre.mareu.ui.meeting.meeting_list;

import android.os.Bundle;

import android.view.Menu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import com.pierre.mareu.R;
import com.pierre.mareu.ui.meeting.meeting_list.dummy.DummyContent;



public class ListMeetingActivity extends AppCompatActivity implements MeetingFragment.OnListFragmentInteractionListener {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MeetingFragment.newInstance(1);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
