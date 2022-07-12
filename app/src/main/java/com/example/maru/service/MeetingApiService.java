package com.example.maru.service;

import com.example.maru.model.Employee;
import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Meeting API service
 */
public interface MeetingApiService {

    /**
     * Get all meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Delete a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    /**
     * Get meetings by their time
     * @param time
     * @return {@link List}
     */
    List<Meeting> getMeetingsByTime(LocalTime time);

    /**
     * Get meetings by their date
     * @param time
     * @return {@link List}
     */
    List<Meeting> getMeetingsByDate(LocalDate time);

    /**
     * Get meeting by their room
     * @param room
     * @return {@link List}
     */
    List<Meeting> getMeetingByRoom(MeetingRoom room);

    /**
     * Get meeting room by String
     * @param name
     * @return {@link List}
     */
    MeetingRoom getMeetingRoomByName(String name);

    /**
     *
     * @param name
     * @return
     */
    List<Employee> getAttendeesByMail(String name); //todo supprimer ?

    /**
     * Get meeting by all filter
     * @param room
     * @param time
     * @param date
     * @return {@link List}
     */
    List<Meeting> getMeetingByFilter(MeetingRoom room, LocalTime time, LocalDate date );

    /**
     * Get meetings by their date and time
     * @param dateTime
     * @return {@link List}
     */
    List<Meeting> getMeetingByDateTime(LocalDateTime dateTime);

    /**
     * Get meetings by their date, time and room
     * @param dateTime
     * @param room
     * @return {@link List}
     */
    List<Meeting> getMeetingByDateTimeRoom(LocalDateTime dateTime, MeetingRoom room);

    /**
     * Get meetings by their date and room
     * @param date
     * @param room
     * @return {@link List}
     */
    List<Meeting> getMeetingByDateRoom(LocalDate date, MeetingRoom room);

    /**
     * Get meetings by their time and room
     * @param time
     * @param room
     * @return {@link List}
     */
    List<Meeting> getMeetingByTimeRoom(LocalTime time, MeetingRoom room);

    /**
     * Check that the meeting do not exist already
     * @param meeting
     * @return {@link List}
     */
    boolean checkMeetingRoomIsAvailable(Meeting meeting);
}
