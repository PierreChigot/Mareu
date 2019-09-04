package com.pierre.mareu.ui.meeting.meeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.pierre.mareu.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MeetingDetailsActivity extends AppCompatActivity {
    private TextView mDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        Intent incomingIntent = getIntent();
        mDateTextView = findViewById(R.id.date_textView);
        long date = incomingIntent.getLongExtra("date", -1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd MMMM yyyy", Locale.FRANCE);
        String dateString = dateFormat.format(date);



        mDateTextView.setText(dateString);
    }
}
