package com.triard.asus.openproject2019.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.triard.asus.openproject2019.model.ClubItemsModel;
import com.triard.asus.openproject2019.adapter.ClubItemsAdapter;
import com.triard.asus.openproject2019.R;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements ClubItemsAdapter.Onclick {
    public static final String EXTRA_URL = "imageUrl";
    private Button mBtn_ckubFav;

    RecyclerView recyclerView;
    ClubItemsAdapter clubItemsAdapter;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        preferences = this.getSharedPreferences ( "MY_DATA", MODE_PRIVATE );
        recyclerView = findViewById(R.id.recycler_view);
        mBtn_ckubFav =(Button)findViewById ( R.id.btn_ckubFav );

        mBtn_ckubFav.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent i = new Intent ( getApplicationContext (), ClubFavoriteActivity.class );
                startActivity ( i );
            }
        } );

        getPlayers ();
    }

//    add clubItemsModels to arraylist
    private void getPlayers(){
        ArrayList<ClubItemsModel> clubItemsModels = new ArrayList<>();

        ClubItemsModel p = new ClubItemsModel ("MU", "England", "https://upload.wikimedia.org/wikipedia/hif/f/ff/Manchester_United_FC_crest.png");
        clubItemsModels.add(p);

        p = new ClubItemsModel ("Barca", "Spain", "https://png.pngtree.com/element_our/png_detail/20181109/barcelona-logo-png_235045.jpg");
        clubItemsModels.add(p);

//        sorting
        String mShortSetting = preferences.getString ( "Sort", "Ascending" );
        if(mShortSetting.equals ( "Ascending" )){
            Collections.sort ( clubItemsModels, ClubItemsModel.BY_TITTLE_ASCENDING );
        }else if(mShortSetting.equals ( "Descending" )){
            Collections.sort ( clubItemsModels, ClubItemsModel.BY_TITTLE_DESCENDING );
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        onclick
        clubItemsAdapter = new ClubItemsAdapter (this, clubItemsModels, this);
        recyclerView.setAdapter( clubItemsAdapter );
    }

//    intent untuk berpindah ke halaman detail club
    @Override
    public void clickItem(ClubItemsModel clubItemsModel) {
        Intent intent = new Intent(MainActivity.this, ClubsDetailActivity.class);
        intent.putExtra("nama", clubItemsModel.getNama());
        intent.putExtra("asal", clubItemsModel.getAsal());
        intent.putExtra(EXTRA_URL, clubItemsModel.getImg());
        startActivity(intent);

    }

//    menu searching, untuk mencaru club sepakbola
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
                    getPlayers ();
                }
                if(which == 1){
                    SharedPreferences.Editor editor = preferences.edit ();
                    editor.putString ( "Sort", "Descending" );
                    editor.apply ();
                    getPlayers ();
                }
            }
        } );
        builder.create ().show ();
    }
}
