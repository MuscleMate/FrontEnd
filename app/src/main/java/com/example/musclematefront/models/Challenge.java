package com.example.musclematefront.models;

import java.util.Date;

public class Challenge {
    private String id;
    private String title;
    private String description;
    private int duration;
    private String goal;
    private int target;
    private String difficulty;
    private String status;
    private Date startDate;
    private Date endDate;
    private int version;
    private int exp;

    public Challenge(String id, String title, String description, int duration, String goal, int target, String difficulty, String status, Date startDate, Date endDate, int version, int exp) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.goal = goal;
        this.target = target;
        this.difficulty = difficulty;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.version = version;
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

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", goal='" + goal + '\'' +
                ", target=" + target +
                ", difficulty='" + difficulty + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", version=" + version +
                '}';
    }
}
