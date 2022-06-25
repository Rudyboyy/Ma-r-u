package com.example.maru.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.MenuItem;
import android.view.View;

import com.example.maru.R;
import com.example.maru.databinding.ActivityAddMeetingBinding;
import com.example.maru.databinding.ActivityMainBinding;
import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.service.MeetingApiService;

import java.util.Objects;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    private final MeetingApiService mMeetingApiService = DI.getMeetingApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initUi();
        setSaveButton();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setSaveButton() {
        binding.saveButton.setOnClickListener(view -> {
/*            Meeting meeting = new Meeting(
                    System.currentTimeMillis(),
                    binding.textFieldTopic.getEditText().getText().toString(),
                    binding.textFieldTime.getEditText().getText().toString(),
                    binding.textFieldRoom.getEditText().getText().toString(),
                    binding.textFieldAttendees.getEditText().getText().toString()
            )*/
//            mMeetingApiService.createMeeting(meeting);
            finish();
        });
    }



    private void initUi() {
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}