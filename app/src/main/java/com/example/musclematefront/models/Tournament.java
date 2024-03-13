package com.example.musclematefront.models;

import java.util.Date;
import java.util.List;

public class Tournament {
    private String _id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<User> admins; // Assuming admins are represented by their _id
    private List<User> contestants; // Assuming contestants are represented by their _id
    private String determinant;

    // Constructor with all fields
    public Tournament(String _id, String name, String description, Date startDate,
                      Date endDate, List<User> admins, List<User> contestants,
                      String determinant) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.admins = admins;
        this.contestants = contestants;
        this.determinant = determinant;
    }

    // Getters and setters for each field
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    public List<User> getContestants() {
        return contestants;
    }

    public void setContestants(List<User> contestants) {
        this.contestants = contestants;
    }

    public String getDeterminant() {
        return determinant;
    }

    public void setDeterminant(String determinant) {
        this.determinant = determinant;
    }
}
