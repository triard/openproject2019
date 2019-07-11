package com.triard.asus.openproject2019.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.triard.asus.openproject2019.R;
import com.triard.asus.openproject2019.activities.ClubFavoriteActivity;
import com.triard.asus.openproject2019.model.Club;

import java.util.ArrayList;

public class ClubFavoriteItemAdapter extends RecyclerView.Adapter<ClubFavoriteItemAdapter.ViewHolder> {

    Context context;
    public ArrayList<Club> club_fav;

    public ClubFavoriteItemAdapter(Context context, ArrayList<Club> club_fav) {
        this.context = context;
        this.club_fav = club_fav;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.activity_club_favorite_list_item, viewGroup, false);
        return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int position) {
        final Club club = club_fav.get(position);
        viewHolder.vNama.setText( club.getNama());
        viewHolder.vAsal.setText( club.getAsal());
        Picasso.get().load( club.getImg()).into(viewHolder.vImageIv);

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewHolder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return club_fav.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView vImageIv;
        TextView vNama, vAsal;

        public ViewHolder( View itemView) {
            super ( itemView );
            this.vImageIv = itemView.findViewById ( R.id.ImageViewFavorite );
            this.vNama = itemView.findViewById ( R.id.TextViewNamaFavorite );
            this.vAsal = itemView.findViewById ( R.id.TextViewAsalFavorite );
        }
    }
}
