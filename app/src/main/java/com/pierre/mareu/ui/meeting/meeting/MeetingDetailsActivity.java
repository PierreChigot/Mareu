package com.pierre.mareu.ui.meeting.meeting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.pierre.mareu.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.Locale;

import static android.app.PendingIntent.getActivity;

public class MeetingDetailsActivity extends AppCompatActivity {
    private TextView mDateTextView;
    private EditText mmeetingNameEditText;
    private TextView mdateEditTextView;
    private TextView mbeginTimeEditTextView;
    private TextView mendTimeEditTextView;
    private Spinner mmeetingRoomsSpinner;
    private Chip mparticipantChip;
    private AutoCompleteTextView mparticipantsAutoCompleteTextView;
    private ChipGroup mparticipantsChipGroup;
    private LocalTime mbeginTime;
    private LocalTime mendTime;



    private int mYear, mMonth, mDay, mbeginHour, mbeginMinutes, mendHour, mendMinutes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        mDateTextView = findViewById(R.id.date_textView);
        mmeetingNameEditText = findViewById(R.id.meetingName_editText);
        mmeetingNameEditText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        mdateEditTextView = findViewById(R.id.dateEdit_TextView);
        mbeginTimeEditTextView = findViewById(R.id.beginTimeEdit_TextView);
        mendTimeEditTextView = findViewById(R.id.endTimeEdit_TextView);
        mmeetingRoomsSpinner = findViewById(R.id.spinner);
        mparticipantsAutoCompleteTextView = findViewById(R.id.participant_autoCompleteTextView);
        mparticipantsChipGroup = findViewById(R.id.chipGroup);


        mdateEditTextView.setShowSoftInputOnFocus(false);
        mbeginTimeEditTextView.setShowSoftInputOnFocus(false);

        mdateEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MeetingDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        LocalDate date = LocalDate.of(year, month + 1, day);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd MMMM yyyy", Locale.FRANCE);
                        String dateFormatted = date.format(formatter);
                        mdateEditTextView.setText(dateFormatted);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        mbeginTimeEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MeetingDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
                        mbeginTime = LocalTime.of(hour, minutes);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.FRANCE);
                        String timeFormatted = mbeginTime.format(formatter);
                        mbeginTimeEditTextView.setText(timeFormatted);
                    }
                }, mbeginHour, mbeginMinutes, true);
                timePickerDialog.show();
            }
        });
        mendTimeEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MeetingDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
                        mendTime = LocalTime.of(hour, minutes);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.FRANCE);
                        String timeFormatted = mendTime.format(formatter);
                        mendTimeEditTextView.setText(timeFormatted);
                        if (mendTime.isBefore(mbeginTime)) {
                            Toast.makeText(MeetingDetailsActivity.this, "L'heure de fin de la réunion doit être après l'heure de début"
                                    , Toast.LENGTH_LONG).show();
                            mendTimeEditTextView.setTextColor(getResources().getColor(R.color.errorColor));
                        } else {
                            mendTimeEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));
                        }
                    }
                }, mendHour, mendMinutes, true);
                timePickerDialog.show();


            }
        });
        //Spinner to choose meeting room :
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meeting_rooms_arrays, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mmeetingRoomsSpinner.setAdapter(adapter);


        //AutocompleteTextView + chips to choose the participants :
        ArrayAdapter<CharSequence> adapterParticipants = ArrayAdapter.createFromResource(this,
                R.array.participants_arrays, android.R.layout.simple_dropdown_item_1line);
        mparticipantsAutoCompleteTextView.setAdapter(adapterParticipants);






    }


}
