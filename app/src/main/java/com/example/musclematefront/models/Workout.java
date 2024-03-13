package com.example.musclematefront.models;
import java.util.Date;
import java.util.List;

public class Workout {
    private String _id;
    private String title;
    private String description;
    private int duration;
    private Date date;
    private List<String> exercises;
    private String user; // Assuming user is represented by their _id
    private List<String> equipment;
    private List<String> company; // Assuming company is represented by their _id
    private boolean favourite;
    private List<String> access; // Assuming access is represented by user _id

    // Constructor with all fields
    public Workout(String _id, String title, String description, int duration, Date date,
                   List<String> exercises, String user, List<String> equipment,
                   List<String> company, boolean favourite, List<String> access) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.date = date;
        this.exercises = exercises;
        this.user = user;
        this.equipment = equipment;
        this.company = company;
        this.favourite = favourite;
        this.access = access;
    }

    // Getters and setters for each field
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getExercises() {
        return exercises;
    }

    public void setExercises(List<String> exercises) {
        this.exercises = exercises;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }

    public List<String> getCompany() {
        return company;
    }

    public void setCompany(List<String> company) {
        this.company = company;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public List<String> getAccess() {
        return access;
    }

    public void setAccess(List<String> access) {
        this.access = access;
    }
}
