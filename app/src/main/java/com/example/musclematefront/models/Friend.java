package com.example.musclematefront.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Friend {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private RP rp;

    public Friend(String id, String email, String firstName, String lastName, RP rp) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rp = rp;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public RP getRp() {
        return rp;
    }

    public void setRp(RP rp) {
        this.rp = rp;
    }

    public static Friend fromJson(JSONObject json) throws JSONException {
        String id = json.getString("_id");
        String email = json.getString("email");
        String firstName = json.getString("firstName");
        String lastName = json.getString("lastName");
        JSONObject rpJson = json.getJSONObject("RP");
        RP rp = new RP(rpJson.getInt("level"), rpJson.getInt("levelPoints"), rpJson.getInt("levelPointsMax"));
        return new Friend(id, email, firstName, lastName, rp);
    }
}