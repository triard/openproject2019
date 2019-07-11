package com.triard.asus.openproject2019.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.triard.asus.openproject2019.R;
import com.triard.asus.openproject2019.model.ClubItemsModel;

import java.lang.reflect.Type;
import java.util.List;

public class ClubFavoriteActivity<models> extends AppCompatActivity {


    private ListView lvItemChosen;
    private ClubItemsModel models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_club_favorite );

        lvItemChosen = (ListView) findViewById( R.id.listview_detail_item );
        Context context;
        //start convert the string value to array
        SharedPreferences sharedPreferences = getSharedPreferences("MODE_SHARED", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("KEY_SHARED", null);
        if(json.isEmpty ()){
            Toast.makeText ( ClubFavoriteActivity.this,"belum ada data",Toast.LENGTH_LONG ).show ();
        }else {
            Type type = new TypeToken<List<ClubItemsModel >>(){
            }.getType ();
            List<ClubItemsModel> arr = gson.fromJson ( json, type );
            for(ClubItemsModel data: arr){
                ArrayAdapter<ClubItemsModel> adapter = new ArrayAdapter<ClubItemsModel> (this, android.R.layout.activity_list_item);
                adapter.notifyDataSetChanged();
            }
//            String[] listItem = gson.fromJson ( json, String[].class );
//            ArrayAdapter<ClubItemsModel> adapter = new ArrayAdapter<ClubItemsModel> (this, android.R.layout.activity_list_item);
//            lvItemChosen.setAdapter (adapter);
//            adapter.notifyDataSetChanged ();
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
