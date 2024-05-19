package com.example.musclematefront.models;

public class RankingChallenges {
    private String id;
    private String firstName;
    private String lastName;
    private int challengesCompleted;

    public RankingChallenges(String id, String firstName, String lastName, int challengesCompleted) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.challengesCompleted = challengesCompleted;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getChallengesCompleted() {
        return challengesCompleted;
    }

    public void setChallengesCompleted(int challengesCompleted) {
        this.challengesCompleted = challengesCompleted;
    }

}