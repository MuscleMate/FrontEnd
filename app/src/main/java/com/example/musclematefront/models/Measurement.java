package com.example.musclematefront.models;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;

public class Measurement {
    private String name;
    private int size;
    private String id;
    private Date date;

    public Measurement(String name, int size, String id, Date date) {
        this.name = name;
        this.size = size;
        this.id = id;
        this.date = date;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    // Method to parse JSON to Measurement object
    public static Measurement fromJson(JSONObject jsonObject) throws JSONException {
        String name = jsonObject.getString("name");
        JSONObject sizeObject = jsonObject.getJSONObject("size");
        int size = sizeObject.getInt("size");
        String id = sizeObject.getString("_id");
        // Parse date string to Date object, assuming it's in ISO 8601 format
        Date date = new Date(sizeObject.getString("date"));
        return new Measurement(name, size, id, date);
    }

    // Method to convert Measurement object to JSON
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        JSONObject sizeObject = new JSONObject();
        sizeObject.put("size", size);
        sizeObject.put("_id", id);
        // Convert Date object to ISO 8601 formatted string
        sizeObject.put("date", date.toInstant().toString());
        jsonObject.put("size", sizeObject);
        return jsonObject;
    }
}