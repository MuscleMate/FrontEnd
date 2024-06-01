package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Exercise;
import com.example.musclematefront.models.SingleExercise;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SingleExerciseParser {

    public static List<SingleExercise> parseSingleExercise(JSONObject jsonResponse) {
        List<SingleExercise> exercises = new ArrayList<>();

        try{
            JSONArray exerciseArray = jsonResponse.getJSONArray("exercises");

            for(int i = 0; i< exerciseArray.length(); i++){
                JSONObject exerciseObject = exerciseArray.getJSONObject(i);
                if(exerciseObject.has("_id")){
                    String _id = exerciseObject.getString("_id");
                    String title = exerciseObject.getString("title");


                    SingleExercise exercise = new SingleExercise(_id,title);
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