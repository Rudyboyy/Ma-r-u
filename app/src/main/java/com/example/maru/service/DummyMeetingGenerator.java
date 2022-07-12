package com.example.maru.service;

import static com.example.maru.model.Employee.DUMMY_EMPLOYEE;
import static com.example.maru.model.MeetingRoom.AHRI;
import static com.example.maru.model.MeetingRoom.AKALI;
import static com.example.maru.model.MeetingRoom.DARIUS;
import static com.example.maru.model.MeetingRoom.DRAVEN;
import static com.example.maru.model.MeetingRoom.GRAVE;
import static com.example.maru.model.MeetingRoom.ILLAOI;
import static com.example.maru.model.MeetingRoom.PYKE;
import static com.example.maru.model.MeetingRoom.SION;
import static com.example.maru.model.MeetingRoom.YASUO;

import com.example.maru.model.Meeting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public  abstract class DummyMeetingGenerator {



    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Réunion A", LocalTime.of(10,  0) , LocalDate.now().plusDays(1) , AHRI , Arrays.asList(DUMMY_EMPLOYEE.get(0).getMail(), DUMMY_EMPLOYEE.get(1).getMail())),
            new Meeting("Réunion B", LocalTime.now()                  , LocalDate.now()             , PYKE , Arrays.asList(DUMMY_EMPLOYEE.get(1).getMail(), DUMMY_EMPLOYEE.get(2).getMail())),
            new Meeting("Réunion C", LocalTime.of(12,  0) , LocalDate.now().minusDays(1), YASUO , Arrays.asList(DUMMY_EMPLOYEE.get(3).getMail(), DUMMY_EMPLOYEE.get(5).getMail())),
            new Meeting("Réunion D", LocalTime.of(13,  0) , LocalDate.now().plusDays(2) , SION  , Arrays.asList(DUMMY_EMPLOYEE.get(6).getMail(), DUMMY_EMPLOYEE.get(9).getMail())),
            new Meeting("Réunion E", LocalTime.of(23,  59), LocalDate.now()             , DARIUS , Arrays.asList(DUMMY_EMPLOYEE.get(0).getMail(), DUMMY_EMPLOYEE.get(9).getMail())),
            new Meeting("Réunion F", LocalTime.of(15,  0) , LocalDate.now()             , GRAVE , Arrays.asList(DUMMY_EMPLOYEE.get(2).getMail(), DUMMY_EMPLOYEE.get(4).getMail())),
            new Meeting("Réunion G", LocalTime.of(16,  0) , LocalDate.now()             , DRAVEN , Arrays.asList(DUMMY_EMPLOYEE.get(4).getMail(), DUMMY_EMPLOYEE.get(7).getMail())),
            new Meeting("Réunion H", LocalTime.of(17,  0) , LocalDate.now()             , ILLAOI, Arrays.asList(DUMMY_EMPLOYEE.get(5).getMail(), DUMMY_EMPLOYEE.get(8).getMail())),
            new Meeting("Réunion I", LocalTime.of(9 ,  0) , LocalDate.now()             , YASUO , Arrays.asList(DUMMY_EMPLOYEE.get(7).getMail(), DUMMY_EMPLOYEE.get(2).getMail())),
            new Meeting("Réunion J", LocalTime.of(8 ,  0) , LocalDate.now()             , AKALI  , Arrays.asList(DUMMY_EMPLOYEE.get(8).getMail(), DUMMY_EMPLOYEE.get(1).getMail()))
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
