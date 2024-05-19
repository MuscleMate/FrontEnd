package com.example.musclematefront.models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Workout {
    private String _id;
    private String title;
    private String description;
    private boolean ongoing;
    private List<Exercise> exercises;
    private int exerciseAmount;
    private int duration;
    private HashMap<String, String> user; // Assuming user is represented by their _id
    private List<String> equipment;
    private HashMap<String, String> company; // Assuming company is represented by their _id
    private boolean favourite;
    private HashMap<String, String> access; // Assuming access is represented by user _id
    private String date;
    private String time;




    // Constructor with all fields
    public Workout(String id, String title, String description, boolean ongoing, List<Exercise> exercises,
                   HashMap<String, String> user, List<String> equipment, HashMap<String, String> company, boolean favourite,
                   HashMap<String, String> access, String date, String time) {
        this._id = id;
        this.title = title;
        this.description = description;
        this.ongoing = ongoing;
        this.exercises = exercises;
        this.exerciseAmount = this.exercises.size();
        this.duration = 0;
        this.user = user;
        this.equipment = equipment;
        this.company = company;
        this.favourite = favourite;
        this.access = access;
        this.date = date;
        this.time = time;
    }

    public Workout(String _id, String title, int duration, int exercises, boolean favourite, String date, String time){

        this._id = _id;
        this.title = title;
        this.description = "";
        this.exercises = new ArrayList<>();
        this.exerciseAmount = exercises;
        this.duration = duration;
        this.user = new HashMap<String, String>();
        this.equipment = new ArrayList<>();
        this.company = new HashMap<String, String>();
        this.favourite = favourite;
        this.access = new HashMap<String, String>();
        this.date = date;
        this.time = time;
    }
    public Workout(){
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public HashMap<String, String> getUser() {
        return user;
    }

    public void setUser(HashMap<String, String> user) {
        this.user = user;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }

    public HashMap<String, String> getCompany() {
        return company;
    }

    public void setCompany(HashMap<String, String> company) {
        this.company = company;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public HashMap<String, String> getAccess() {
        return access;
    }

    public void setAccess(HashMap<String, String> access) {
        this.access = access;
    }

    public boolean isOngoing() {
        return ongoing;
    }

    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }

    public int getExerciseAmount() {
        return exerciseAmount;
    }

    public void setExerciseAmount(int exerciseAmount) {
        this.exerciseAmount = exerciseAmount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
