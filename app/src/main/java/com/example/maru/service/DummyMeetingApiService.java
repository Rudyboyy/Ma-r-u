package com.example.maru.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)//todo enlever
public class DummyMeetingApiService implements MeetingApiService {
    List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public ArrayList<Meeting> getMeetingByRoom(MeetingRoom room) {
        ArrayList<Meeting> result = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
        }
        return result;
    }

/*    @Override
    public ArrayList<Meeting> getMeetingsFilterByTime(LocalTime time) {
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
}
