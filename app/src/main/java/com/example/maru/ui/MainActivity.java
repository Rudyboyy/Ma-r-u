package com.example.maru.ui;

import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.maru.R;
import com.example.maru.databinding.ActivityMainBinding;
import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.service.MeetingApiService;
import com.example.maru.ui.dialog.RoomDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements IOnMeetingDeleted {

    private ActivityMainBinding binding;
    private ArrayList<Meeting> mMeetingList = new ArrayList<>();
    private final MeetingApiService mMeetingApiService = DI.getMeetingApiService();
//    public static final String MEETING_INFO = "meetingInfo";//todo rajout
    MeetingAdapter mMeetingAdapter;
    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1; //todo rajout

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

    private void initToolbar() { //todo test pour la toolbar ne fonctionne pas
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        mMeetingAdapter = new MeetingAdapter(mMeetingList, this);
        binding.recyclerView.setAdapter(mMeetingAdapter);
    }

    private void initData() {
        mMeetingList = new ArrayList<>(mMeetingApiService.getMeetings());
    }

    private void setButton() {
        binding.addButton.setOnClickListener(view1 -> startActivity(new Intent(this, AddMeetingActivity.class)));
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

/*    @Override//todo avec switch au lieu de if
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.time_filter:
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

// initialise la methode sur l'item cliqué
@Override
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.time_filter) {
        timeDialog();
        return true;
    }
    else if (item.getItemId() == R.id.reset_filter) {
        resetDialog();
        return true;
    }
    else if (item.getItemId() == R.id.room_filter) {
        roomDialog();
        return true;
    }
    return super.onOptionsItemSelected(item);
}

    private void roomDialog() {
        RoomDialogFragment filterDialog = new RoomDialogFragment();
        filterDialog.show(getSupportFragmentManager(), "Room");
    }

    private void resetDialog() {
        mMeetingList.clear();
        mMeetingList.addAll(mMeetingApiService.getMeetings());
        if (binding.recyclerView.getAdapter() == null) return;
        binding.recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void timeDialog() {
        if(this.lastSelectedHour == -1)  {
            final Calendar c = Calendar.getInstance();
            this.lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
            this.lastSelectedMinute = c.get(Calendar.MINUTE);
        }

//pour override le onTimeSet
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                lastSelectedHour = hourOfDay;
                lastSelectedMinute = minute;
                Calendar cal = Calendar.getInstance();
                cal.set(hourOfDay, minute);
                mMeetingList.clear();
//                mMeetingList.addAll(mMeetingApiService.getMeetingsFilterByTime(cal.getTime()));
                if (binding.recyclerView.getAdapter() == null) return;
                binding.recyclerView.getAdapter().notifyDataSetChanged();
            }
        };

        TimePickerDialog timePickerDialog;//todo = null; ??

            timePickerDialog = new TimePickerDialog(this,
//                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    timeSetListener, lastSelectedHour, lastSelectedMinute, true);// ou false avec am et pm

        timePickerDialog.show();

    }

/*    public ArrayList<Meeting> getMeetingFilterByTime(LocalTime time) {
        ArrayList<Meeting> result = new ArrayList<>();
        for (int i = 0; i < mMeetingList.size(); i++) {
            if (mMeetingList.get(i).getTime() == time) {
                result.add(mMeetingList.get(i));
            }
        }
        return result;
    }*/
}
/*    @Override
    public ArrayList<Meeting> getMeetingFilterByTime(LocalDateTime time) {
        ArrayList<Meeting> result = new ArrayList<>();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(time);
        for (int i = 0; i < meetings.size(); i++) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(meetings.get(i).getTime());
            boolean sameDay = cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY) &&
                    cal1.get(Calendar.MINUTE) == cal2.get(Calendar.MINUTE);
            if (sameDay) result.add(meetings.get(i));
        }
        return result;
    }*/