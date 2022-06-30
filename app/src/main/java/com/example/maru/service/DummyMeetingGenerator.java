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

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public  abstract class DummyMeetingGenerator {



    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Réunion A" ,new java.util.Date(2022-1900,11,11,11,11,11), AKALI ,new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(3));}}),
            new Meeting("Réunion B" ,new java.util.Date(2022-1900,10,6,13,0,0), YASUO, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(4));add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting("Réunion C" ,new java.util.Date(2022-1900,6,29,6,0,0), SION, new ArrayList<Employee>() {{
                for (int i = 0; i < DUMMY_EMPLOYEE.size(); i++) {
                    if (i % 2 == 0) {
                        add(DUMMY_EMPLOYEE.get(i));
                }}
            }}),
            new Meeting("Réunion D" ,new java.util.Date(2022-1900,11,12,12,0,0), ILLAOI, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting("Réunion E" ,new java.util.Date(2022-1900,6,29,14,0,0), AHRI, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting("Réunion F" ,new java.util.Date(2022-1900,7,5,15,0,0), PIKE, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting("Réunion G" ,new java.util.Date(2022-1900,8,1,10,0,0), DRAVEN, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting("Réunion H" ,new java.util.Date(2022-1900,9,10,9,0,0), DARIUS, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting("Réunion I" ,new java.util.Date(2022-1900,7,14,14,15,0), GRAVE, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting("Réunion J" ,new Date(2022-1900,1,15,16,0,0), RIVEN, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}})
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
