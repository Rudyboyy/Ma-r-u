package com.example.maru.service;

import com.example.maru.model.Meeting;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public  abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1, "Réunion A" ,new Time(10, 0,0), "Akali", "eddy.moitout@lamzone.com"),
            new Meeting(2, "Réunion B" ,new Time(11,0,0), "Yasuo", "thibault.monfils@lamzone.com"),
            new Meeting(3, "Réunion C" ,new Time(12,0,0), "Sion", "jessica.pote@lamzone.com"),
            new Meeting(4, "Réunion D" ,new Time(13,0,0), "Illaoi", "marie.rouana@lamzone.com"),
            new Meeting(5, "Réunion E" ,new Time(14,0,0), "Ahri", "sarah.croche@lamzone.com"),
            new Meeting(6, "Réunion F" ,new Time(15,0,0), "Pike", "brice.glace@lamzone.com"),
            new Meeting(7, "Réunion G" ,new Time(16,0,0), "Draven", "alain.proviste@lamzone.com"),
            new Meeting(8, "Réunion H" ,new Time(17,0,0), "Darius", "cecile.onxa@lamzone.com"),
            new Meeting(9, "Réunion I" ,new Time(18,0,0), "Grave", "oussama.lairbizar@lamzone.com"),
            new Meeting(10, "Réunion J" ,new Time(19,0,0), "Riven", "justin.ptipeu@lamzone.com")
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
