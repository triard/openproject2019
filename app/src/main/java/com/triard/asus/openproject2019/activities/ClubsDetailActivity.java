package com.triard.asus.openproject2019.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.triard.asus.openproject2019.R;

import static com.triard.asus.openproject2019.activities.MainActivity.EXTRA_URL;

public class ClubsDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvTeam, tvCountry, tvAlternate,tvFormedYear,tvLeague, tvStadium ,tvDescriptionEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_club_detail );

        imageView  = findViewById(R.id.iv_img);
        tvTeam= findViewById(R.id.tv_nama);
        tvCountry= findViewById(R.id.tv_asal);
        tvAlternate = findViewById ( R.id.tv_nickname );
        tvFormedYear= findViewById(R.id.tv_since);
        tvLeague= findViewById(R.id.tv_league);
        tvStadium= findViewById(R.id.tv_stadium);
        tvDescriptionEN= findViewById(R.id.tv_desc);

        tvTeam.setText(getIntent().getStringExtra("nama"));
        tvCountry.setText(getIntent().getStringExtra("asal"));
        tvAlternate.setText ( getIntent ().getStringExtra ( "nickname" ) );
        tvFormedYear.setText(getIntent().getStringExtra("since"));
        tvLeague.setText(getIntent().getStringExtra("liga"));
        tvStadium.setText(getIntent().getStringExtra("stadium"));
        tvDescriptionEN.setText(getIntent().getStringExtra("desc"));
        String image = getIntent().getStringExtra(EXTRA_URL);
        Picasso.get().load(image).into(imageView);
    }
}
