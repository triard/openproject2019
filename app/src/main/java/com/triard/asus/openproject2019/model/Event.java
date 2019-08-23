package com.triard.asus.openproject2019.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Event {
    @SerializedName("idEvent")
    private int idEvent;
    @SerializedName("strEvent")
    private String strEvent;
    @SerializedName("strLeague")
    private String strLeague;
    @SerializedName("idHomeTeam")
    private int idHomeTeam;
    @SerializedName("strHomeTeam")
    private String strHomeTeam;
    @SerializedName("idAwayTeam")
    private int idAwayTeam;
    @SerializedName("strAwayTeam")
    private String strAwayTeam;
    @SerializedName("dateEvent")
    private String dateEvent;
    @SerializedName("strTime")
    private String strTime;


    public Event(int idEvent, String strEvent, String strLeague, int idHomeTeam, String strHomeTeam, int idAwayTeam, String strAwayTeam, Date dateEvent, String strTime){
        this.idEvent = idEvent;
        this.strEvent = strEvent;
        this.strLeague = strLeague;
        this.idHomeTeam = idHomeTeam;
        this.strHomeTeam = strHomeTeam;
        this.idAwayTeam = idAwayTeam;
        this.strAwayTeam = strAwayTeam;
        this.dateEvent = dateEvent;
        this.strTime = strTime;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setId(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getStrEvent() { return strEvent; }

    public void setStrEvent(String strEvent) {
        this.strEvent = strEvent;
    }

    public String getStrLeague() { return strLeague; }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public int getIdHomeTeam() {
        return idHomeTeam;
    }

    public void setIdHomeTeam(int idHomeTeam) {
        this.idHomeTeam = idHomeTeam;
    }

    public String getStrHomeTeam() {
        return strHomeTeam;
    }

    public void setStrHomeTeam(String strHomeTeam) {
        this.strHomeTeam = strHomeTeam;
    }

    public int getIdAwayTeam() {
        return idAwayTeam;
    }

    public void setIdAwayTeam(int idAwayTeam) {
        this.idAwayTeam = idAwayTeam;
    }

    public String getStrAwayTeam() {
        return strAwayTeam;
    }

    public void setStrAwayTeam(String strAwayTeam) {
        this.strAwayTeam = strAwayTeam;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }


    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

}
