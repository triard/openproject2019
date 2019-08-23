package com.triard.asus.openproject2019.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.internal.bind.DateTypeAdapter;
import com.triard.asus.openproject2019.R;
import com.triard.asus.openproject2019.model.Event;
import com.triard.asus.openproject2019.model.Schedule;

import java.util.ArrayList;
import java.util.Date;

public class ClubScheduleAdapter extends RecyclerView.Adapter<ClubScheduleAdapter.ViewHolder> {

    Context context;
    public ArrayList<Event> schedules;

    public ClubScheduleAdapter(Context context, ArrayList<Event> schedules) {
        this.schedules = schedules;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.from(context).inflate(R.layout.activity_club_favorite_schedule_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Event event= schedules.get(position);
        holder.txtIdEvent.setText( event.getIdEvent());
        holder.txtLeague.setText( event.getStrLeague());
        holder.txtEvent.setText( event.getStrEvent());
        holder.txtDate.setText ( event.getDateEvent());
        holder.txtTime.setText( event.getStrTime());
        holder.txtHome.setText( event.getStrHomeTeam());
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtIdEvent, txtLeague, txtEvent, txtTime,txtHome,txtDate;


        public ViewHolder(View itemView) {
            super(itemView);

            txtLeague = (TextView) itemView.findViewById(R.id.TextViewLeague);
            txtEvent = (TextView) itemView.findViewById(R.id.TextViewEvent);
            txtDate = (TextView) itemView.findViewById(R.id.TextViewDate);
            txtTime = (TextView) itemView.findViewById(R.id.TextViewTime);
            txtHome  = (TextView) itemView.findViewById(R.id.TextViewHome);
        }
    }
}


