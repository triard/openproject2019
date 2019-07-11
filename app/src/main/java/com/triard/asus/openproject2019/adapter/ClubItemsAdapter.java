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
import com.triard.asus.openproject2019.model.ClubItemsModel;
import com.triard.asus.openproject2019.utils.CustomFilter;
import com.triard.asus.openproject2019.R;

import java.util.ArrayList;

public class ClubItemsAdapter extends RecyclerView.Adapter<ClubItemsAdapter.ViewHolder> implements Filterable {

    Context mContext;
    public ArrayList<ClubItemsModel> clubItemsModels;
    ArrayList<ClubItemsModel> filterList;
    private Onclick listener;
    CustomFilter filter;
    ArrayList<ClubItemsModel> itemSelected = new ArrayList<>();

    public ClubItemsAdapter(Context mContext, ArrayList<ClubItemsModel> clubItemsModels, Onclick onclick) {
        this.mContext = mContext;
        this.clubItemsModels = clubItemsModels;
        this.listener = onclick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate( R.layout.activity_club_list_item, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder myHolder, int position) {
        final ClubItemsModel clubItemsModel = clubItemsModels.get(position);

        myHolder.mNama.setText( clubItemsModel.getNama());
        myHolder.mAsal.setText( clubItemsModel.getAsal());
        Picasso.get().load( clubItemsModel.getImg()).into(myHolder.mImageIv);
        myHolder.bind( clubItemsModel, listener);

        Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
        myHolder.itemView.startAnimation(animation);

        final ArrayList<ClubItemsModel> listItemChosen = new ArrayList<> (  );
        SharedPreferences sharedPref = mContext.getSharedPreferences ( "MODE_SHARED", Context.MODE_PRIVATE );
        final SharedPreferences.Editor editor = sharedPref.edit ();
        final Gson gson =  new Gson ();

        myHolder.cbItem.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener ( ) {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    itemSelected.add( clubItemsModel );
                    String jsonString = gson.toJson(getSelectedString());
                    editor.putString( "KEY_SHARED",jsonString);
                    editor.commit ();
                    System.out.println("JSON : " + jsonString);
                    System.out.println("SIZE : " + listItemChosen.size());
                }else{
                    itemSelected.remove( clubItemsModel );
                    String jsonString = gson.toJson(getSelectedString());
                    editor.putString( "KEY_SHARED",jsonString);
                    editor.commit ();
                    System.out.println("JSON : " + jsonString);
                    System.out.println("SIZE : " + listItemChosen.size());
                }
            }
        } );


    }

    private ArrayList<ClubItemsModel> getSelectedString() {
        return itemSelected;    }


    @Override
    public int getItemCount() {
        return clubItemsModels.size();
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
        ImageView mImageIv;
        TextView mNama, mAsal;

        public ViewHolder(@NonNull View itemview) {
            super(itemview);
            this.mImageIv = itemview.findViewById(R.id.ImageView);
            this.mNama = itemview.findViewById(R.id.TextViewNama);
            this.mAsal = itemview.findViewById(R.id.TextViewAsal);
            this.cbItem = itemview.findViewById ( R.id.cb_favorite);
        }

        public void bind(final ClubItemsModel clubItemsModel, final Onclick onModelClick){


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onModelClick.clickItem( clubItemsModel );
                }
            });
        }
    }

    public interface Onclick{
        void clickItem(ClubItemsModel clubItemsModel);
    }
}
