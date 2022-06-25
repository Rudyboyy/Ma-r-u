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

@RequiresApi(api = Build.VERSION_CODES.O) //todo si je l'enleve ca ne marche pas                     pourquoi ???
public  abstract class DummyMeetingGenerator {



    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1, "Réunion A" ,LocalTime.parse("12:15"), AKALI ,new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(3));}}),
            new Meeting(2, "Réunion B" ,LocalTime.parse("13:15"), YASUO, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(4));add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(3, "Réunion C" ,LocalTime.parse("09:25"), SION, new ArrayList<Employee>() {{
                for (int i = 0; i < DUMMY_EMPLOYEE.size(); i++) {
                    if (i % 2 == 0) {
                        add(DUMMY_EMPLOYEE.get(i));
                }}
            }}),
            new Meeting(4, "Réunion D" ,LocalTime.parse("15:15"), ILLAOI, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(5, "Réunion E" ,LocalTime.parse("10:15"), AHRI, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(6, "Réunion F" ,LocalTime.parse("17:15"), PIKE, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(7, "Réunion G" ,LocalTime.parse("18:15"), DRAVEN, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(8, "Réunion H" ,LocalTime.parse("19:15"), DARIUS, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(9, "Réunion I" ,LocalTime.parse("11:15"), GRAVE, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}}),
            new Meeting(10, "Réunion J" ,LocalTime.parse("08:15"), RIVEN, new ArrayList<Employee>() {{add(DUMMY_EMPLOYEE.get(0));}})
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
