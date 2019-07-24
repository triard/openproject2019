package com.triard.asus.openproject2019.utils;

import android.widget.Filter;

import com.triard.asus.openproject2019.adapter.ClubItemsAdapter;
import com.triard.asus.openproject2019.model.Club;

import java.util.ArrayList;

public class CustomFilter extends Filter {
    ClubItemsAdapter adapter;
    ArrayList<Club> filterList;


    public CustomFilter(ArrayList<Club> filterList, ClubItemsAdapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length()>0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<Club> filterClubs = new ArrayList<>();
            for(int i=0; i<filterList.size(); i++){
                if (filterList.get(i).getStrTeam().toUpperCase().contains(constraint)){
                    filterClubs.add(filterList.get(i));
                }
            }
            results.count = filterClubs.size();
            results.values = filterClubs;
        }else {
            results.count = filterList.size();
            results.values = filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.clubs = (ArrayList<Club>) results.values;
        adapter.notifyDataSetChanged();
    }
}
