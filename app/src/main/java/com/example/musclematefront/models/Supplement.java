package com.example.musclematefront.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Supplement {
    private String name;
    private String status;
    private CurrentDose currentDose;

    public Supplement(String name, String status, CurrentDose currentDose) {
        this.name = name;
        this.status = status;
        this.currentDose = currentDose;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CurrentDose getCurrentDose() {
        return currentDose;
    }

    public void setCurrentDose(CurrentDose currentDose) {
        this.currentDose = currentDose;
    }

}