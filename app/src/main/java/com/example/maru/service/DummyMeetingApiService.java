package com.example.maru.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<Meeting> getMeetingsInChronologicalOrder() {// todo marche pas
        Collections.sort(meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting meeting, Meeting t1) {
                if (meeting.getDate() == null || t1.getDate() == null) {
                    return 0;
                }
                return meeting.getDate().compareTo(t1.getDate());
            }
        });
        return null;
    }

    @Override
    public List<Meeting> getMeetingByFilter(java.util.Date date, MeetingRoom room, Date time) {
        ArrayList<Meeting> result = new ArrayList<>();
         if (room != null) {
            result.addAll(getMeetingByRoom(room));
        } if (date != null) {
            result.addAll(getMeetingsByDate(date));
        } if (time != null) {
            result.addAll(getMeetingsByTime(time));
        }
        return result;
    }

    @Override
    public List<Meeting> getMeetingsByTime(java.util.Date time) {
        ArrayList<Meeting> result = new ArrayList<>();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(time);
        for (int i = 0; i < meetings.size(); i++) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(meetings.get(i).getDate());
            boolean sameHour = cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY);// cal1.getTime().getHours() == cal2.getTime().getHours();
            if (sameHour) result.add(meetings.get(i));
        }
        return result;
    }

    @Override
    public List<Meeting> getMeetingsByDate(java.util.Date Date) {
        ArrayList<Meeting> result = new ArrayList<>();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(Date);
        for (int i = 0; i < meetings.size(); i++) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(meetings.get(i).getDate());
            boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                    cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
            if (sameDay) result.add(meetings.get(i));
        }
        return result;
    }
}
