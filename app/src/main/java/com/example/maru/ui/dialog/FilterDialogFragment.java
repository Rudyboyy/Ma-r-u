package com.example.maru.ui.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.maru.R;
import com.example.maru.databinding.FragmentFilterDialogBinding;
import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.model.MeetingRoom;
import com.example.maru.service.MeetingApiService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class FilterDialogFragment extends DialogFragment {

    private FragmentFilterDialogBinding binding;
    private final MeetingApiService mMeetingApiService = DI.getMeetingApiService();
    private OnPositiveButtonClickListener mCallBack;
    private MeetingRoom mRoom;
    private String roomName;
    private Date mDate;
    private Date mTime;
    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;
    private String dateString;
    private String timeString;

    public Date format() {//todo comprend pas
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss", Locale.getDefault());
            mDate = formatter.parse(dateString + timeString); // DateString + TimeString
            return mDate;
        } catch (Exception exception) {
            return null;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        binding = FragmentFilterDialogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        builder.setView(view);
        initSpinner();
        initDate();
        initTime();

        builder.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
            mRoom = mMeetingApiService.getMeetingRoomByName(roomName);
            mCallBack.onButtonClicked(mRoom, mDate, mTime);
        });

        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss());

        return builder.create();
    }

    private void initTime() {
        binding.timePicker.setOnFocusChangeListener((view, hasFocus) -> {
            if (this.lastSelectedHour == -1) {
                final Calendar c = Calendar.getInstance();
                this.lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
                this.lastSelectedMinute = c.get(Calendar.MINUTE);
            }

            TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) { // todo prend que si on met l'heure exact
                    lastSelectedHour = hourOfDay;
                    lastSelectedMinute = minute;
                    Calendar cal = Calendar.getInstance();
                    cal.set(hourOfDay, minute);
                    timeString = hourOfDay + ":" + minute;
                    binding.timePicker.setText(timeString);
                    mTime = cal.getTime();
                }
            };

            TimePickerDialog timePickerDialog;

            timePickerDialog = new
                    TimePickerDialog(getActivity(),
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    timeSetListener, lastSelectedHour, lastSelectedMinute, true);// ou false avec am et pm

            if (hasFocus) {
                timePickerDialog.show();
            } else timePickerDialog.dismiss();
        });
    }


    private void initDate() {
        binding.datePicker.setOnFocusChangeListener((view, hasFocus) -> {
            DatePickerDialog datePickerDialog;
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(requireActivity(), (datePicker, year, month, day) -> {
                month++;
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);
                dateString = day + "/" + month + "/" + year;
                binding.datePicker.setText(dateString);
                mDate = cal.getTime();
            }, mYear, mMonth, mDay);
            if (hasFocus) {
                datePickerDialog.show();
            } else datePickerDialog.dismiss();
        });
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.rooms,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.roomList.setAdapter(adapter);

        binding.roomList.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        roomName = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                }
        );
    }

    public interface OnPositiveButtonClickListener {
        void onButtonClicked(MeetingRoom room, Date date, Date time);
    }

    private void createCallBack() {
        this.mCallBack = (OnPositiveButtonClickListener) getActivity();
    }

    @Override//todo test
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        createCallBack();
    }
}