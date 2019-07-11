package com.triard.asus.openproject2019.utils;

import android.widget.Filter;

import com.triard.asus.openproject2019.adapter.ClubItemsAdapter;
import com.triard.asus.openproject2019.model.ClubItemsModel;

import java.util.ArrayList;

public class CustomFilter extends Filter {
    ClubItemsAdapter adapter;
    ArrayList<ClubItemsModel> filterList;

    public CustomFilter(ArrayList<ClubItemsModel> filterList, ClubItemsAdapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }



    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length()>0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<ClubItemsModel> filtereClubItemsModels = new ArrayList<>();
            for(int i=0; i<filterList.size();i++){
                if (filterList.get(i).getNama().toUpperCase().contains(constraint)){
                    filtereClubItemsModels.add(filterList.get(i));
                }
            }
            results.count = filtereClubItemsModels.size();
            results.values = filtereClubItemsModels;
        }else {

            results.count = filterList.size();
            results.values = filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.clubItemsModels = (ArrayList<ClubItemsModel>) results.values;
        adapter.notifyDataSetChanged();
    }
}
