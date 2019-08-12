package com.triard.asus.openproject2019.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.triard.asus.openproject2019.R;

import static com.triard.asus.openproject2019.activities.ClubFavoriteActivity.EXTRA_URL;

public class ClubsFavoriteDetailActivity extends AppCompatActivity {

    ImageView imageViewfav;
    TextView tvTeamfav, tvCountryfav, tvAlternatefav,tvFormedYearfav,tvLeaguefav, tvStadiumfav ,tvDescriptionENfav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_clubs_favorite_detail );

        imageViewfav  = findViewById(R.id.iv_imgfav);
        tvTeamfav= findViewById(R.id.tv_namafav);
        tvCountryfav= findViewById(R.id.tv_asalfav);
        tvAlternatefav = findViewById ( R.id.tv_nicknamefav );
        tvFormedYearfav= findViewById(R.id.tv_sincefav);
        tvLeaguefav= findViewById(R.id.tv_leaguefav);
        tvStadiumfav= findViewById(R.id.tv_stadiumfav);
        tvDescriptionENfav= findViewById(R.id.tv_descfav);

        tvTeamfav.setText(getIntent().getStringExtra("namafav"));
        tvCountryfav.setText(getIntent().getStringExtra("asalfav"));
        tvAlternatefav.setText ( getIntent ().getStringExtra ( "nicknamefav" ) );
        tvFormedYearfav.setText(getIntent().getStringExtra("sincefav"));
        tvLeaguefav.setText(getIntent().getStringExtra("ligafav"));
        tvStadiumfav.setText(getIntent().getStringExtra("stadiumfav"));
        tvDescriptionENfav.setText(getIntent().getStringExtra("descfav"));
        String image = getIntent().getStringExtra(EXTRA_URL);
        Picasso.get().load(image).into(imageViewfav);
    }

    public void pindah(View view) {
        Intent intent = new Intent(ClubsFavoriteDetailActivity.this, ClubsFavoriteSchedule.class);
        startActivity(intent);
    }
}
