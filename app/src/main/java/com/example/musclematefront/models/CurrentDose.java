package com.example.musclematefront.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class CurrentDose {
    private int dose;
    private int frequency;
    private String frequencyUnit;
    private String id;
    private Date date;

    public CurrentDose(int dose, int frequency, String frequencyUnit, String id, Date date) {
        this.dose = dose;
        this.frequency = frequency;
        this.frequencyUnit = frequencyUnit;
        this.id = id;
        this.date = date;
    }

    // Getters and setters

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(String frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}