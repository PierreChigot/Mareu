package com.pierre.mareu.ui.meeting.meeting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pierre.mareu.R;
import com.pierre.mareu.di.DI;
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.service.MeetingAPIService;
import com.pierre.mareu.ui.meeting.meeting_list.ListMeetingActivity;
import com.pierre.mareu.ui.meeting.meeting_list.ListMeetingViewModel;
import com.pierre.mareu.ui.meeting.meeting_list.ViewModelFactory;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class MeetingDetailsActivity extends AppCompatActivity {
    private FloatingActionButton mcloseMeetingDetailFloatingActionButton;
    private Button msaveMeetingButton;
    private TextView mDateTextView;
    private EditText mmeetingNameEditText;
    private TextView mdateEditTextView;
    private TextView mbeginTimeEditTextView;
    private TextView mendTimeEditTextView;
    private Spinner mmeetingRoomsSpinner;
    private AutoCompleteTextView mparticipantsAutoCompleteTextView;
    private ChipGroup mparticipantsChipGroup;
    private Button maddParticipantButton;
    private String mmeetingName;
    private String mmeetingRoom;
    private String mparticipants;
    private String mdateFormatted = "";
    private String mendTimeFormatted = "";
    private String mbeginTimeFormatted = "";
    private LocalDate mdate;
    private LocalTime mbeginTime;
    protected LocalDateTime mdateTime;
    private LocalTime mendTime;

    private Meeting mMeeting;
    private int mYear, mMonth, mDay, mbeginHour, mbeginMinutes, mendHour, mendMinutes;

    private MeetingDetailsViewModel mMeetingDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meeting_details);
        mcloseMeetingDetailFloatingActionButton = findViewById(R.id.close_meetingDetail_floatingActionButton);
        msaveMeetingButton = findViewById(R.id.saveMetting_button);
        mDateTextView = findViewById(R.id.date_textView);
        mmeetingNameEditText = findViewById(R.id.meetingName_editText);
        mmeetingNameEditText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        mdateEditTextView = findViewById(R.id.dateEdit_TextView);
        mbeginTimeEditTextView = findViewById(R.id.beginTimeEdit_TextView);
        mendTimeEditTextView = findViewById(R.id.endTimeEdit_TextView);
        mmeetingRoomsSpinner = findViewById(R.id.spinner);
        mparticipantsAutoCompleteTextView = findViewById(R.id.participant_autoCompleteTextView);
        mparticipantsChipGroup = findViewById(R.id.chipGroup);
        maddParticipantButton = findViewById(R.id.addParticipant_button);

        mdateEditTextView.setShowSoftInputOnFocus(false);
        mbeginTimeEditTextView.setShowSoftInputOnFocus(false);

        mcloseMeetingDetailFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //Get the ViewModel
        mMeetingDetailsViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MeetingDetailsViewModel.class);
        mMeetingDetailsViewModel.getViewActionMutableLiveData().observe(this, new Observer<ViewAction>() {
            @Override
            public void onChanged(ViewAction viewAction) {
                switch (viewAction) {
                    case OK:
                        finish();
                        break;
                    case DISPLAY_ERROR:
                        //TODO toast
                        break;

                }
            }
        });

        msaveMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newMeeting();


            }
        });
        mdateEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MeetingDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mdate = LocalDate.of(year, month + 1, day);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd MMMM yyyy", Locale.FRANCE);
                        mdateFormatted = mdate.format(formatter);
                        mdateEditTextView.setText(mdateFormatted);
                        mdateEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));
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
                        mbeginTimeFormatted = mbeginTime.format(formatter);
                        mbeginTimeEditTextView.setText(mbeginTimeFormatted);
                        mbeginTimeEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));
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
                        mendTimeFormatted = mendTime.format(formatter);
                        mendTimeEditTextView.setText(mendTimeFormatted);
                        mendTimeEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));
                    }
                }, mendHour, mendMinutes, true);
                timePickerDialog.show();
            }
        });
        if (mbeginTime != null && mendTime != null) {
            if (mendTime.isBefore(mbeginTime)) {
                Toast.makeText(MeetingDetailsActivity.this, "L'heure de fin de la réunion doit être après l'heure de début"
                        , Toast.LENGTH_LONG).show();
                mendTimeEditTextView.setTextColor(getResources().getColor(R.color.errorColor));
            } else {
                mendTimeEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        }
        //Spinner to choose meeting room :
        ArrayList<String> meetingRooms = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.meeting_rooms_arrays)));
        meetingRooms.add(0, getString(R.string.choose_meetingRoom));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, meetingRooms) {
            @Override
            public boolean isEnabled(int position) {
                // Disable the first item from Spinner
                // First item will be use for hint
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                    tv.setTextSize(12);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        mmeetingRoomsSpinner.setAdapter(adapter);
        mmeetingRoomsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedMeetingRoom = (String) adapterView.getItemAtPosition(position);

                if (position > 0) {
                    TextView tv = (TextView) view;
                    tv.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //AutocompleteTextView + chips to add the participants :
        ArrayAdapter<CharSequence> adapterParticipants = ArrayAdapter.createFromResource(this,
                R.array.participants_arrays, android.R.layout.simple_dropdown_item_1line);
        mparticipantsAutoCompleteTextView.setAdapter(adapterParticipants);
        maddParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mparticipantsAutoCompleteTextView.getText() != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mparticipantsAutoCompleteTextView.getWindowToken(), 0);

                    String participant;
                    participant = mparticipantsAutoCompleteTextView.getText().toString();
                    // ensure it's an e-mail
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(participant).matches() || participant.isEmpty()) {
                        Toast.makeText(MeetingDetailsActivity.this, R.string.its_not_an_email_message
                                , Toast.LENGTH_SHORT).show();
                    } else {
                        final Chip chip = new Chip(mparticipantsChipGroup.getContext());
                        chip.setText(participant);
                        chip.setChipIcon(getDrawable(R.drawable.ic_person_pin_black_18dp));
                        chip.setCheckable(false);
                        chip.setClickable(true);
                        chip.setCloseIconVisible(true);


                        mparticipantsChipGroup.addView(chip);
                        chip.setOnCloseIconClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mparticipantsChipGroup.removeView(chip);
                            }
                        });
                    }

                }
                mparticipantsAutoCompleteTextView.setText("");
            }
        });
    }

    private void newMeeting() {
        //we recover all the values when we click on the save button and
        //The name of the meeting
        String meetingName = mmeetingNameEditText.getText().toString();

        //The date and time
        LocalDateTime dateTimeBegin = LocalDateTime.of(mdate, mbeginTime);
        LocalDateTime dateTimeEnd = LocalDateTime.of(mdate,mendTime);

        //The name of the meeting room
        String meetingRoom = mmeetingRoomsSpinner.getSelectedItem().toString();

        //The list of participants
        List<String> participants = new ArrayList<String>();
        for (int j = 0; j < mparticipantsChipGroup.getChildCount(); j++) {
            Chip chip = (Chip) mparticipantsChipGroup.getChildAt(j);
            participants.add(chip.getText().toString());
        }
        mMeetingDetailsViewModel.addMeeting(meetingName, meetingRoom, dateTimeBegin, dateTimeEnd,participants );

    }
}
