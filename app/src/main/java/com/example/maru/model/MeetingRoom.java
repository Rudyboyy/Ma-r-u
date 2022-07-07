package com.example.maru.model;

import androidx.annotation.DrawableRes;

import com.example.maru.R;

public enum MeetingRoom {

    AKALI("akali", R.drawable.ahri),
    YASUO("yasuo", R.drawable.ahri),
    SION("sion", R.drawable.ahri),
    ILLAOI("illaoi", R.drawable.ahri),
    AHRI("ahri", R.drawable.ahri),
    PIKE("pike", R.drawable.ahri),
    DRAVEN("draven", R.drawable.ahri),
    DARIUS("darius", R.drawable.ahri),
    GRAVE("grave", R.drawable.ahri),
    RIVEN("riven", R.drawable.ahri);

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
