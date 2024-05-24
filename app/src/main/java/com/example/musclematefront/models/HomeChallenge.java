package com.example.musclematefront.models;

import java.util.Date;

public class HomeChallenge {
    private String id;
    private String title;
    private int timeToEnd;
    private int exp;

    public HomeChallenge(String id, String title, int timeToEnd, int exp) {
        this.id = id;
        this.title = title;
        this.timeToEnd=timeToEnd;
        this.exp = exp;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public int getTimeToEnd() {
        return timeToEnd;
    }

    public void setTimeToEnd(int timeToEnd) {
        this.timeToEnd = timeToEnd;
    }
}
