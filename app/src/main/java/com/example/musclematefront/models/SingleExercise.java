package com.example.musclematefront.models;

public class SingleExercise {
    private String _id;
    private String title;


    public SingleExercise(String id, String title){
        this._id = id;
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
