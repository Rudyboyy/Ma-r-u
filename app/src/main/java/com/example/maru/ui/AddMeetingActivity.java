package com.example.maru.ui;

import static com.example.maru.model.Employee.DUMMY_EMPLOYEE;
import static com.example.maru.model.MeetingRoom.ILLAOI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.MenuItem;
import android.view.View;

import com.example.maru.R;
import com.example.maru.databinding.ActivityAddMeetingBinding;
import com.example.maru.databinding.ActivityMainBinding;
import com.example.maru.di.DI;
import com.example.maru.model.Employee;
import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;
import com.example.maru.service.MeetingApiService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    private final MeetingApiService mMeetingApiService = DI.getMeetingApiService();
    private MeetingRoom mRoom;
    private Date mDate;
    private ArrayList<Employee> mEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
        setSaveButton();
        initToolbar();
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
            String mail = binding.textFieldAttendees.getEditText().getText().toString();
            ArrayList<Employee> attendees = new ArrayList<>();
            for (int i = 0; i < mEmployees.size(); i++) {
            if (mail.equals(Employee.DUMMY_EMPLOYEE.get(i).toString())) {
                attendees.add(Employee.DUMMY_EMPLOYEE.get(i));
            }}
            mRoom = mMeetingApiService.getMeetingRoomByName(binding.textFieldRoom.getEditText().getText().toString());


            Meeting meeting = new Meeting(
//                    binding.textFieldTopic.getEditText().getText().toString(),
//                    new Date(11,11,11),
//                    mRoom,
//                    attendees
                    "Réunion 12" ,new Date(2022,7,3,11,0,0), ILLAOI, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}
            );
            mMeetingApiService.createMeeting(meeting);
            finish();
        });
    }



    private void initUi() {
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @NonNull
    public static Intent navigate(Context context) {//todo test
        return new Intent(context, AddMeetingActivity.class);
    }
}