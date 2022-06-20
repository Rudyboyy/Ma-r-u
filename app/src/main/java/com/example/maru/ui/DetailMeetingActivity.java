package com.example.maru.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maru.R;
import com.example.maru.model.Meeting;

public class DetailMeetingActivity extends AppCompatActivity {
    Meeting meeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_meeting);
//        meeting = (Meeting) getIntent().getSerializableExtra(MEETING_INFO);
    }
}