package com.pierre.mareu.ui.meeting.meeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.pierre.mareu.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.Locale;

public class MeetingDetailsActivity extends AppCompatActivity {
    private TextView mDateTextView;
    private EditText mTimeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        mDateTextView = findViewById(R.id.date_textView);
        mTimeEditText = findViewById(R.id.time_editText);

        //We recover the date from the previous activity and we format as for example : "ven. 27 septembre 2019"
        Intent incomingIntent = getIntent();
        String dateString = incomingIntent.getStringExtra("date");
        LocalDate date = LocalDate.parse(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd MMMM yyyy", Locale.FRANCE);
        String dateFormatted = date.format(formatter);

        mDateTextView.setText(dateFormatted);
    }
}
