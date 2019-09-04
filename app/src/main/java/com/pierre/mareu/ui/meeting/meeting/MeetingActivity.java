package com.pierre.mareu.ui.meeting.meeting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import com.pierre.mareu.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.pierre.mareu.service.DummyMeetingGenerator.date;

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
                Calendar calendar = Calendar.getInstance();
                calendar.set(year,month,day);

                long date = calendar.getTimeInMillis();

                Intent intent = new Intent(MeetingActivity.this, MeetingDetailsActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });
    }
}
