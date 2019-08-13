package com.triard.asus.openproject2019.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.triard.asus.openproject2019.R;
import com.triard.asus.openproject2019.adapter.ClubScheduleAdapter;
import com.triard.asus.openproject2019.model.Schedule;

import java.util.ArrayList;

public class ClubsFavoriteSchedule extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClubScheduleAdapter adapter;
    private ArrayList<Schedule> schedulesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_favorite_schedule);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.recylerview_detail_schedule);

        adapter = new ClubScheduleAdapter(schedulesArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ClubsFavoriteSchedule.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        schedulesArrayList = new ArrayList<>();
        schedulesArrayList.add(new Schedule("2001","friendly","ind vs jpn","12/04/2019","18.59"));
        schedulesArrayList.add(new Schedule("2002","friendly","ind vs jpn","12/04/2019","18.59"));
        schedulesArrayList.add(new Schedule("2003","friendly","ind vs jpn","12/04/2019","18.59"));
        schedulesArrayList.add(new Schedule("2004","friendly","ind vs jpn","12/04/2019","18.59"));
        schedulesArrayList.add(new Schedule("2005","friendly","ind vs jpn","12/04/2019","18.59"));
        schedulesArrayList.add(new Schedule("2006","friendly","ind vs jpn","12/04/2019","18.59"));
        schedulesArrayList.add(new Schedule("2007","friendly","ind vs jpn","12/04/2019","18.59"));
    }
}
