package com.example.maru.ui;


import static com.example.maru.ui.MainActivity.MEETING_INFO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;
import com.example.maru.model.Meeting;

import java.util.ArrayList;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private ArrayList<Meeting> mMeetings;
    private final IOnMeetingDeleted deleteMeeting;
    private final Context mContext;

    public MeetingAdapter(ArrayList<Meeting> meetings, IOnMeetingDeleted deleteMeeting, Context context) {
        this.mContext = context;
        this.mMeetings = meetings;
        this.deleteMeeting = deleteMeeting;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMeetings(ArrayList<Meeting> meetings) {
        mMeetings = meetings;
        notifyDataSetChanged();
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
        Meeting meeting = mMeetings.get(position);
        holder.displayMeeting(meeting);

        holder.deleteButton.setOnClickListener(view -> deleteMeeting.onDeleteMeeting(meeting));

        holder.itemView.setOnClickListener(view -> {
            Intent detailMeetingActivityIntent = new Intent(mContext, DetailMeetingActivity.class);
            detailMeetingActivityIntent.putExtra(MEETING_INFO, meeting);
            mContext.startActivity(detailMeetingActivityIntent);
        });
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
        public final TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.avatar = itemView.findViewById(R.id.item_list_avatar);
            this.meetingInfo = itemView.findViewById(R.id.item_list_meeting_info);
            this.attendees = itemView.findViewById(R.id.item_list_attendees);
            this.deleteButton = itemView.findViewById(R.id.item_list_delete_button);
            this.date = itemView.findViewById(R.id.item_list_date);
        }

        @SuppressLint("SetTextI18n")
        public void displayMeeting(Meeting meeting) {
            String finalMonth = "" + meeting.getDate().getMonthValue();
            String finalDay = "" + meeting.getDate().getDayOfMonth();
            if (meeting.getDate().getMonthValue() < 10) {
                finalMonth = "0" + finalMonth;
            }
            if (meeting.getDate().getDayOfMonth() < 10) {
                finalDay = "0" + finalDay;
            }
            avatar.setImageResource(meeting.getMeetingRoom().getIconRes());
            date.setText(finalDay + "/" + finalMonth + "/" + meeting.getDate().getYear());
            meetingInfo.setText(meeting.getMeetingTopic() + " - " + meeting.getStringTime() + " - " + meeting.getMeetingRoom());
            attendees.setText(meeting.getAttendees().toString().replace("[", "").replace("]", ""));
        }
    }
}
