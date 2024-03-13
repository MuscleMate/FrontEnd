package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Workout;
import com.google.gson.Gson;

public class WorkoutParser {

    public Workout parseWorkout(String json) {
        return new Gson().fromJson(json, Workout.class);
    }
}
