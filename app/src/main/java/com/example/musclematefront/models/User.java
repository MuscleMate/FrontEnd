package com.example.musclematefront.models;

import java.util.List;

public class User {
    private String _id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int height;
    private int weight;
    private String gender;
    private List<String> friends;
    private List<String> workouts;
    private List<String> tournaments;

    public User(String _id, String email, String password, String firstName, String lastName,
                String dateOfBirth, int height, int weight, String gender,
                List<String> friends, List<String> workouts, List<String> tournaments) {
        this._id = _id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.friends = friends;
        this.workouts = workouts;
        this.tournaments = tournaments;
    }
    // Getters and setters for each field
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<String> workouts) {
        this.workouts = workouts;
    }

    public List<String> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<String> tournaments) {
        this.tournaments = tournaments;
    }
}
