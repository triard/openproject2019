package com.triard.asus.openproject2019.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.triard.asus.openproject2019.model.Club;
import com.triard.asus.openproject2019.utils.CustomFilter;
import com.triard.asus.openproject2019.R;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ClubItemsAdapter extends RecyclerView.Adapter<ClubItemsAdapter.ViewHolder> implements Filterable {

    Context mContext;
    public ArrayList<Club> clubs;
    ArrayList<Club> filterList;
    private Onclick listener;
    CustomFilter filter;
    ArrayList<Club> itemSelected = new ArrayList<>();

    public ClubItemsAdapter(Context mContext, ArrayList<Club> clubs, Onclick onclick) {
        this.mContext = mContext;
        this.clubs = clubs;
        this.listener = onclick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate( R.layout.activity_club_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder myHolder, int position) {
        final Club club = clubs.get(position);
        myHolder.mStrTeam.setText( club.getStrTeam ());
        myHolder.mStrCountry.setText( club.getStrCountry ());
        myHolder.mStrStadium.setText( club.getStrStadium ());
        myHolder.mStrLeague.setText( club.getStrLeague ());
        myHolder.mStrAlternate.setText( club.getStrAlternate());
        myHolder.mIntFormedYear.setText( club.getIntFormedYear ());
        myHolder.mStrDescriptionEN.setText( club.getStrDescriptionEN ());
        Picasso.get().load( club.getStrBadgeTeam ()).into(myHolder.mImgBadgeTeam);
        myHolder.bind(club,listener);

//        animasi list club
        Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
        myHolder.itemView.startAnimation(animation);

        final ArrayList<Club> listItemChosen = new ArrayList<> (  );
        SharedPreferences sharedPref = mContext.getSharedPreferences ( "MODE_SHARED", Context.MODE_PRIVATE );
        final SharedPreferences.Editor editor = sharedPref.edit ();
        final Gson gson =  new Gson ();

        myHolder.cbItem.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener ( ) {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    itemSelected.add( club );
                    String jsonString = gson.toJson(getSelectedString());
                    editor.putString( "CLUB_FAVORITE",jsonString);
                    editor.commit ();
                }else{
                    itemSelected.remove( club );
                    String jsonString = gson.toJson(getSelectedString());
                    editor.putString( "CLUB_FAVORITE",jsonString);
                    editor.commit ();
                }
            }
        } );
    }

    private ArrayList<Club> getSelectedString() {
        return itemSelected;    }


    @Override
    public int getItemCount() {
        return clubs.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new CustomFilter(filterList, this);
        }
        return filter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CheckBox cbItem;
        ImageView mImgBadgeTeam;
        TextView mStrTeam,mStrCountry,mStrAlternate,mIntFormedYear,mStrLeague, mStrStadium ,mStrDescriptionEN;

        public ViewHolder(View itemview) {
            super(itemview);
            this.mImgBadgeTeam = itemview.findViewById(R.id.ImageView);
            this.mStrTeam = itemview.findViewById(R.id.TextViewNama);
            this.mStrCountry = itemview.findViewById(R.id.TextViewAsal);
            this.mStrAlternate = itemview.findViewById(R.id.TextViewAlternate);
            this.mStrLeague = itemview.findViewById(R.id.TextViewLegaue);
            this.mStrStadium = itemview.findViewById(R.id.TextViewStadium);
            this.mIntFormedYear = itemview.findViewById(R.id.TextViewSince);
            this.mStrDescriptionEN = itemview.findViewById(R.id.TextViewDesc);
            this.cbItem = itemview.findViewById ( R.id.cb_favorite);
        }

        public void bind(final Club club, final Onclick onModelClick){


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onModelClick.clickItem( club );
                }
            });
        }
    }

    public interface Onclick {
        void clickItem(Club club);
    }
}
