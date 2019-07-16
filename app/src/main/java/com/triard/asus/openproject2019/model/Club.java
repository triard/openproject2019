package com.triard.asus.openproject2019.model;

import java.util.Comparator;

public class Club {
    private String strTeam, strBadgeTeam, strAlternate,intFormedYear,strLeague, strStadium ,strCountry,strDescriptionEN;


    public Club(String strTeam, String strBadgeTeam, String strAlternate, String intFormedYear, String strLeague, String strStadium, String strCountry, String strDescriptionEN) {
        this.strTeam = strTeam;
        this.strBadgeTeam = strBadgeTeam;
        this.strAlternate = strAlternate;
        this.intFormedYear = intFormedYear;
        this.strLeague = strLeague;
        this.strStadium = strStadium;
        this.strCountry = strCountry;
        this.strDescriptionEN = strDescriptionEN;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public void setStrTeam(String strTeam) {
        this.strTeam = strTeam;
    }

    public String getStrBadgeTeam() {
        return strBadgeTeam;
    }

    public void setStrBadgeTeam(String strBadgeTeam) {
        this.strBadgeTeam = strBadgeTeam;
    }

    public String getStrAlternate() {
        return strAlternate;
    }

    public void setStrAlternate(String strAlternate) {
        this.strAlternate = strAlternate;
    }

    public String getIntFormedYear() {
        return intFormedYear;
    }

    public void setIntFormedYear(String intFormedYear) {
        this.intFormedYear = intFormedYear;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public String getStrStadium() {
        return strStadium;
    }

    public void setStrStadium(String strStadium) {
        this.strStadium = strStadium;
    }

    public String getStrCountry() {
        return strCountry;
    }

    public void setStrCountry(String strCountry) {
        this.strCountry = strCountry;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public void setStrDescriptionEN(String strDescriptionEN) {
        this.strDescriptionEN = strDescriptionEN;
    }

    public  static final Comparator<Club> BY_TITTLE_ASCENDING = new Comparator<Club> ( ) {

        @Override
        public int compare(Club o1, Club o2) {
            return o1.getStrTeam ().compareTo ( o2.getStrTeam () );
        }
    };

    public  static final Comparator<Club> BY_TITTLE_DESCENDING = new Comparator<Club> ( ) {

        @Override
        public int compare(Club o1, Club o2) {
            return o2.getStrTeam ().compareTo ( o1.getStrTeam () );
        }
    };
}
