package com.triard.asus.openproject2019;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Toast;

import com.triard.asus.openproject2019.activities.ClubFavoriteActivity;
import com.triard.asus.openproject2019.activities.ClubsDetailActivity;
import com.triard.asus.openproject2019.activities.MainActivity;
import com.triard.asus.openproject2019.adapter.ClubItemsAdapter;
import com.triard.asus.openproject2019.interfaces.ClubService;
import com.triard.asus.openproject2019.model.Club;
import com.triard.asus.openproject2019.network.ApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment implements ClubItemsAdapter.Onclick {
    public static final String EXTRA_URL = "imageUrl";
    private Button mBtn_ckubFav;
    RecyclerView recyclerView;
    ClubItemsAdapter clubItemsAdapter;
    SharedPreferences preferences;
    private ClubService clubService;
    private ArrayList<Club> clubs = new ArrayList<>();
    private static final String TAG = MainActivity.class.getName();
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences = getActivity().getSharedPreferences ( "MY_DATA", MODE_PRIVATE );

        recyclerView = getActivity().findViewById ( R.id.recycler_view );
        recyclerView.setLayoutManager ( new LinearLayoutManager( getContext ( ), LinearLayoutManager.VERTICAL, false ) );

        //      membuat koneksi ke ClubService
        clubService = ApiClient.getClient ().create(ClubService.class);
        getAllClub();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    }


    //    add clubs to arraylist
    public void getAllClub() {
        Call<ArrayList<Club>> clubResponse = clubService.getAllClub();
        clubResponse.enqueue(new Callback<ArrayList<Club>>() {
            @Override
            public void onResponse(Call<ArrayList<Club>> call, Response<ArrayList<Club>> response) {
                clubs = response.body();
                Data();
            }

            @Override
            public void onFailure(Call<ArrayList<Club>> call, Throwable t) {
                Log.e(TAG, t.toString());
                String message = "Failed to get more data clubs, please check your connection.";
                Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Data() {

//        onclick
        clubItemsAdapter = new ClubItemsAdapter(getContext(), clubs, this);
        recyclerView.setAdapter(clubItemsAdapter);


        //        sorting
        String mShortSetting = preferences.getString("Sort", "Ascending");
        if (mShortSetting.equals("Ascending")) {
            Collections.sort(clubs, Club.BY_TITTLE_ASCENDING);
        } else if (mShortSetting.equals("Descending")) {
            Collections.sort(clubs, Club.BY_TITTLE_DESCENDING);
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

    //    menu searching, untuk mencaru club sepakbola
    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                clubItemsAdapter.getFilter().filter(s);
                if (getActivity().fileList() != null) {
                    Toast.makeText(getContext(), "No Records Found!", Toast.LENGTH_LONG).show();
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
        if (id == R.id.action_sort) {
            Toast.makeText(getContext(), "Sort", Toast.LENGTH_SHORT).show();
            ShowSortDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowSortDialog() {
        String[] option = {"Ascending", "Descending"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Sort by");
        builder.setIcon(R.drawable.ic_action_sort);
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort", "Ascending");
                    editor.apply();
                    getAllClub();
                }
                if (which == 1) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort", "Descending");
                    editor.apply();
                    getAllClub();
                }
            }
        });
        builder.create().show();
    }
}
