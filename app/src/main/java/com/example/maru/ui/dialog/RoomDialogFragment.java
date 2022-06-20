package com.example.maru.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.maru.databinding.FragmentRoomDialogBinding;
import com.example.maru.model.MeetingRoom;

public class RoomDialogFragment extends DialogFragment {

    private FragmentRoomDialogBinding binding;

    public RoomDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        binding = FragmentRoomDialogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        builder.setView(view);
        AutoCompleteTextView roomList = binding.roomList;

        ArrayAdapter<Enum> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, MeetingRoom.values());
        roomList.setAdapter(adapter);
        roomList.setOnClickListener(view1 -> roomList.showDropDown());

/*        roomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {//todo test pour enlever le clavier
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InputMethodManager in = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(adapterView.getApplicationWindowToken(), 0);
            }
        });*/

        builder.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {});

        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss());

        return builder.create();
    }
}