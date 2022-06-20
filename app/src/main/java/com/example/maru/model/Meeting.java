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

    private final long id;
    private MeetingRoom meetingRoom;
    private String meetingTopic;
    private ArrayList<Employee> attendees;
    private LocalTime mTime;
    private int minute;
    private int hour;

@RequiresApi(api = Build.VERSION_CODES.O)
public static LocalTime getRandomMeetingHour() {
    int hour = new Random().nextInt(24);
    int minutes = new Random().nextInt(60);

    return LocalTime.of(hour, minutes);
}
/*        public static void main(String[] args) {
            SimpleDateFormat formater = null;

            Date aujourdhui = new Date();

            formater = new SimpleDateFormat("HH:mm");
            System.out.println(formater.format(aujourdhui));
        }*///todo test

    protected static final SimpleDateFormat dateHeureFormat =
            new SimpleDateFormat("hh:mm");//todo test


    public static String formatterDateHeure(Date date) {
        return dateHeureFormat.format(date);
    }//todo test

    public long getId() {
        return id;
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

    public ArrayList<Employee> getAttendees() {
        return attendees;
    }

    public void setAttendees(ArrayList<Employee> attendees) {
        this.attendees = attendees;
    }

    public LocalTime getTime() {
        return mTime;
    }

    public void setTime(LocalTime time) {
        mTime = time;
    }


    public Meeting(long id, String topic, LocalTime time, MeetingRoom room, ArrayList<Employee> attendees) {

        this.id = id;
        this.meetingTopic = topic;
        this.meetingRoom = room;
        this.attendees = attendees;
        mTime = time;
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
