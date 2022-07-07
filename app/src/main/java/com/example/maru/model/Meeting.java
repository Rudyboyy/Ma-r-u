package com.example.maru.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Meeting implements Serializable {

    private MeetingRoom meetingRoom;
    private String meetingTopic;
    private List<String> attendees; // todo ***
    private LocalTime mTime;
    private LocalDate mDate;
    private LocalDateTime mDateTime;

    private final SimpleDateFormat dateHourFormat =
            new SimpleDateFormat("HH:mm", Locale.getDefault());//todo test metre dans utils

    public String formatterDateHour(LocalTime date) {
        return date.format(DateTimeFormatter.ofPattern("HH:mm"));
    } //todo test metre dans utils

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
    }// todo ***

    public void setAttendees(ArrayList<String> attendees) {
        this.attendees = attendees;
    }// todo ***

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


    public Meeting(String topic, LocalTime time, LocalDate date, MeetingRoom room, List<String> attendees) {// todo ***

        this.meetingTopic = topic;
        this.meetingRoom = room;
        this.attendees = attendees;
        this.mDate = date;
        this.mTime = time;
        this.mDateTime = LocalDateTime.of(date, time);
    }

 /*   public String getAttendeesNames() {// todo ***
        String result = "";
        for(Employee attendee : attendees) {
            result += attendee.getMail()+", ";
        }
        return result;
    }*/

    public String getRoomsNames() {//todo rajout
        String result = "";
        for(MeetingRoom rooms : MeetingRoom.values()) {
            result += rooms.getName();
        }
        return result;
    }

/*    public String getAttendeesNames() { //todo celui-ci ???
        StringBuilder result = new StringBuilder();
        for(Employee attendee : attendees) {
            result.append(attendee.getMail()).append(", ");
        }
        return result.toString();
    }*/

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingRoom=" + meetingRoom +
                ", meetingTopic='" + meetingTopic + '\'' +
                ", attendees=" + attendees +
                ", mDateTime=" + mDateTime +
                ", mTime=" + mTime +
                ", mDate=" + mDate +
                ", dateHourFormat=" + dateHourFormat +
                '}';
    }
}
