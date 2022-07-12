package com.example.maru.model;

import androidx.annotation.DrawableRes;

import com.example.maru.R;

public enum MeetingRoom {

    AKALI("akali", R.drawable.akali),
    YASUO("yasuo", R.drawable.yasuo),
    SION("sion", R.drawable.sion),
    ILLAOI("illaoi", R.drawable.illaoi),
    AHRI("ahri", R.drawable.ahri),
    PYKE("pyke", R.drawable.pyke),
    DRAVEN("draven", R.drawable.draven),
    DARIUS("darius", R.drawable.darius),
    GRAVE("grave", R.drawable.grave),
    RIVEN("riven", R.drawable.riven);

    private final String name;

    @DrawableRes
    private final int iconRes;

    MeetingRoom(String name, int iconRes) {
        this.name = name;
        this.iconRes = iconRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
