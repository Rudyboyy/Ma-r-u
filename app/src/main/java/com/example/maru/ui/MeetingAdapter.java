package com.example.maru.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maru.R;
import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.service.MeetingApiService;

import java.util.ArrayList;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private ArrayList<Meeting> mMeetings;
    private final IOnMeetingDeleted deleteMeeting;

    public MeetingAdapter(ArrayList<Meeting> meetings, IOnMeetingDeleted deleteMeeting) {
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
        holder.deleteButton.setOnClickListener(view -> {
            deleteMeeting.onDeleteMeeting(meeting);
        });

/*        holder.itemView.setOnClickListener(view -> {

            Intent detailMeetingActivityIntent = new Intent(mContext, DetailMeetingActivity.class);
            detailMeetingActivityIntent.putExtra(MEETING_INFO, mMeetings);
            mContext.startActivity(detailMeetingActivityIntent);
        });*/
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
            attendees.setText(meeting.getAttendeesNames());
        }
    }
}
