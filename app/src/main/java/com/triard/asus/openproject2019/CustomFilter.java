package com.triard.asus.openproject2019;

import android.widget.Filter;

import com.triard.asus.openproject2019.adapter.MyAdapter;
import com.triard.asus.openproject2019.model.Model;

import java.util.ArrayList;

public class CustomFilter extends Filter {
    MyAdapter adapter;
    ArrayList<Model> filterList;

    public CustomFilter(ArrayList<Model> filterList, MyAdapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }



    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length()>0){
            constraint = constraint.toString().toUpperCase();
            ArrayList<Model> filtereModels = new ArrayList<>();
            for(int i=0; i<filterList.size();i++){
                if (filterList.get(i).getNama().toUpperCase().contains(constraint)){
                    filtereModels.add(filterList.get(i));
                }
            }
            results.count = filtereModels.size();
            results.values = filtereModels;
        }else {

            results.count = filterList.size();
            results.values = filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.models = (ArrayList<Model>) results.values;
        adapter.notifyDataSetChanged();
    }
}
