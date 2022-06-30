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

    private void setMeeting() {
        meeting = (Meeting) getIntent().getSerializableExtra(MEETING_INFO);
        binding.topic.setText("logo salle réu + sujet réu");//meeting.getMeetingTopic();
        binding.stateMeeting.setText("Réunion en cours...");
        binding.attendeesMail.setText("gerard@gmail.com, geraldine@gmail.com, gertrude@gmail.com");
    }
}