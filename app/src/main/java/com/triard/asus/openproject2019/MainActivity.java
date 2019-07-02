package com.triard.asus.openproject2019;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements MyAdapter.Onclick {
    public static final String EXTRA_URL = "imageUrl";

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = this.getSharedPreferences ( "MY_DATA", MODE_PRIVATE );

        recyclerView = findViewById(R.id.recycler_view);

        getPlayers ();
    }

//    add models to arraylist
    private void getPlayers(){
        ArrayList<Model> models = new ArrayList<>();


        Model p = new Model();
        p.setNama("Manchester United");
        p.setAsal("England");

        String url = p.setImg(R.drawable.soccer);
        Picasso.get ().load ( url );
        models.add(p);

        p = new Model();
        p.setNama("Barcelona");
        p.setAsal("Spain");
        p.setImg(R.drawable.ic_launcher_foreground);
        models.add(p);

        String mShortSetting = preferences.getString ( "Sort", "Ascending" );
        if(mShortSetting.equals ( "Ascending" )){
            Collections.sort ( models, Model.BY_TITTLE_ASCENDING );
        }else if(mShortSetting.equals ( "Descending" )){
            Collections.sort ( models, Model.BY_TITTLE_DESCENDING );
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        myAdapter = new MyAdapter(this, models, this);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void clickItem(Model model) {
        Intent intent = new Intent(MainActivity.this, activityDetail.class);
        intent.putExtra("nama", model.getNama());
        intent.putExtra("asal", model.getAsal());
        intent.putExtra(EXTRA_URL, model.getImg());
        startActivity(intent);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String s) {
                myAdapter.getFilter().filter(s);
                if(fileList()!=null){
                    Toast.makeText(MainActivity.this,"No Records Found!",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(MainActivity.this,"Records Found!",Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                myAdapter.getFilter().filter(s);
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
