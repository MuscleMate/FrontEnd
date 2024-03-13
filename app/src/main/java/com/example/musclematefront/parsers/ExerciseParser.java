package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Exercise;
import com.google.gson.Gson;

public class ExerciseParser {

    public Exercise parseExercise(String json) {
        return new Gson().fromJson(json, Exercise.class);
    }
}