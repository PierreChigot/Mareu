package com.pierre.mareu.ui.meeting.meeting_list;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pierre.mareu.R;
import com.pierre.mareu.ui.meeting.MeetingUIModel;
import com.pierre.mareu.ui.meeting.meeting.MeetingDetailsActivity;

import java.util.List;


public class ListMeetingActivity extends AppCompatActivity implements ListMeetingAdapter.OnDeleteButtonListener, ListMeetingAdapter.OnItemClickedListener {


    private ListMeetingViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        final ListMeetingAdapter adapter = new ListMeetingAdapter(this, this);
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        FloatingActionButton mAddMeetingFloatingActionButton = findViewById(R.id.add_meeting_floatingActionButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAddMeetingFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rankingActivityIntent = new Intent(ListMeetingActivity.this, MeetingDetailsActivity.class);
                startActivity(rankingActivityIntent);
            }
        });


        //Get the ViewModel
        mViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(ListMeetingViewModel.class);

        // Create the observer which updates the UI.
        mViewModel.getUiModelsLiveData().observe(this, new Observer<List<MeetingUIModel>>() {
            @Override
            public void onChanged(List<MeetingUIModel> meetingUIModels) {
                adapter.submitList(meetingUIModels);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.refresh();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idItem = item.getItemId();
        if (idItem == R.id.sort_date){
            item.setChecked(true);
            Toast.makeText(ListMeetingActivity.this, R.string.by_date
                    , Toast.LENGTH_LONG).show();
            mViewModel.sortByDate();
        }else if (idItem == R.id.sort_place) {
            item.setChecked(true);
            Toast.makeText(ListMeetingActivity.this, R.string.by_place
                    , Toast.LENGTH_LONG).show();
            mViewModel.sortByPlace();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }
    @Override
    public void onDeleteMeeting(int meetingId) {

        mViewModel.deleteMeeting(meetingId);
    }

    @Override
    public void onItemClicked(int meetingId) {
        Intent rankingActivityIntent = new Intent(ListMeetingActivity.this, MeetingDetailsActivity.class);
        rankingActivityIntent.putExtra("ID",meetingId);
        startActivity(rankingActivityIntent);

    }
}
