package com.triard.asus.openproject2019.model;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class Club {
    @SerializedName("idTeam")
    private String idTeam;
    @SerializedName("strTeam")
    private String strTeam;
    @SerializedName("idLeague")
    private String idLeague;
    @SerializedName("strLeague")
    private String strLeague;
    @SerializedName("strStadiumLocation")
    private String strStadiumLocation;
    @SerializedName("strTeamBadge")
    private String strTeamBadge;
//    @SerializedName("strAlternate")
//    private String strAlternate;
//    @SerializedName("intFormedYear")
//    private String intFormedYear;
//    @SerializedName("strCountry")
//    private String strCountry;
//    @SerializedName("strDescriptionEN")
//    private String strDescriptionEN;


    public Club() {
    }

//    , String strAlternate, String intFormedYear, String strCountry, String strDescriptionEN
    public Club(String idTeam, String strTeam, String idLeague, String strLeague, String strStadiumLocation, String strTeamBadge) {
        this.idTeam = idTeam;
        this.strTeam = strTeam;
        this.idLeague = idLeague;
        this.strLeague = strLeague;
        this.strStadiumLocation = strStadiumLocation;
        this.strTeamBadge = strTeamBadge;
//        this.strAlternate = strAlternate;
//        this.intFormedYear = intFormedYear;
//        this.strCountry = strCountry;
//        this.strDescriptionEN = strDescriptionEN;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setId(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public void setStrTeam(String strTeam) {
        this.strTeam = strTeam;
    }

    public String getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(String idLeague) {
        this.idLeague = idLeague;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public String getStrStadiumLocation() {
        return strStadiumLocation;
    }

    public void setStrStadiumLocation(String strStadiumLocation) {
        this.strStadiumLocation = strStadiumLocation;
    }

    public String getStrTeamBadge() {
        return strTeamBadge;
    }

    public void setStrTeamBadge(String strTeamBadge) {
        this.strTeamBadge = strTeamBadge;
    }


//
//    public String getStrAlternate() {
//        return strAlternate;
//    }
//
//    public void setStrAlternate(String strAlternate) {
//        this.strAlternate = strAlternate;
//    }
//
//    public String getIntFormedYear() {
//        return intFormedYear;
//    }
//
//    public void setIntFormedYear(String intFormedYear) {
//        this.intFormedYear = intFormedYear;
//    }
//
//    public String getStrCountry() {
//        return strCountry;
//    }
//
//    public void setStrCountry(String strCountry) {
//        this.strCountry = strCountry;
//    }
//
//    public String getStrDescriptionEN() {
//        return strDescriptionEN;
//    }
//
//    public void setStrDescriptionEN(String strDescriptionEN) {
//        this.strDescriptionEN = strDescriptionEN;
//    }

    public static final Comparator<Club> BY_TITTLE_ASCENDING = new Comparator<Club> ( ) {

        @Override
        public int compare(Club o1, Club o2) {
            return o1.getStrTeam ( ).compareTo ( o2.getStrTeam ( ) );
        }
    };

    public static final Comparator<Club> BY_TITTLE_DESCENDING = new Comparator<Club> ( ) {

        @Override
        public int compare(Club o1, Club o2) {
            return o2.getStrTeam ( ).compareTo ( o1.getStrTeam ( ) );
        }
    };
}
