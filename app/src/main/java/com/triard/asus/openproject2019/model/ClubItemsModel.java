package com.triard.asus.openproject2019.model;

import java.util.Comparator;

public class ClubItemsModel {
    private String nama, asal;
    private String img;

    public ClubItemsModel(String nama, String asal, String img) {
        this.nama = nama;
        this.asal = asal;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }


    public  static final Comparator<ClubItemsModel> BY_TITTLE_ASCENDING = new Comparator<ClubItemsModel> ( ) {

        @Override
        public int compare(ClubItemsModel o1, ClubItemsModel o2) {
            return o1.getNama ().compareTo ( o2.getNama () );
        }
    };

    public  static final Comparator<ClubItemsModel> BY_TITTLE_DESCENDING = new Comparator<ClubItemsModel> ( ) {

        @Override
        public int compare(ClubItemsModel o1, ClubItemsModel o2) {
            return o2.getNama ().compareTo ( o1.getNama () );
        }
    };
}
