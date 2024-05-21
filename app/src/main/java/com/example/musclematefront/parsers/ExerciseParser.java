package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Exercise;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExerciseParser {

    public static List<Exercise> parseExercise(JSONArray jsonResponse) {
        List<Exercise> exercises = new ArrayList<>();

        try{
            JSONArray exerciseArray = jsonResponse;

            for(int i = 0; i< exerciseArray.length(); i++){
                JSONObject exerciseObject = exerciseArray.getJSONObject(i);
                if(exerciseObject.has("_id")){
                    String _id = exerciseObject.getString("_id");
                    String title = exerciseObject.getString("title");
                    String description = exerciseObject.getString("description");
                    String type = exerciseObject.getString("type");
                    String difficulty = exerciseObject.getString("difficulty");
                    int sets = exerciseObject.getInt("sets");
                    int reps = exerciseObject.getInt("reps");
                    int weight = exerciseObject.getInt("weight");
                    int duration = exerciseObject.getInt("duration");
                    int __v = exerciseObject.getInt("__v");

                    Exercise exercise = new Exercise(_id,title,description,type,difficulty,sets,reps,weight,duration,__v);
                    exercises.add(exercise);
                }else{
                    System.err.println("Notification is missing required fields: _id");
                }
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    return exercises;
    }
}