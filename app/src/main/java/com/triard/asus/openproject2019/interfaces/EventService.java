package com.triard.asus.openproject2019.interfaces;

import com.triard.asus.openproject2019.model.Event;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventService {
    //by club
    @GET("/club/{id}/events")
    Call<ArrayList<Event>> getEventsByClub(@Path("id") String id);

    //by date
    @GET("/events/{date}")
    Call<ArrayList<Event>> getEventsByDate(@Path("date") String date);
}
