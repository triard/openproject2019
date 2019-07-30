package com.triard.asus.openproject2019.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.triard.asus.openproject2019.R;
import com.triard.asus.openproject2019.adapter.ClubItemsAdapter;
import com.triard.asus.openproject2019.interfaces.ClubService;
import com.triard.asus.openproject2019.model.Club;
import com.triard.asus.openproject2019.network.ApiClient;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ClubItemsAdapter.Onclick {
    public static final String EXTRA_URL = "imageUrl";
    private Button mBtn_ckubFav;
    RecyclerView recyclerView;
    ClubItemsAdapter clubItemsAdapter;
    SharedPreferences preferences;
    private ClubService clubService;
    private ArrayList<Club> clubs = new ArrayList<> (  ) ;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        recyclerView = findViewById ( R.id.recycler_view );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getApplicationContext ( ), LinearLayoutManager.VERTICAL, false ) );
        preferences = this.getSharedPreferences ( "MY_DATA", MODE_PRIVATE );
        mBtn_ckubFav = (Button) findViewById ( R.id.btn_ckubFav );
        mBtn_ckubFav.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent i = new Intent ( getApplicationContext ( ), ClubFavoriteActivity.class );
                startActivity ( i );
            }
        } );
        clubService = ApiClient.getClient ().create(ClubService.class);
        getAllClub ( );
    }

//    add clubs to arraylist
      public void getAllClub(){
          Call<ArrayList<Club>> clubResponse = clubService.getAllClub ();
          clubResponse.enqueue( new Callback<ArrayList<Club>> ( ) {
              @Override
              public void onResponse(Call<ArrayList<Club>> call, Response<ArrayList<Club>> response) {
                  clubs = response.body ();
                  Data();
              }

              @Override
              public void onFailure(Call<ArrayList<Club>> call, Throwable t) {
                  Log.e ( TAG, t.toString () );
                  String message = "Failed to get more data clubs, please check your connection.";
                  Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
              }
          });
    }

    private void Data() {

        clubItemsAdapter = new ClubItemsAdapter (this,clubs , this);
        recyclerView.setAdapter( clubItemsAdapter );

        String sortSetting = preferences.getString ( "Sort", "Ascending" );
        if(sortSetting.equals ( "Ascending" )){
            Collections.sort (clubs , Club.BY_TITTLE_ASCENDING );
        }else if(sortSetting.equals ( "Descending" )){
            Collections.sort (clubs , Club.BY_TITTLE_DESCENDING );
        }
    }

    @Override
    public void clickItem(Club club) {
        Intent intent = new Intent(MainActivity.this, ClubsDetailActivity.class);
        intent.putExtra("idteam", club.getIdTeam ());
        intent.putExtra("nama", club.getStrTeam ());
        intent.putExtra("asal", club.getStrCountry ());
        intent.putExtra("since", club.getIntFormedYear ());
        intent.putExtra("nickname", club.getStrAlternate ());
        intent.putExtra("idleague", club.getIdLeague ());
        intent.putExtra("liga", club.getStrLeague ());
        intent.putExtra("stadium", club.getStrStadiumLocation ());
        intent.putExtra("desc", club.getStrDescriptionEN ());
        intent.putExtra(EXTRA_URL, club.getStrTeamBadge ());
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                clubItemsAdapter.getFilter().filter(s);
                if(fileList()!=null){
                    Toast.makeText(MainActivity.this,"No Records Found!",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                clubItemsAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_sort){
            Toast.makeText(this, "Sort", Toast.LENGTH_SHORT).show();
            ShowSortDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowSortDialog() {
        String [] option = {"Ascending","Descending"};
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ("Sort by");
        builder.setIcon ( R.drawable.ic_action_sort );
        builder.setItems ( option, new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    SharedPreferences.Editor editor = preferences.edit ();
                    editor.putString ( "Sort", "Ascending" );
                    editor.apply ();
                    getAllClub ();
                }
                if(which == 1){
                    SharedPreferences.Editor editor = preferences.edit ();
                    editor.putString ( "Sort", "Descending" );
                    editor.apply ();
                    getAllClub ();
                }
            }
        } );
        builder.create ().show ();
    }
}