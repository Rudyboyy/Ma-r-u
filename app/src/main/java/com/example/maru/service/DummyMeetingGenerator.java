package com.example.maru.service;

import static com.example.maru.model.Employee.DUMMY_EMPLOYEE;
import static com.example.maru.model.MeetingRoom.AHRI;
import static com.example.maru.model.MeetingRoom.AKALI;
import static com.example.maru.model.MeetingRoom.DARIUS;
import static com.example.maru.model.MeetingRoom.DRAVEN;
import static com.example.maru.model.MeetingRoom.GRAVE;
import static com.example.maru.model.MeetingRoom.ILLAOI;
import static com.example.maru.model.MeetingRoom.PIKE;
import static com.example.maru.model.MeetingRoom.RIVEN;
import static com.example.maru.model.MeetingRoom.SION;
import static com.example.maru.model.MeetingRoom.YASUO;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.maru.model.Employee;
import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public  abstract class DummyMeetingGenerator {



    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1, "Réunion A" ,Meeting.getRandomMeetingHour(), AKALI ,new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(3));}}),
            new Meeting(2, "Réunion B" ,Meeting.getRandomMeetingHour(), YASUO, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(4));add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(3, "Réunion C" ,Meeting.getRandomMeetingHour(), SION, new ArrayList<Employee>() {{
                for (int i = 0; i < DUMMY_EMPLOYEE.size(); i++) {
                    if (i % 2 == 0) {
                        add(DUMMY_EMPLOYEE.get(i));
                }}
            }}),
            new Meeting(4, "Réunion D" ,Meeting.getRandomMeetingHour(), ILLAOI, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(5, "Réunion E" ,Meeting.getRandomMeetingHour(), AHRI, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(6, "Réunion F" ,Meeting.getRandomMeetingHour(), PIKE, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(7, "Réunion G" ,Meeting.getRandomMeetingHour(), DRAVEN, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(8, "Réunion H" ,Meeting.getRandomMeetingHour(), DARIUS, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(9, "Réunion I" ,Meeting.getRandomMeetingHour(), GRAVE, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(10, "Réunion J" ,Meeting.getRandomMeetingHour(), RIVEN, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}})
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
