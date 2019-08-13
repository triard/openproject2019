package com.triard.asus.openproject2019.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.triard.asus.openproject2019.R;
import com.triard.asus.openproject2019.model.Schedule;

import java.util.ArrayList;

public class ClubScheduleAdapter extends RecyclerView.Adapter<ClubScheduleAdapter.ViewHolder> {

    private ArrayList<Schedule> schedules;

    public ClubScheduleAdapter(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.activity_club_favorite_schedule_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtIdEvent.setText(schedules.get(position).getIdEvent());
        holder.txtLeague.setText(schedules.get(position).getLeague());
        holder.txtEvent.setText(schedules.get(position).getEvent());
        holder.txtDate.setText(schedules.get(position).getDate());
        holder.txtTime.setText(schedules.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtIdEvent, txtLeague, txtEvent, txtDate, txtTime;

        public ViewHolder(View itemView) {
            super(itemView);
            txtIdEvent = (TextView) itemView.findViewById(R.id.TextViewIdEvent);
            txtLeague = (TextView) itemView.findViewById(R.id.TextViewLeague);
            txtEvent = (TextView) itemView.findViewById(R.id.TextViewEvent);
            txtDate = (TextView) itemView.findViewById(R.id.TextViewDate);
            txtTime = (TextView) itemView.findViewById(R.id.TextViewTime);
        }
    }
}


