package com.example.musclematefront.parsers;

import static com.example.musclematefront.parsers.ExerciseParser.parseExercise;

import com.example.musclematefront.models.Exercise;
import com.example.musclematefront.models.Workout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class WorkoutParser {

    public static Workout parseWorkout(JSONObject jsonResponse) {
        Workout workout = new Workout();
        try {
            workout.set_id(jsonResponse.getString("_id"));
            workout.setTitle(jsonResponse.getString("title"));
            workout.setDescription(jsonResponse.getString("description"));
            workout.setOngoing(jsonResponse.getBoolean("ongoing"));
            workout.setExercises(parseExercise(jsonResponse.getJSONArray("exercises")));
            workout.setUser(new HashMap<String, String>() {{
                put(jsonResponse.getJSONObject("user").getString("_id"),
                jsonResponse.getJSONObject("user").getString("firstName"));
                            }}
            );
            workout.setEquipment(jsonToStringList(jsonResponse.getJSONArray("equipment")));
            workout.setCompany(jsonToStringMap(jsonResponse.getJSONArray("company")));
            workout.setFavourite(jsonResponse.getBoolean("favourite"));
            workout.setAccess(jsonToStringMap(jsonResponse.getJSONArray("access")));
            workout.setDate(jsonResponse.getString("date"));
            workout.setTime(jsonResponse.getString("time"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return workout;
    }

    private static List<String> jsonToStringList(JSONArray tab) {
        List<String> list = new ArrayList<>();
        try {
            for (int i = 0; i < tab.length(); i++) {
                list.add(tab.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static HashMap<String, String> jsonToStringMap(JSONArray tab){
        HashMap<String,String> map = new HashMap<>();
        try {
            for (int i = 0; i < tab.length(); i++) {
                JSONObject companionObject = tab.getJSONObject(i);
                if(companionObject.has("_id")){
                    map.put(
                            companionObject.getString("_id"),
                            companionObject.getString("firstName")
                    );

                }else{
                    System.err.println("Notification is missing required fields: _id");
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;

    }

}


