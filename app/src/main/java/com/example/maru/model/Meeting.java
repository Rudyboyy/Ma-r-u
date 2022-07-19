package com.example.maru.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Meeting implements
        Serializable,
        Comparable<Meeting> {

    private MeetingRoom meetingRoom;
    private String meetingTopic;
    private List<String> attendees;
    private LocalTime mTime;
    private LocalDate mDate;
    private LocalDateTime mDateTime;

    private final SimpleDateFormat dateHourFormat =
            new SimpleDateFormat("HH:mm", Locale.getDefault());

    public String formatterDateHour(LocalTime date) {
        return date.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(ArrayList<String> attendees) {
        this.attendees = attendees;
    }

    public String getStringTime() {
        return formatterDateHour(mTime);
    }

    public LocalTime getTime() {
        return mTime;
    }

    public void setDate(LocalDate date) {
        mDate = date;
    }

    public LocalDateTime getDateTime() {
        return mDateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        mDateTime = dateTime;
    }

    public LocalDate getDate() {
        return mDate;
    }

    public void setTime(LocalTime time) {
        mTime = time;
    }


    public Meeting(String topic, LocalTime time, LocalDate date, MeetingRoom room, List<String> attendees) {

        this.meetingTopic = topic;
        this.meetingRoom = room;
        this.attendees = attendees;
        this.mDate = date;
        this.mTime = time;
        this.mDateTime = LocalDateTime.of(date, time);
    }

    @NonNull
    @Override
    public String toString() {
        return "Meeting{" +
                "meetingRoom=" + meetingRoom +
                ", meetingTopic='" + meetingTopic + '\'' +
                ", attendees=" + attendees +
                ", mTime=" + mTime +
                ", mDate=" + mDate +
                ", mDateTime=" + mDateTime +
                ", dateHourFormat=" + dateHourFormat +
                '}';
    }

    @Override
    public int compareTo(Meeting meeting) {
        if (getDateTime() == null || meeting.getDateTime() == null)
            return 0;
        return getDateTime().compareTo(meeting.getDateTime());
    }
}
