package com.example.maru.service;

import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    public List<Meeting> getMeetingByFilter(MeetingRoom room, LocalTime time, LocalDate date) {
        ArrayList<Meeting> result = new ArrayList<>();
        if (room != null) {
            result.addAll(getMeetingByRoom(room));
        }
        if (date != null && time != null) {
            result.addAll(getMeetingByDateTime(LocalDateTime.of(date, time)));
        }
        if (time != null && date == null) {
            result.addAll(getMeetingsByTime(time));
        }
        if (date != null && time == null) {
            result.addAll(getMeetingsByDate(date));
        }
        if (date != null && time != null && room != null) {
            result.clear();
            result.addAll(getMeetingByDateTimeRoom(LocalDateTime.of(date, time), room));
        }
        if (room != null && date != null) {
            result.clear();
            result.addAll(getMeetingByDateRoom(date, room));
        }
        if (room != null && time != null) {
            result.clear();
            result.addAll(getMeetingByTimeRoom(time, room));
        }
        return result;
    }

    @Override
    public List<Meeting> getMeetingByDateTime(LocalDateTime dateTime) {
        ArrayList<Meeting> result = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).getTime().getHour() == dateTime.toLocalTime().getHour() && meetings.get(i).getTime().getMinute() == dateTime.toLocalTime().getMinute()
                    && meetings.get(i).getDate().getDayOfMonth() == dateTime.toLocalDate().getDayOfMonth() && meetings.get(i).getDate().getYear() == dateTime.toLocalDate().getYear()) {
                result.add(meetings.get(i));
            }
        }
        return result;
    }

    @Override
    public List<Meeting> getMeetingByDateTimeRoom(LocalDateTime dateTime, MeetingRoom room) {
        ArrayList<Meeting> result = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).getTime().getHour() == dateTime.toLocalTime().getHour() && meetings.get(i).getTime().getMinute() == dateTime.toLocalTime().getMinute()
                    && meetings.get(i).getDate().getDayOfMonth() == dateTime.toLocalDate().getDayOfMonth() && meetings.get(i).getDate().getYear() == dateTime.toLocalDate().getYear()
                    && meetings.get(i).getMeetingRoom() == room) {
                result.add(meetings.get(i));
            }
        }
        return result;
    }

    @Override
    public List<Meeting> getMeetingByDateRoom(LocalDate date, MeetingRoom room) {
        ArrayList<Meeting> result = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).getDate().getDayOfMonth() == date.getDayOfMonth() && meetings.get(i).getDate().getYear() == date.getYear()
                    && meetings.get(i).getMeetingRoom() == room) {
                result.add(meetings.get(i));
            }
        }
        return result;
    }

    @Override
    public List<Meeting> getMeetingByTimeRoom(LocalTime time, MeetingRoom room) {
        ArrayList<Meeting> result = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).getTime().getHour() == time.getHour() && meetings.get(i).getTime().getMinute() == time.getMinute()
                    && meetings.get(i).getMeetingRoom() == room) {
                result.add(meetings.get(i));
            }
        }
        return result;
    }

    @Override
    public boolean checkMeetingRoomIsAvailable(Meeting meeting) {
        for (int i = 0; i < meetings.size(); i++) {
            if (meeting.getMeetingRoom() == meetings.get(i).getMeetingRoom() && meeting.getDate().getDayOfMonth() == meetings.get(i).getDate().getDayOfMonth()
                    && meeting.getDate().getYear() == meetings.get(i).getDate().getYear() && meeting.getTime().isAfter(meetings.get(i).getTime().minusHours(1))
                    && meeting.getTime().isBefore(meetings.get(i).getTime().plusHours(1))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Meeting> getMeetingsByTime(LocalTime time) {
        ArrayList<Meeting> result = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).getTime().getHour() == time.getHour() && meetings.get(i).getTime().getMinute() == time.getMinute()) {
                result.add(meetings.get(i));
            }
        }
        return result;
    }

    @Override
    public List<Meeting> getMeetingsByDate(LocalDate date) {
        ArrayList<Meeting> result = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).getDate().getDayOfMonth() == date.getDayOfMonth() && meetings.get(i).getDate().getYear() == date.getYear()) {
                result.add(meetings.get(i));
            }
        }
        return result;
    }
}
