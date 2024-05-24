package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Challenge;
import com.example.musclematefront.models.HomeChallenge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeChallengeParser {
    public static List<HomeChallenge> parseHomeChallenges(JSONObject jsonResponse) {
        List<HomeChallenge> challenges = new ArrayList<>();

        try {
            JSONArray challengesArray = jsonResponse.getJSONArray("challenges");

            for (int i = 0; i < challengesArray.length(); i++) {
                JSONObject challengeObject = challengesArray.getJSONObject(i);

                if (challengeObject.has("_id")){

                    String id = challengeObject.getString("_id");
                    String title = challengeObject.getString("title");
                   int timeToEnd = challengeObject.getInt("timeToEnd");
                    int exp = 0;
                    HomeChallenge challenge = new HomeChallenge(id, title, timeToEnd,exp);
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