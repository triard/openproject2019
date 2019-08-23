package com.triard.asus.openproject2019.activities;

import android.content.SharedPreferences;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.triard.asus.openproject2019.R;

import com.triard.asus.openproject2019.adapter.ClubScheduleAdapter;
import com.triard.asus.openproject2019.interfaces.EventService;

import com.triard.asus.openproject2019.model.Event;
import com.triard.asus.openproject2019.network.ApiClient;


import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubsFavoriteSchedule extends AppCompatActivity {
    RecyclerView recyclerView;
    ClubScheduleAdapter clubScheduleAdapter;
    SharedPreferences preference;

    private EventService eventService;
    private ArrayList<Event> events = new ArrayList<> (  ) ;
    private static final String tag = ClubsFavoriteSchedule.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_favorite_schedule);

        recyclerView = findViewById( R.id.recylerview_detail_schedule);
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );
        preference = this.getSharedPreferences ( "MY_DATA", MODE_PRIVATE );

        eventService = ApiClient.getClient ().create(EventService.class);
        getEventsByClub();

    }
    //   tampil semua jadwal

    public void getEventsByClub(){
        Call<ArrayList<Event>> clubResponse = eventService.getEventsByClub ();
        clubResponse.enqueue( new Callback<ArrayList<Event>>( ) {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                events = response.body ();
                Data();
            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                Log.e ( tag, t.toString () );
                String message = "Failed to get more events club, please check your connection.";
                Toast.makeText(ClubsFavoriteSchedule.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Data() {

        clubScheduleAdapter = new ClubScheduleAdapter(this,events, this);
        recyclerView.setAdapter( clubScheduleAdapter );

    }
}
