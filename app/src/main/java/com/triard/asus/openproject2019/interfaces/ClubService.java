package com.triard.asus.openproject2019.interfaces;

import com.triard.asus.openproject2019.model.Club;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClubService {
    @GET("/club")
    Call<ArrayList<Club>> getAllClub();
}
