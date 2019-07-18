package com.triard.asus.openproject2019.model;

import com.google.gson.annotations.SerializedName;

public class ClubContructor{
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


    public ClubContructor(){}

    public ClubContructor(String idTeam, String strTeam, String idLeague, String strLeague, String strStadiumLocation, String strTeamBadge) {
        this.idTeam = idTeam;
        this.strTeam = strTeam;
        this.idLeague = idLeague;
        this.strLeague = strLeague;
        this.strStadiumLocation = strStadiumLocation;
        this.strTeamBadge = strTeamBadge;
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
}
