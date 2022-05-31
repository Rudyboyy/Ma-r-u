package com.example.maru.model;

import java.sql.Time;

public class Meeting {

    private final long id;
    String MeetingTopic, MeetingRoom, Attendees;
    Time mTime;

    public long getId() {
        return id;
    }

    public String getMeetingTopic() {
        return MeetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        MeetingTopic = meetingTopic;
    }

    public String getMeetingRoom() {
        return MeetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        MeetingRoom = meetingRoom;
    }

    public String getAttendees() {
        return Attendees;
    }

    public void setAttendees(String attendees) {
        Attendees = attendees;
    }

    public Time getTime() {
        return mTime;
    }

    public void setTime(Time time) {
        mTime = time;
    }

    public Meeting(long id, String topic, String room, String attendees) {

        this.id = id;
        this.MeetingTopic = topic;
        this.MeetingRoom = room;
        this.Attendees = attendees;
//        this.mTime = new Time();
    }

    public Meeting(long id, String topic, Time time, String room, String attendees) {

        this.id = id;
        this.MeetingTopic = topic;
        this.MeetingRoom = room;
        this.Attendees = attendees;
        mTime = time;
    }
}
