package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Friend;
import com.example.musclematefront.models.RP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FriendsParser {
    public static List<Friend> parseFriends(JSONObject jsonResponse) {
        List<Friend> friends = new ArrayList<>();

        try {
            if (jsonResponse.has("friends")) {
                JSONArray friendsArray = jsonResponse.getJSONArray("friends");

                for (int i = 0; i < friendsArray.length(); i++) {
                    JSONObject friendObject = friendsArray.getJSONObject(i);

                    String id = friendObject.optString("_id");
                    String email = friendObject.optString("email");
                    String firstName = friendObject.optString("firstName");
                    String lastName = friendObject.optString("lastName");

                    JSONObject rpObject = friendObject.optJSONObject("RP");
                    RP rp = null;
                    if (rpObject != null) {
                        int level = rpObject.optInt("level");
                        int levelPoints = rpObject.optInt("levelPoints");
                        int levelPointsMax = rpObject.optInt("levelPointsMax");
                        rp = new RP(level, levelPoints, levelPointsMax);
                    } else {
                        int level = 0;
                        int levelPoints = 0;
                        int levelPointsMax = 0;
                        rp = new RP(level, levelPoints, levelPointsMax);
                    }

                    if (id != null && email != null && firstName != null && lastName != null && rp != null) {
                        Friend friend = new Friend(id, email, firstName, lastName, rp);
                        friends.add(friend);
                    } else {
                        System.err.println("Friend is missing required fields: _id, email, firstName, lastName, or RP");
                    }
                }
            } else {
                System.err.println("No 'friends' array found in JSON response.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return friends;
    }
}