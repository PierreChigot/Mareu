package com.pierre.mareu.ui.meeting.meeting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
import com.pierre.mareu.model.Meeting;
import com.pierre.mareu.ui.meeting.meeting_list.ViewModelFactory;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingDetailsActivity extends AppCompatActivity {
    private EditText mMeetingNameEditText;
    private TextView mDateEditTextView;
    private TextView mBeginTimeEditTextView;
    private TextView mEndTimeEditTextView;
    private Spinner mMeetingRoomsSpinner;
    private AutoCompleteTextView mParticipantsAutoCompleteTextView;
    private ChipGroup mParticipantsChipGroup;
    private String mDateFormatted = "";
    private LocalDate mDate;
    private LocalTime mBeginTime;
    private LocalTime mEndTime;
    int mId;
    private int mYear, mMonth, mDay, mbeginHour, mbeginMinutes, mendHour, mendMinutes;
    private MeetingDetailsViewModel mMeetingDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        FloatingActionButton closeMeetingDetailFloatingActionButton = findViewById(R.id.close_meetingDetail_floatingActionButton);
        Button saveMeetingButton = findViewById(R.id.saveMetting_button);
        mMeetingNameEditText = findViewById(R.id.meetingName_editText);
        mMeetingNameEditText.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        mDateEditTextView = findViewById(R.id.dateEdit_TextView);
        mBeginTimeEditTextView = findViewById(R.id.beginTimeEdit_TextView);
        mEndTimeEditTextView = findViewById(R.id.endTimeEdit_TextView);
        mMeetingRoomsSpinner = findViewById(R.id.spinner);
        mParticipantsAutoCompleteTextView = findViewById(R.id.participant_autoCompleteTextView);
        mParticipantsChipGroup = findViewById(R.id.chipGroup);
        Button addParticipantButton = findViewById(R.id.addParticipant_button);
        mDateEditTextView.setShowSoftInputOnFocus(false);
        mBeginTimeEditTextView.setShowSoftInputOnFocus(false);

        closeMeetingDetailFloatingActionButton.setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(MeetingDetailsActivity.this, getString(R.string.error_message)
                                , Toast.LENGTH_LONG).show();
                        break;
                    case DISPLAY_ERROR_MEETING_ROOM:
                        Toast.makeText(MeetingDetailsActivity.this, getString(R.string.error_message_meeting_room)
                                , Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        saveMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMeeting();
            }
        });
        mDateEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MeetingDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mDate = LocalDate.of(year, month + 1, day);
                        mDateFormatted = mMeetingDetailsViewModel.formatDate(mDate);
                        mDateEditTextView.setText(mDateFormatted);
                        mDateEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));
                    }
                }, mYear, mMonth, mDay);
                if (mDateEditTextView.getText().equals("")) {
                    Toast.makeText(MeetingDetailsActivity.this, "lmdateEditTextView.getText()" + mDateEditTextView.getText()
                            , Toast.LENGTH_LONG).show();

                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                } else {
                    Toast.makeText(MeetingDetailsActivity.this, "lmdateEditTextView.getText()" + mDateEditTextView.getText()
                            , Toast.LENGTH_LONG).show();
                    //TODO set the date of the date picker at the date of the textview

                }
                datePickerDialog.show();
            }
        });
        mBeginTimeEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MeetingDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
                        mBeginTime = LocalTime.of(hour, minutes);
                        mBeginTimeEditTextView.setText(mMeetingDetailsViewModel.formatTime(mBeginTime));
                        mBeginTimeEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));
                    }
                }, mbeginHour, mbeginMinutes, true);
                timePickerDialog.show();
            }
        });
        mEndTimeEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MeetingDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
                        mEndTime = LocalTime.of(hour, minutes);
                        mEndTimeEditTextView.setText(mMeetingDetailsViewModel.formatTime(mEndTime));
                        mEndTimeEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));
                    }
                }, mendHour, mendMinutes, true);
                timePickerDialog.show();
            }
        });
        if (mBeginTime != null && mEndTime != null) {
            if (mEndTime.isBefore(mBeginTime)) {
                Toast.makeText(MeetingDetailsActivity.this, "L'heure de fin de la réunion doit être après l'heure de début"
                        , Toast.LENGTH_LONG).show();
                mEndTimeEditTextView.setTextColor(getResources().getColor(R.color.errorColor));
            } else {
                mEndTimeEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        }
        //Spinner to choose meeting room :
        ArrayList<String> meetingRooms = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.meeting_rooms_arrays)));
        meetingRooms.add(0, getString(R.string.choose_meetingRoom));
        // Disable the first item from Spinner
        // First item will be use for hint
        // Set the hint text color gray
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, meetingRooms) {
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
        mMeetingRoomsSpinner.setAdapter(spinnerAdapter);
        mMeetingRoomsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
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
        mParticipantsAutoCompleteTextView.setAdapter(adapterParticipants);
        addParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mParticipantsAutoCompleteTextView.getText() != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(mParticipantsAutoCompleteTextView.getWindowToken(), 0);

                    String participant;
                    participant = mParticipantsAutoCompleteTextView.getText().toString();
                    // ensure it's an e-mail
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(participant).matches() || participant.isEmpty()) {
                        Toast.makeText(MeetingDetailsActivity.this, R.string.its_not_an_email_message
                                , Toast.LENGTH_SHORT).show();
                    } else {
                        final Chip chip = addChip(participant);
                        mParticipantsChipGroup.addView(chip);
                        chip.setOnCloseIconClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mParticipantsChipGroup.removeView(chip);
                            }
                        });
                    }
                }
                mParticipantsAutoCompleteTextView.setText("");
            }
        });
        //if we are on the case where we edit a meeting :
        mId = getIntent().getIntExtra("ID", -1);
        if (mId != -1) {
            Toast.makeText(MeetingDetailsActivity.this, "l'id est  : " + mId
                    , Toast.LENGTH_LONG).show();
            Meeting meeting = mMeetingDetailsViewModel.getMeetingFromId(mId);
            String mMeetingName = meeting.getName();
            LocalDateTime mMeetingBeginLocalDateTime = meeting.getDateTimeBegin();
            LocalDateTime mMeetingEndLocalDateTime = meeting.getDateTimeEnd();
            String mParticipants = meeting.getParticipants();

            mMeetingNameEditText.setText(mMeetingName);
            int position = spinnerAdapter.getPosition(meeting.getMeetingRoom());
            mMeetingRoomsSpinner.setSelection(position);

            mDate = mMeetingBeginLocalDateTime.toLocalDate();
            mDateEditTextView.setText(mMeetingDetailsViewModel.formatDate(mDate));
            mDateEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));

            mBeginTime = mMeetingBeginLocalDateTime.toLocalTime();
            mBeginTimeEditTextView.setText(mBeginTime.toString());
            mBeginTimeEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));

            mEndTime = mMeetingEndLocalDateTime.toLocalTime();
            mEndTimeEditTextView.setText(mEndTime.toString());
            mEndTimeEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));

            String[] parts = mParticipants.split(", ");
            for (String part : parts) {
                final Chip chip = addChip(part);
                mParticipantsChipGroup.addView(chip);
                chip.setOnCloseIconClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mParticipantsChipGroup.removeView(chip);
                    }
                });

            }
        }
    }
    private void saveMeeting() {

        //we recover all the values when we click on the save button and
        //The name of the meeting
        String meetingName = mMeetingNameEditText.getText().toString();

        //The date and time
        LocalDateTime dateTimeBegin = LocalDateTime.of(mDate, mBeginTime);
        LocalDateTime dateTimeEnd = LocalDateTime.of(mDate, mEndTime);

        //The name of the meeting room
        String meetingRoom = mMeetingRoomsSpinner.getSelectedItem().toString();

        //The list of participants
        List<String> participants = new ArrayList<>();
        for (int j = 0; j < mParticipantsChipGroup.getChildCount(); j++) {
            Chip chip = (Chip) mParticipantsChipGroup.getChildAt(j);
            participants.add(chip.getText().toString());
        }
        mMeetingDetailsViewModel.saveMeeting(meetingName, meetingRoom, dateTimeBegin, dateTimeEnd, participants, mId);
    }
    private Chip addChip(String participant) {
        final Chip chip = new Chip(mParticipantsChipGroup.getContext());
        chip.setText(participant);
        chip.setChipIcon(getDrawable(R.drawable.ic_person_pin_black_18dp));
        chip.setCheckable(false);
        chip.setClickable(true);
        chip.setCloseIconVisible(true);
        return chip;
    }
}
