package com.example.maru.service;

import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    void deleteMeeting(Meeting meeting);

    void createMeeting(Meeting meeting);

    List<Meeting> getMeetingsByTime(java.util.Date time);

    List<Meeting> getMeetingsByDate(java.util.Date time);

    List<Meeting> getMeetingByRoom(MeetingRoom room);

    MeetingRoom getMeetingRoomByName(String name);

    List<Meeting> getMeetingsInChronologicalOrder();

    List<Meeting> getMeetingByFilter(java.util.Date date, MeetingRoom room, Date time);
}
