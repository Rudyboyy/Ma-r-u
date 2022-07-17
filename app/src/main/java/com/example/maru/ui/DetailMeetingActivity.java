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
            return getString(R.string.meeting_details_time_now);

        } else if (meeting.getTime().isAfter(LocalTime.now()) && meeting.getDate().equals(LocalDate.now())) {
            return getString(R.string.meeting_details_time_today, meeting.getTime().toString());

        } else if (meeting.getTime().isBefore(LocalTime.now()) && meeting.getDate().equals(LocalDate.now()) || meeting.getDate().isBefore(LocalDate.now())) {
            return getString(R.string.meeting_details_time_done);

        } else if (meeting.getDate().isAfter(LocalDate.now().plusDays(1))) {
            return getString(R.string.meeting_details_expected_date, finalDay, finalMonth, meeting.getDate().getYear(), meeting.getTime().toString());

        } else if (meeting.getDate().equals(LocalDate.now().plusDays(1))) {
            return getString(R.string.meeting_details_expected_tomorrow, meeting.getTime().toString());
        }
        return null;
    }

    private void setMeeting() {
        meeting = (Meeting) getIntent().getSerializableExtra(MEETING_INFO);
        binding.itemListAvatar.setImageResource(meeting.getMeetingRoom().getIconRes());
        binding.topic.setText(meeting.getMeetingTopic());
        binding.stateMeeting.setText(setMeetingState(meeting));
        StringBuilder attendeesString = new StringBuilder();
        for (int i = 0; i<meeting.getAttendees().size(); i++) {
            attendeesString.append(meeting.getAttendees().get(i));
            if (i<meeting.getAttendees().size() -1) {
                attendeesString.append(", ");
            } else attendeesString.append(".");
        }
        binding.attendeesMail.setText(attendeesString);
    }
}