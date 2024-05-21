package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Challenge;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SingleChallengeParser {
    public static Challenge parseSingleChallenge(JSONObject jsonResponse) {
        Challenge challenge = null;

        try {
            JSONObject challengeObject = jsonResponse.getJSONObject("challenge");

            if (challengeObject.has("_id") && challengeObject.has("title") && challengeObject.has("description") &&
                    challengeObject.has("duration") && challengeObject.has("goal") && challengeObject.has("target") &&
                    challengeObject.has("difficulty") && challengeObject.has("status") &&
                    challengeObject.has("startDate") && challengeObject.has("endDate") && challengeObject.has("__v") && challengeObject.has("exp")) {

                String id = challengeObject.getString("_id");
                String title = challengeObject.getString("title");
                String description = challengeObject.getString("description");
                int duration = challengeObject.getInt("duration");
                String goal = challengeObject.getString("goal");
                int target = challengeObject.getInt("target");
                String difficulty = challengeObject.getString("difficulty");
                String status = challengeObject.getString("status");
                Date startDate = parseDate(challengeObject.getString("startDate"));
                Date endDate = parseDate(challengeObject.getString("endDate"));
                int version = challengeObject.getInt("__v");
                int exp = challengeObject.getInt("exp");

                challenge = new Challenge(id, title, description, duration, goal, target, difficulty, status, startDate, endDate, version, exp);
            } else {
                System.err.println("Challenge is missing required fields.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return challenge;
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