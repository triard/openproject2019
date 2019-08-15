package com.triard.asus.openproject2019;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.triard.asus.openproject2019.activities.ClubFavoriteActivity;
import com.triard.asus.openproject2019.activities.ClubsDetailActivity;
import com.triard.asus.openproject2019.activities.MainActivity;
import com.triard.asus.openproject2019.adapter.ClubFavoriteItemAdapter;
import com.triard.asus.openproject2019.adapter.ClubItemsAdapter;
import com.triard.asus.openproject2019.interfaces.ClubService;
import com.triard.asus.openproject2019.model.Club;
import com.triard.asus.openproject2019.network.ApiClient;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class favorit extends Fragment implements ClubFavoriteItemAdapter.Onclick{
    public static final String EXTRA_URL = "imageUrl";
    private Button mBtn_ckubFav;
    RecyclerView recyclerView;
    SharedPreferences preferences;
    private ClubService clubService;
    private ArrayList<Club> clubs = new ArrayList<>();
    private static final String TAG = MainActivity.class.getName();
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    ClubFavoriteItemAdapter clubFavoriteItemAdapter;

    public favorit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorit, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getActivity().findViewById( R.id.recylerview_detail_item);
        recyclerView.setLayoutManager ( new LinearLayoutManager ( getActivity().getApplicationContext() ) );

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MODE_SHARED", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("CLUB_FAVORITE", null);
        if (json==null) {
            Toast.makeText( getActivity().getApplicationContext(),"No Records Found!",Toast.LENGTH_SHORT ).show ();
        } else {
            Type type = new TypeToken<ArrayList<Club>>(){
            }.getType ();
            ArrayList<Club> arr = gson.fromJson ( json, type );
            clubFavoriteItemAdapter = new ClubFavoriteItemAdapter( getContext() ,arr, this );
            recyclerView.setAdapter ( clubFavoriteItemAdapter );
        }
    }




    //    intent untuk berpindah ke halaman detail club
    @Override
    public void clickItem(Club club) {
        Intent intent = new Intent(getActivity(), ClubsDetailActivity.class);
        intent.putExtra("idteam", club.getIdTeam());
        intent.putExtra("nama", club.getStrTeam());
        intent.putExtra("asal", club.getStrCountry());
        intent.putExtra("since", club.getIntFormedYear());
        intent.putExtra("nickname", club.getStrAlternate());
        intent.putExtra("idleague", club.getIdLeague());
        intent.putExtra("liga", club.getStrLeague());
        intent.putExtra("stadium", club.getStrStadiumLocation());
        intent.putExtra("desc", club.getStrDescriptionEN());
        intent.putExtra(EXTRA_URL, club.getStrTeamBadge());
        startActivity(intent);

    }

//    //    menu searching, untuk mencaru club sepakbola
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getActivity().getMenuInflater().inflate(R.menu.menu, menu);
//        final MenuItem item = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                clubItemsAdapter.getFilter().filter(s);
//                if (getActivity().fileList() != null) {
//                    Toast.makeText(getContext(), "No Records Found!", Toast.LENGTH_LONG).show();
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                clubItemsAdapter.getFilter().filter(s);
//                return false;
//            }
//        });
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_sort) {
//            Toast.makeText(getContext(), "Sort", Toast.LENGTH_SHORT).show();
//            ShowSortDialog();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void ShowSortDialog() {
//        String[] option = {"Ascending", "Descending"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Sort by");
//        builder.setIcon(R.drawable.ic_action_sort);
//        builder.setItems(option, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which == 0) {
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("Sort", "Ascending");
//                    editor.apply();
//                    getAllClub();
//                }
//                if (which == 1) {
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("Sort", "Descending");
//                    editor.apply();
//                    getAllClub();
//                }
//            }
//        });
//        builder.create().show();
//    }
}
