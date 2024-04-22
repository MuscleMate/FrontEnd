package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Workout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class WorkoutParser {

    public static List<Workout> parseWorkout(JSONObject jsonResponse) {
        List<Workout> workouts = new ArrayList<>();

        try{
            JSONArray workoutArray = jsonResponse.getJSONArray("workouts");

            for(int i = 0; i< workoutArray.length(); i++){
                JSONObject workoutObject = workoutArray.getJSONObject(i);
                if(workoutObject.has("_id")){
                     String _id = workoutObject.getString("_id");
                     String title = workoutObject.getString("title");
                     String description = workoutObject.getString("description");
                     int duration = workoutObject.getInt("duration");
                     Date date = parseDate(workoutObject.getString("date"));
                     List<String> exercises; // TODO: Add implementation after discusing what exercises really is
                     String user = workoutObject.getString("user");
                     List<String> equipment;// TODO: Add implementation after discusing what equipment really is
                     List<String> company;// TODO: Add implementation after discusing what company really is
                     boolean favourite = workoutObject.getBoolean("favourite");
                     List<String> access;// TODO: Add implementation after discusing what access really is
                     List<String> temporaryEmptyList = new ArrayList<String>();

                Workout workout = new Workout(_id, title, description, duration, date, temporaryEmptyList, user, temporaryEmptyList, temporaryEmptyList,favourite, temporaryEmptyList);
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
    private static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

