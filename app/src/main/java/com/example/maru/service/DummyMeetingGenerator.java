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

import static java.lang.Enum.valueOf;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.maru.model.Employee;
import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;

import java.sql.Time;
import java.time.LocalDate;
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
            new Meeting("Réunion A" , LocalTime.of(10,  0), LocalDate.now().plusDays(1), AKALI , Arrays.asList(DUMMY_EMPLOYEE.get(0).getMail(), DUMMY_EMPLOYEE.get(1).getMail())),
            new Meeting("Réunion B" , LocalTime.of(11,  0), LocalDate.now(), AKALI , Arrays.asList(DUMMY_EMPLOYEE.get(1).getMail(), DUMMY_EMPLOYEE.get(2).getMail())),
            new Meeting("Réunion C" , LocalTime.of(12,  0), LocalDate.now(), YASUO , Arrays.asList(DUMMY_EMPLOYEE.get(3).getMail(), DUMMY_EMPLOYEE.get(5).getMail())),
            new Meeting("Réunion D" , LocalTime.of(13,  0), LocalDate.now(), SION  , Arrays.asList(DUMMY_EMPLOYEE.get(6).getMail(), DUMMY_EMPLOYEE.get(9).getMail())),
            new Meeting("Réunion E" , LocalTime.of(14,  0), LocalDate.now(), AKALI , Arrays.asList(DUMMY_EMPLOYEE.get(8).getMail(), DUMMY_EMPLOYEE.get(0).getMail())),
            new Meeting("Réunion F" , LocalTime.of(15,  0), LocalDate.now(), AKALI , Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion G" , LocalTime.of(16,  0), LocalDate.now(), AKALI , Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion H" , LocalTime.of(17,  0), LocalDate.now(), ILLAOI, Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion I" , LocalTime.of(18,  0), LocalDate.now(), YASUO , Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion J" , LocalTime.of(19,  0), LocalDate.now(), AHRI  , Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion K" , LocalTime.of(20,  0), LocalDate.now(), AKALI , Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion L" , LocalTime.of(20, 30), LocalDate.now(), GRAVE , Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion M" , LocalTime.of(19, 30), LocalDate.now(), SION  , Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion N" , LocalTime.of( 9, 30), LocalDate.now(), ILLAOI, Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion O" , LocalTime.of( 8, 30), LocalDate.now(), AHRI  , Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion P" , LocalTime.of( 7, 30), LocalDate.now(), PIKE  , Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion Q" , LocalTime.of( 6, 30), LocalDate.now(), DRAVEN, Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion R" , LocalTime.of(11, 30), LocalDate.now(), DARIUS, Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion S" , LocalTime.of(14, 30), LocalDate.now(), GRAVE , Arrays.asList("zeze", "defezf")),
            new Meeting("Réunion T" , LocalTime.of(15, 30), LocalDate.now(), RIVEN , Arrays.asList("zeze", "defezf"))
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
