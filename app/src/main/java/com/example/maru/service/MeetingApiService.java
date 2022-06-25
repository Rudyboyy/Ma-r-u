package com.example.maru.service;

import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    void deleteMeeting(Meeting meeting);

    void createMeeting(Meeting meeting);

    List<Meeting> getMeetingsByTime(LocalTime time);//todo rajout

    List<Meeting> getMeetingByRoom(MeetingRoom room);//todo rajout

    MeetingRoom getMeetingRoomByName(String name);

    List<Meeting> getMeetingsInChronologicalOrder();
}
