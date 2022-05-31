package com.example.maru.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.maru.R;
import com.example.maru.databinding.ActivityMainBinding;
import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private ArrayList<Meeting> mMeetingList = new ArrayList<>();
    private final MeetingApiService mMeetingApiService = DI.getMeetingApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initUi();
    }

    private void initUi() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setButton();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        MeetingAdapter meetingAdapter = new MeetingAdapter(mMeetingList);
        binding.recyclerView.setAdapter(meetingAdapter);
    }

    private void initData() {
        mMeetingList = new ArrayList<>(mMeetingApiService.getMeetings());
    }

    private void setButton() {
        binding.addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.addButton) {
            startActivity(new Intent(this, AddMeetingActivity.class));
        }
    }
}