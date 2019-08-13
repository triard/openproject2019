package com.triard.asus.openproject2019.model;

public class Schedule {
    private String IdEvent;
    private String League;
    private String Event;
    private String Date;
    private String Time;

    public Schedule(String IdEvent,String League,String Event,String Date,String Time){
        this.IdEvent = IdEvent;
        this.League = League;
        this.Event = Event;
        this.Date = Date;
        this.Time = Time;
    }

    //  idevent
    public String getIdEvent() {
        return IdEvent;
    }

    public void setIdEvent(String IdEvent) {
        this.IdEvent = IdEvent;
    }

    //league
    public String getLeague() {
        return League;
    }

    public void setLeague(String League) {
        this.League = League;
    }

    //EVENT
    public String getEvent() {
        return Event;
    }

    public void setEvent(String Event) {
        this.Event = Event;
    }

    //DATE
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    //time
    public String getTime() {
        return Time;
    }

    public void setTime(String  Time) {
        this.Time = Time;
    }

}
