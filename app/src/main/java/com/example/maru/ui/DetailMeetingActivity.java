package com.example.maru.ui;


import static com.example.maru.ui.MainActivity.MEETING_INFO;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.maru.R;
import com.example.maru.databinding.ActivityDetailMeetingBinding;
import com.example.maru.model.Meeting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class DetailMeetingActivity extends AppCompatActivity {
    Meeting meeting;
    ActivityDetailMeetingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
        setMeeting();
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
        Toolbar toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void initUi() {
        binding = ActivityDetailMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private String setMeetingState(Meeting meeting) {
        String finalMonth = "" + meeting.getDate().getMonthValue();
        String finalDay = "" + meeting.getDate().getDayOfMonth();
        if (meeting.getDate().getMonthValue() < 10) {
            finalMonth = "0" + finalMonth;
        }
        if (meeting.getDate().getDayOfMonth() < 10) {
            finalDay = "0" + finalDay;
        }

        if (meeting.getTime().isBefore(LocalTime.now()) && meeting.getTime().isAfter(LocalTime.now().minusHours(1)) && meeting.getDate().equals(LocalDate.now())) {
            return "Réunion en cours...";//todo mettre dans String.xml
        } else if (meeting.getTime().isAfter(LocalTime.now()) && meeting.getDate().equals(LocalDate.now())) {
            return "Réunion prévue aujourd'hui à " + meeting.getTime();
        } else if (meeting.getTime().isBefore(LocalTime.now()) && meeting.getDate().equals(LocalDate.now()) || meeting.getDate().isBefore(LocalDate.now())) {
            return "Réunion terminée";
        } else if (meeting.getDate().isAfter(LocalDate.now().plusDays(1))) {
            return "Réunion prévue le " + finalDay + "/" + finalMonth + "/" + meeting.getDate().getYear() + " à " + meeting.getTime();
        } else if (meeting.getDate().equals(LocalDate.now().plusDays(1))) {
            return "Réunion prévue demain à " + meeting.getTime();
        } //todo remplacer par un switch 
        return null;
    }

    private void setMeeting() {
        meeting = (Meeting) getIntent().getSerializableExtra(MEETING_INFO);
        binding.itemListAvatar.setImageResource(meeting.getMeetingRoom().getIconRes());
        binding.topic.setText(meeting.getMeetingTopic());//setText("logo salle réu + sujet réu")
        binding.stateMeeting.setText(setMeetingState(meeting));
        binding.attendeesMail.setText(meeting.getAttendees().toString().replace("[", "").replace("]", ""));
    }
}