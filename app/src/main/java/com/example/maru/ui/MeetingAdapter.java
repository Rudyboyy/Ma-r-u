package com.example.maru.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;
import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.service.MeetingApiService;

import java.util.ArrayList;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private final ArrayList<Meeting> mMeetings;
    private final MeetingApiService mMeetingApiService = DI.getMeetingApiService();

    public MeetingAdapter(ArrayList<Meeting> meetings) {
        this.mMeetings = meetings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.displayMeeting(mMeetings.get(position));
        holder.deleteButton.setOnClickListener(view -> mMeetingApiService.deleteMeeting(mMeetings.get(position)));//todo fonctionne pas
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView avatar;
        public final TextView meetingInfo;
        public final TextView attendees;
        public final ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.avatar = itemView.findViewById(R.id.item_list_avatar);
            this.meetingInfo = itemView.findViewById(R.id.item_list_meeting_info);
            this.attendees = itemView.findViewById(R.id.item_list_attendees);
            this.deleteButton = itemView.findViewById(R.id.item_list_delete_button);
        }

        public void displayMeeting(Meeting meeting) {
//            SimpleTimeZone simpleTimeZone = new SimpleTimeZone()
            meetingInfo.setText(meeting.getMeetingTopic()+" - "+meeting.getTime()+" - "+meeting.getMeetingRoom());
            attendees.setText(meeting.getAttendees());
        }
    }
}
