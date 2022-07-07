package com.example.maru.ui;

import static com.example.maru.model.MeetingRoom.RIVEN;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.maru.R;
import com.example.maru.databinding.ActivityAddMeetingBinding;
import com.example.maru.di.DI;
import com.example.maru.model.Employee;
import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;
import com.example.maru.service.MeetingApiService;
import com.example.maru.ui.dialog.MultiSpinner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    private MeetingApiService mMeetingApiService;
    private MeetingRoom mRoom;
    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;
    private LocalTime mTime;
    private LocalDate mDate;// = LocalDate.now();
    private String roomName;
    private String mTopic;
    private List<String> mAttendees;

//    private List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetingApiService = DI.getMeetingApiService();
        initUi();
        initToolbar();
        initTime();
        initDate();
        initRoomsSpinner();
//        initAttendeesSpinner();
        setSaveButton();
/*        MultiSpinner multiSpinner = (MultiSpinner) findViewById(R.id.multi_spinner);
        multiSpinner.setItems(items, getString(R.array.attendees), );*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.addToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }


    private void setSaveButton() {
        binding.saveButton.setOnClickListener(view -> {

            mRoom = mMeetingApiService.getMeetingRoomByName(roomName);
            mTopic = binding.textFieldTopic.getEditText().getText().toString();
            mAttendees = Arrays.asList(binding.textFieldAttendees.getEditText().getText().toString().split(" "));

            if (mTopic.isEmpty()) {
                binding.textFieldTopic.setError("Veuiller choisir le sujet de la réunion");
                return;
            } else binding.textFieldTopic.setError(null);

            if (mDate == null) {
                binding.textFieldDate.setError("Veuiller choisir la date de la réunion");
                return;
            } else binding.textFieldDate.setError(null);

            if (mTime == null) {
                binding.textFieldTime.setError("Veuiller choisir l'heure de la réunion");
                return;
            } else binding.textFieldTime.setError(null);

            if (mRoom == null) {
                binding.textFieldRoom.setError("Veuiller choisir une salle de réunion");
                return;
            } else binding.textFieldRoom.setError(null);

            if (mAttendees.size() < 2) {
                binding.textFieldAttendees.setError("Veuiller ajouter 2 participant mininum");
                return;
            } else binding.textFieldAttendees.setError(null);


            Meeting meeting = new Meeting(
                    mTopic,
                    mTime,
                    mDate,
                    mRoom,
                    mAttendees
            );

            if (mMeetingApiService.checkMeetingRoomIsAvailable(meeting)) {
                Toast.makeText(this, "Réunion créé !", Toast.LENGTH_SHORT).show();
                mMeetingApiService.createMeeting(meeting);
                finish();
            } else Toast.makeText(this, "La salle de réunion choisi n'est pas disponible !", Toast.LENGTH_SHORT).show();
        });
    }

    private void initDate() {
        binding.textDate.setOnFocusChangeListener((view, hasFocus) -> {
            DatePickerDialog datePickerDialog;
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(this, (datePicker, year, month, day) -> {
                month++;
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);

                String finalMonth = "" + month;
                if (month < 10) {
                    finalMonth = "0" + finalMonth;
                }

                String finalDay = "" + day;
                if (day < 10) {
                    finalDay = "0" + finalDay;
                }

                String dateString = finalDay + "/" + finalMonth + "/" + year;
                binding.textDate.setText(dateString);
                mDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }, mYear, mMonth, mDay);
            if (hasFocus) {
                datePickerDialog.show();
            } else datePickerDialog.dismiss();
        });
    }

    private void initTime() {
        binding.textTime.setOnFocusChangeListener((view, hasFocus) -> {
            if (this.lastSelectedHour == -1) {
                final Calendar c = Calendar.getInstance();
                this.lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
                this.lastSelectedMinute = c.get(Calendar.MINUTE);
            }

            TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    lastSelectedHour = hourOfDay;
                    lastSelectedMinute = minute;
//                    Calendar cal = Calendar.getInstance();
//                    cal.set(hourOfDay, minute);

                    String finalHour = "" + hourOfDay;
                    if (hourOfDay < 10) {
                        finalHour = "0" + finalHour;
                    }

                    String finalMinute = "" + minute;
                    if (minute < 10) {
                        finalMinute = "0" + finalMinute;
                    }

                    String timeString = finalHour + ":" + finalMinute;
                    binding.textTime.setText(timeString);
                    mTime = LocalTime.of(hourOfDay, minute);
                }
            };

            TimePickerDialog timePickerDialog;

            timePickerDialog = new
                    TimePickerDialog(this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    timeSetListener, lastSelectedHour, lastSelectedMinute, true);// ou false avec am et pm

            if (hasFocus) {
                timePickerDialog.show();
            } else timePickerDialog.dismiss();
        });
    }

    private void initRoomsSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.rooms,
                android.R.layout.simple_spinner_dropdown_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spinnerRoom.setAdapter(adapter);

        binding.spinnerRoom.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        roomName = adapterView.getItemAtPosition(i).toString();
                        binding.textRoom.setText(roomName);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                }
        );
    }

/*    private void initAttendeesSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.attendees,
                android.R.layout.simple_spinner_dropdown_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);

        binding.spinnerAttendee.setAdapter(adapter);

        binding.spinnerAttendee.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String attendeesMail = adapterView.getItemAtPosition(i).toString();
                        binding.textAttendees.setText(attendeesMail);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                }
        );
    }*/

    private void initUi() {
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.textDate.setKeyListener(null);// pour que le clavier ne s'affiche pas et qu'il ne soit pas editable
        binding.textTime.setKeyListener(null);
        binding.textRoom.setKeyListener(null);
    }

    /*    @NonNull
        public static Intent navigate(Context context) {//todo test
            return new Intent(context, AddMeetingActivity.class);
        }*/
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

/*    @Override
    public void onItemsSelected(boolean[] selected) {
        new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String attendeesMail = adapterView.getItemAtPosition(i).toString();
                binding.textAttendees.setText(attendeesMail);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
    }*/
}