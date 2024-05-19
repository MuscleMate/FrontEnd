package com.example.musclematefront.parsers;

import com.example.musclematefront.models.RankingChallenges;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RankingChallengesParser {
    public static List<RankingChallenges> parseRankingChallenges(JSONObject jsonResponse) {
        List<RankingChallenges> rankingEntries = new ArrayList<>();
        JSONArray rankingArray;

        try {
            if (jsonResponse.has("ranking")) {
                rankingArray = jsonResponse.getJSONArray("ranking");

                for (int i = 0; i < rankingArray.length(); i++) {
                    JSONObject rankingObject = rankingArray.getJSONObject(i);

                    String id = rankingObject.optString("_id");
                    String firstName = rankingObject.optString("firstName");
                    String lastName = rankingObject.optString("lastName");
                    int challengesCompleted = rankingObject.optInt("challengesCompleted");

                    if (id != null && firstName != null && lastName != null) {
                        RankingChallenges entry = new RankingChallenges(id, firstName, lastName, challengesCompleted);
                        rankingEntries.add(entry);
                    } else {
                        System.err.println("Ranking entry is missing required fields: _id, firstName, lastName");
                    }
                }
            } else {
                System.err.println("No 'ranking' array found in JSON response.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rankingEntries;
    }
}
