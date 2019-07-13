package com.triard.asus.openproject2019.interfaces;

import com.triard.asus.openproject2019.model.Club;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClubService {
    @GET("/search_all_teams.php?l=English%20Premier%20League")
    Call<ArrayList<Club>> getAllClub();
}
