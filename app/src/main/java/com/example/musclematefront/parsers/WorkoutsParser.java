package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Workout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class WorkoutsParser {

    public static List<Workout> parseWorkouts(JSONObject jsonResponse) {
        List<Workout> workouts = new ArrayList<>();

        try{
            JSONArray workoutArray = jsonResponse.getJSONArray("workouts");

            for(int i = 0; i< workoutArray.length(); i++){
                JSONObject workoutObject = workoutArray.getJSONObject(i);
                if(workoutObject.has("_id")){
                     String _id = workoutObject.getString("_id");
                     String title = workoutObject.getString("title");
                     int duration = workoutObject.getInt("duration");
                     int exercises = workoutObject.getInt("exercises");
                     boolean favourite = workoutObject.getBoolean("favourite");
                     String date = workoutObject.getString("date");
                     String time = workoutObject.getString("time");



                Workout workout = new Workout(_id, title, duration, exercises ,favourite, date, time);
                workouts.add(workout);
                }else {
                    System.err.println("Notification is missing required fields: _id");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    return workouts;
    }
}

