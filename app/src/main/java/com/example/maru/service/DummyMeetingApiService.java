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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
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
    public List<Meeting> getMeetingByRoom(MeetingRoom room) {
        List<Meeting> result = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).getMeetingRoom() == room) {
                result.add(meetings.get(i));
            }
        }
        return result;
    }

    @Override
    public MeetingRoom getMeetingRoomByName(String name) {
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).getMeetingRoom().getName().equals(name)) {
                return meetings.get(i).getMeetingRoom();
            }
        }
        return null;
    }

   /* @Override
    public List<Meeting> getMeetingsInChronologicalOrder() {//todo fonctionne pas
        ArrayList<Meeting> result = new ArrayList<>();
        for (int h = 0; h < 24; h++) {
            for (int i = 0; i < meetings.size(); i++) {
                if (meetings.get(i).getTime().isBefore(LocalTime.of(h, 0))) {
                    result.add(meetings.get(i));
                }
            }
        }
        return result;
    }*/

    @Override
    public List<Meeting> getMeetingsInChronologicalOrder() {// todo faire test sur mobile
        Collections.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting meeting, Meeting t1) {
                return 0;
            }
        });
        ArrayList<Meeting> result = new ArrayList<>();
        int h = LocalTime.MIDNIGHT.plusHours(1).getHour();
        while (h != LocalTime.MIDNIGHT.minusHours(1).getHour()) {
            for (int i = 0; i < meetings.size(); i++) {
                    if (h == meetings.get(i).getTime().getHour()) {
                        result.add(meetings.get(i));
                    }
                    h++;
                }
        }
        return result;
    }

    @Override
    public List<Meeting> getMeetingsByTime(LocalTime time) {
        ArrayList<Meeting> result = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).getTime().isBefore(time.plusHours(1)) && meetings.get(i).getTime().isAfter(time) || meetings.get(i).getTime().equals(time)) {
                result.add(meetings.get(i));
            }
        }
        return result;
    }
}
