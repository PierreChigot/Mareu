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
    private String mMeetingName;
    private LocalDateTime mMeetingBeginLocalDateTime;
    private LocalDateTime mMeetingEndLocalDateTime;
    private String mMeetingRoom;
    private String mParticipants;
    private String mdateFormatted = "";
    private String mendTimeFormatted = "";
    private String mbeginTimeFormatted = "";
    private LocalDate mdate;
    private LocalTime mbeginTime;
    protected LocalDateTime mdateTime;
    private LocalTime mendTime;
    ArrayAdapter<String> mSpinnerAdapter;
    private boolean mEditingMeeting = false;
    int mId;

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



        msaveMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMeeting(false);
            }
        });
        mdateEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MeetingDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mdate = LocalDate.of(year, month + 1, day);
                        mdateFormatted = formatDate(mdate);
                        mdateEditTextView.setText(mdateFormatted);
                        mdateEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));
                    }
                }, mYear, mMonth, mDay);
                if (mdateEditTextView.getText().equals("")){
                    Toast.makeText(MeetingDetailsActivity.this, "lmdateEditTextView.getText()" + mdateEditTextView.getText()
                            , Toast.LENGTH_LONG).show();

                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                }else {
                    Toast.makeText(MeetingDetailsActivity.this, "lmdateEditTextView.getText()" + mdateEditTextView.getText()
                            , Toast.LENGTH_LONG).show();
                    //TODO set the date of teh date picker at the date of the textview
                    //datePickerDialog.getDatePicker().init(mdate.getYear(),mdate.getMonthValue(),mdate.getDayOfMonth(),datePickerDialog);
                }

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
        mSpinnerAdapter= new ArrayAdapter<String>(this, R.layout.spinner_item, meetingRooms) {
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
                if (position == 0 ) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                    tv.setTextSize(12);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        mmeetingRoomsSpinner.setAdapter(mSpinnerAdapter);
        mmeetingRoomsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        mparticipantsAutoCompleteTextView.setAdapter(adapterParticipants);
        maddParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mparticipantsAutoCompleteTextView.getText() != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(mparticipantsAutoCompleteTextView.getWindowToken(), 0);

                    String participant;
                    participant = mparticipantsAutoCompleteTextView.getText().toString();
                    // ensure it's an e-mail
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(participant).matches() || participant.isEmpty()) {
                        Toast.makeText(MeetingDetailsActivity.this, R.string.its_not_an_email_message
                                , Toast.LENGTH_SHORT).show();
                    } else {
                       final Chip chip = addChip(participant);
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

        //if we are on the case where we edit a meeting :
        mId= getIntent().getIntExtra("ID", -1);
        if (mId != -1){
            Toast.makeText(MeetingDetailsActivity.this, "l'id est  : " + mId
                    , Toast.LENGTH_LONG).show();

            mEditingMeeting = true;

            Meeting meeting = mMeetingDetailsViewModel.editMeeting(mId);
            mMeetingName = meeting.getName();
            mMeetingBeginLocalDateTime = meeting.getDateTimeBegin();
            mMeetingEndLocalDateTime = meeting.getDateTimeEnd();
            mMeetingRoom = meeting.getMeetingRoom();
            mParticipants = meeting.getParticipants();

            mmeetingNameEditText.setText(mMeetingName);
            int position = mSpinnerAdapter.getPosition(meeting.getMeetingRoom());
            mmeetingRoomsSpinner.setSelection(position);

            mdate = mMeetingBeginLocalDateTime.toLocalDate();
            mdateEditTextView.setText(formatDate(mdate));
            mdateEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));

            mbeginTime = mMeetingBeginLocalDateTime.toLocalTime();
            mbeginTimeEditTextView.setText(mbeginTime.toString());
            mbeginTimeEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));

            mendTime = mMeetingEndLocalDateTime.toLocalTime();
            mendTimeEditTextView.setText(mendTime.toString());
            mendTimeEditTextView.setTextColor(getResources().getColor(R.color.colorBlack));

            String[] parts = mParticipants.split(", ");
            for (int i = 0 ; i < parts.length ; i++){
                final Chip chip = addChip(parts[i]);
                mparticipantsChipGroup.addView(chip);
                chip.setOnCloseIconClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mparticipantsChipGroup.removeView(chip);
                    }
                });

            }
        }
    }
    private void saveMeeting(boolean editingMeeting) {

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
        if (editingMeeting){
            mMeetingDetailsViewModel.saveMeeting(meetingName, meetingRoom, dateTimeBegin, dateTimeEnd, participants, mId);
        }else {
            mMeetingDetailsViewModel.saveMeeting(mMeetingName, mMeetingRoom, mMeetingBeginLocalDateTime,
                    mMeetingEndLocalDateTime, participants, mId);
        }


    }
    private String formatDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE dd MMMM yyyy", Locale.FRANCE);
        return date.format(formatter);
    }
    private Chip addChip(String participant){
        final Chip chip = new Chip(mparticipantsChipGroup.getContext());
        chip.setText(participant);
        chip.setChipIcon(getDrawable(R.drawable.ic_person_pin_black_18dp));
        chip.setCheckable(false);
        chip.setClickable(true);
        chip.setCloseIconVisible(true);
        return chip;
    }

}
