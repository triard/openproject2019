package com.triard.asus.openproject2019.adapter;

import android.content.Context;
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
import com.triard.asus.openproject2019.model.Club;

import java.util.ArrayList;

public class ClubFavoriteItemAdapter extends RecyclerView.Adapter<ClubFavoriteItemAdapter.ViewHolder> {

    Context context;
    public ArrayList<Club> club_fav;
    private Onclick onclickfav;

    public ClubFavoriteItemAdapter(Context context, ArrayList<Club> club_fav, Onclick onclick){
        this.context = context;
        this.club_fav = club_fav;
        this.onclickfav = onclick;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from ( context ).inflate ( R.layout.activity_club_favorite_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int position) {
        final Club club = club_fav.get(position);
        viewHolder.vStrIdteam.setText( club.getIdTeam ());
        viewHolder.vStrIdLiga.setText( club.getIdLeague ());
        viewHolder.vStrTeam.setText( club.getStrTeam ());
        viewHolder.vStrCountry.setText( club.getStrCountry ());
        viewHolder.vStrAlternate.setText( club.getStrAlternate ());
        viewHolder.vStrLeague.setText( club.getStrLeague ());
        viewHolder.vStrStadium.setText( club.getStrStadiumLocation ());
        viewHolder.vIntFormedYear.setText( club.getIntFormedYear ());
        viewHolder.vStrDescriptionEN.setText( club.getStrDescriptionEN ());
        Picasso.get().load( club.getStrTeamBadge ()).into(viewHolder.vImgBadgeTeam);
        viewHolder.bind( club, onclickfav );

        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewHolder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return club_fav.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView vImgBadgeTeam;
        TextView vStrIdLiga,vStrIdteam,vStrTeam,vStrCountry,vStrAlternate,vIntFormedYear,vStrLeague, vStrStadium ,vStrDescriptionEN;

        public ViewHolder( View itemView) {
            super ( itemView );
            this.vImgBadgeTeam = itemView.findViewById ( R.id.ImageViewFavorite );
            this.vStrIdteam = itemView.findViewById ( R.id.TextViewIdTeamfav );
            this.vStrIdLiga = itemView.findViewById ( R.id.TextViewIdLigafav );
            this.vStrTeam = itemView.findViewById ( R.id.TextViewNamaFavorite );
            this.vStrCountry = itemView.findViewById ( R.id.TextViewAsalFavorite );
            this.vStrAlternate = itemView.findViewById(R.id.TextViewAlternate);
            this.vStrLeague = itemView.findViewById(R.id.TextViewLegaue);
            this.vStrStadium = itemView.findViewById(R.id.TextViewStadium);
            this.vIntFormedYear = itemView.findViewById(R.id.TextViewSince);
            this.vStrDescriptionEN = itemView.findViewById(R.id.TextViewDesc);
        }

        public void bind(final Club club, final Onclick listener) {
            itemView.setOnClickListener ( new View.OnClickListener ( ) {
                @Override
                public void onClick(View v) {
                    listener.clickItem (club);
                }
            } );
        }
    }
    public interface Onclick {
        void clickItem(Club club);
    }
}