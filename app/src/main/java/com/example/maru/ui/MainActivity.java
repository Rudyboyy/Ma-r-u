package com.example.maru.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.maru.R;
import com.example.maru.databinding.ActivityMainBinding;
import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;
import com.example.maru.service.MeetingApiService;
import com.example.maru.ui.dialog.FilterDialogFragment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        IOnMeetingDeleted,
        FilterDialogFragment.OnPositiveButtonClickListener {

    private ActivityMainBinding binding;
    private ArrayList<Meeting> mMeetingList = new ArrayList<>();
    private MeetingApiService mMeetingApiService;
    public static final String MEETING_INFO = "meetingInfo";
    private MeetingAdapter mMeetingAdapter;
    private final String filter = "filter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetingApiService = DI.getMeetingApiService();
        initData();
        initUi();
    }

    private void initUi() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initToolbar();
        setButton();
        initRecyclerView();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        mMeetingAdapter = new MeetingAdapter(mMeetingList, this, MainActivity.this);
        binding.recyclerView.setAdapter(mMeetingAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        initRecyclerView();
    }

    private void initData() {
        mMeetingList = new ArrayList<>(mMeetingApiService.getMeetings());
    }

    private void setButton() {
        binding.addButton.setOnClickListener(view -> AddMeetingActivity.navigate(MainActivity.this));
    }

    @Override
    public void onDeleteMeeting(Meeting meeting) {
        mMeetingApiService.deleteMeeting(meeting);
        initData();
        mMeetingAdapter.setMeetings(mMeetingList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                filterDialog();
                return true;
            case R.id.reset_filter:
                resetDialog();
                return true;
            case R.id.chrono_filter:
                chronoDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("NewApi")
    private void chronoDialog() {
        mMeetingList.sort(Meeting::compareTo);
        if (binding.recyclerView.getAdapter() == null) return;
        binding.recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void filterDialog() {
        FilterDialogFragment filterDialog = new FilterDialogFragment();
        filterDialog.show(getSupportFragmentManager(), filter);
        if (binding.recyclerView.getAdapter() == null) return;
        binding.recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void resetDialog() {
        mMeetingList.clear();
        mMeetingList.addAll(mMeetingApiService.getMeetings());
        if (binding.recyclerView.getAdapter() == null) return;
        binding.recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onButtonClicked(MeetingRoom room, LocalTime time, LocalDate date) {
        mMeetingList.clear();
        mMeetingList.addAll(mMeetingApiService.getMeetingByFilter(room, time, date));
        binding.recyclerView.getAdapter().notifyDataSetChanged();
    }
}