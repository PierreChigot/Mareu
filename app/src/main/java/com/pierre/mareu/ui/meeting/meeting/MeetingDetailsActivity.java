package com.pierre.mareu.ui.meeting.meeting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    private Button maddParticipantButton;
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
        maddParticipantButton = findViewById(R.id.addParticipant_button);


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
                        String timeFormatted = mbeginTime.format(formatter);
                        mbeginTimeEditTextView.setText(timeFormatted);
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
        maddParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mparticipantsAutoCompleteTextView.getText() != null){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mparticipantsAutoCompleteTextView.getWindowToken(), 0);

                    String participant;
                    participant = mparticipantsAutoCompleteTextView.getText().toString();
                    final Chip chip = new Chip(mparticipantsChipGroup.getContext());

                    chip.setText(participant);
                    chip.setChipIcon(getDrawable(R.drawable.ic_person_pin_black_18dp)) ;
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
                mparticipantsAutoCompleteTextView.setText("");



            }
        });






    }


}
