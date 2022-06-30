package com.example.maru.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Meeting implements Serializable {

    private MeetingRoom meetingRoom;
    private String meetingTopic;
    private ArrayList<Employee> attendees;
    private java.util.Date mDate;

    protected static final SimpleDateFormat dateHeureFormat =
            new SimpleDateFormat("HH:mm");//todo test metre dans utils

    public static String formatterDateHour(java.util.Date date) {
        return dateHeureFormat.format(date);
    }//todo test metre dans utils

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

    public ArrayList<Employee> getAttendees() {
        return attendees;
    }

    public void setAttendees(ArrayList<Employee> attendees) {
        this.attendees = attendees;
    }

    public String getTime() {
        return formatterDateHour(mDate);
    }

    public java.util.Date getDate() {
        return mDate;
    }

    public void setTime(Date time) {
        mDate = time;
    }


    public Meeting(String topic, java.util.Date time, MeetingRoom room, ArrayList<Employee> attendees) {

        this.meetingTopic = topic;
        this.meetingRoom = room;
        this.attendees = attendees;
        mDate = time;
    }

    public String getAttendeesNames() {
        String result = "";
        for(Employee attendee : attendees) {
            result += attendee.getMail()+", ";
        }
        return result;
    }

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
}
