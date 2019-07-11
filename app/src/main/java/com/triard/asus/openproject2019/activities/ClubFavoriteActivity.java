package com.triard.asus.openproject2019.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.triard.asus.openproject2019.R;
import com.triard.asus.openproject2019.adapter.ClubFavoriteItemAdapter;
import com.triard.asus.openproject2019.model.Club;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ClubFavoriteActivity<models> extends AppCompatActivity {

    RecyclerView recyclerView;
    ClubFavoriteItemAdapter clubFavoriteItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_club_favorite );

//        recyclerview
        recyclerView = findViewById( R.id.recylerview_detail_item );

        //        set properties
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );


        //start convert the string value to array
        SharedPreferences sharedPreferences = getSharedPreferences("MODE_SHARED", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("CLUB_FAVORITE", null);
        if(json.isEmpty ()){
            Toast.makeText ( ClubFavoriteActivity.this,"belum ada data",Toast.LENGTH_LONG ).show ();
        }else {
            Type type = new TypeToken<ArrayList<Club>>(){
            }.getType ();
            ArrayList<Club> arr = gson.fromJson ( json, type );

            clubFavoriteItemAdapter = new ClubFavoriteItemAdapter ( this,arr );
            recyclerView.setAdapter ( clubFavoriteItemAdapter );
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}