package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Challenge;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChallengeParser {
    public static List<Challenge> parseChallenges(JSONObject jsonResponse) {
        List<Challenge> challenges = new ArrayList<>();

        try {
            JSONArray challengesArray = jsonResponse.getJSONArray("challengesToDo");

            for (int i = 0; i < challengesArray.length(); i++) {
                JSONObject challengeObject = challengesArray.getJSONObject(i);

                if (challengeObject.has("_id") && challengeObject.has("title") && challengeObject.has("description") &&
                        challengeObject.has("duration") && challengeObject.has("goal") && challengeObject.has("target") &&
                        challengeObject.has("difficulty") && challengeObject.has("status") &&
                        challengeObject.has("startDate") && challengeObject.has("endDate") && challengeObject.has("__v")) {

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

                    Challenge challenge = new Challenge(id, title, description, duration, goal, target, difficulty, status, startDate, endDate, version);
                    challenges.add(challenge);
                } else {
                    System.err.println("Challenge is missing required fields.");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return challenges;
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