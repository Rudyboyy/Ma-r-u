package com.example.maru.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum MeetingRoom {

    AKALI("akali"),
    YASUO("yasuo"),
    SION("sion"),
    ILLAOI("illaoi"),
    AHRI("ahri"),
    PIKE("pike"),
    DRAVEN("draven"),
    DARIUS("darius"),
    GRAVE("grave"),
    RIVEN("riven");

    private final String name;

    MeetingRoom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
