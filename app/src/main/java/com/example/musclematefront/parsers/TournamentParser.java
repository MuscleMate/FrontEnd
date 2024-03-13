package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Tournament;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TournamentParser {

    public List<Tournament> parseTournaments(JSONObject json) {
        List<Tournament> tournaments = new ArrayList<>();
        try {
            JSONArray tournamentsArray = json.getJSONArray("tournaments");
            for (int i = 0; i < tournamentsArray.length(); i++) {
                JSONObject tournamentJson = tournamentsArray.getJSONObject(i);
                Tournament tournament = parseTournament(tournamentJson.toString());
                tournaments.add(tournament);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tournaments;
    }

    public Tournament parseTournament(String json) {
        return new Gson().fromJson(json, Tournament.class);
    }
}
