package com.triard.asus.openproject2019.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.triard.asus.openproject2019.R;
import com.triard.asus.openproject2019.adapter.ClubFavoriteItemAdapter;
import com.triard.asus.openproject2019.adapter.ClubItemsAdapter;
import com.triard.asus.openproject2019.model.Club;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ClubFavoriteActivity<models> extends AppCompatActivity implements ClubFavoriteItemAdapter.Onclick {

    public static final String EXTRA_URL = "imageUrl";
    RecyclerView recyclerView;
    ClubFavoriteItemAdapter clubFavoriteItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_club_favorite );

//        recyclerview
        recyclerView = findViewById( R.id.recylerview_detail_item);

        //        set properties
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );

        //start convert the string value to array
        SharedPreferences sharedPreferences = getSharedPreferences("MODE_SHARED", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("CLUB_FAVORITE", null);
        if (json==null) {
            Toast.makeText( ClubFavoriteActivity.this,"No Records Found!",Toast.LENGTH_SHORT ).show ();
        } else {
            Type type = new TypeToken<ArrayList<Club>>(){
            }.getType ();
            ArrayList<Club> arr = gson.fromJson ( json, type );
            clubFavoriteItemAdapter = new ClubFavoriteItemAdapter ( this,arr,this );
            recyclerView.setAdapter ( clubFavoriteItemAdapter );
        }
    }

    @Override
    public void clickItem(Club clubfav) {
        Intent intent = new Intent ( ClubFavoriteActivity.this, ClubsFavoriteDetailActivity.class );
        intent.putExtra("namafav", clubfav.getStrTeam () );
        intent.putExtra("asalfav", clubfav.getStrCountry () );
        intent.putExtra("sincefav", clubfav.getIntFormedYear ());
        intent.putExtra("nicknamefav", clubfav.getStrAlternate ());
        intent.putExtra("ligafav", clubfav.getStrLeague ());
        intent.putExtra("stadiumfav", clubfav.getStrStadium ());
        intent.putExtra("descfav", clubfav.getStrDescriptionEN ());
        intent.putExtra( EXTRA_URL, clubfav.getStrBadgeTeam () );
        startActivity(intent);
    }
}