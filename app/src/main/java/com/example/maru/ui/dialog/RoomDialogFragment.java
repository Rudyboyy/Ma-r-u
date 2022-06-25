package com.example.maru.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.maru.R;
import com.example.maru.databinding.FragmentRoomDialogBinding;
import com.example.maru.di.DI;
import com.example.maru.model.MeetingRoom;
import com.example.maru.service.MeetingApiService;

public class RoomDialogFragment extends DialogFragment {

    private FragmentRoomDialogBinding binding;
    private final MeetingApiService mMeetingApiService = DI.getMeetingApiService();
    private OnPositiveButtonClickListener mCallBack;
    MeetingRoom mRoom;
    String roomName;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        binding = FragmentRoomDialogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        builder.setView(view);

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
                    public void onNothingSelected(AdapterView<?> adapterView) { }
                }
        );

        builder.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
            mRoom = mMeetingApiService.getMeetingRoomByName(roomName);
            mCallBack.onButtonClicked(mRoom);
        });

        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss());

        return builder.create();
    }

    public interface OnPositiveButtonClickListener {
        void onButtonClicked(MeetingRoom room);
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