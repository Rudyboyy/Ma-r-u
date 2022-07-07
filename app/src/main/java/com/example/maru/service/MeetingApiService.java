package com.example.maru.service;

import com.example.maru.model.Employee;
import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    void deleteMeeting(Meeting meeting);

    void createMeeting(Meeting meeting);

    List<Meeting> getMeetingsByTime(LocalTime time);

    List<Meeting> getMeetingsByDate(LocalDate time);

    List<Meeting> getMeetingByRoom(MeetingRoom room);

    MeetingRoom getMeetingRoomByName(String name);

    List<Employee> getAttendeesByMail(String name);

    List<Meeting> getMeetingsInChronologicalOrder();

    List<Meeting> getMeetingByFilter(MeetingRoom room, LocalTime time, LocalDate date );

    List<Meeting> getMeetingByDateTime(LocalDateTime dateTime);

    List<Meeting> getMeetingByDateTimeRoom(LocalDateTime dateTime, MeetingRoom room);

    List<Meeting> getMeetingByDateRoom(LocalDate date, MeetingRoom room);

    List<Meeting> getMeetingByTimeRoom(LocalTime time, MeetingRoom room);

    boolean checkMeetingRoomIsAvailable(Meeting meeting);
}
