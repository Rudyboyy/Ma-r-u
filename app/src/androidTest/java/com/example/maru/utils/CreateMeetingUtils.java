package com.example.maru.utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBackUnconditionally;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.contrib.PickerActions.setTime;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.example.maru.R;
import com.google.android.material.textfield.TextInputEditText;

public class CreateMeetingUtils {


    public static void createMeeting(@Nullable String topic, int day, int month, int years, int hour, int minute, String room, @Nullable String attendees) {

        onView(ViewMatchers.withId(R.id.addButton)).perform(ViewActions.click());
        if (topic != null) {
            onView(ViewMatchers.withId(R.id.textTopic)).perform(ViewActions.typeText(topic));
            pressBackUnconditionally();
        }
        onView(ViewMatchers.withId(R.id.textDate)).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(years, month, day));
        onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.textTime)).perform(ViewActions.click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(hour, minute));
        onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click());
        onView(withId(R.id.spinnerRoom)).perform(click());
        onView(ViewMatchers.withText(room)).perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.textAttendees)).perform(ViewActions.typeText(attendees));
        pressBackUnconditionally();
        onView(ViewMatchers.withId(R.id.saveButton)).perform(ViewActions.click());
    }
}
