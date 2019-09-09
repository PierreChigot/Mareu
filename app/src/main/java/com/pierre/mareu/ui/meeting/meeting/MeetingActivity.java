package com.pierre.mareu.ui.meeting.meeting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import com.pierre.mareu.R;

import org.threeten.bp.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



public class MeetingActivity extends AppCompatActivity {
    private CalendarView mCalendarView;
    private Calendar mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        mCalendarView = findViewById(R.id.calendarView);

        mCalendarView.setMinDate(new Date().getTime());
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                LocalDate date = LocalDate.of(year, month + 1, day);
                String dateString = date.toString();
                Intent intent = new Intent(MeetingActivity.this, MeetingDetailsActivity.class);
                intent.putExtra("date", dateString);
                startActivity(intent);

            }
        });
    }
}
