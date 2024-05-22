package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Measurement;
import com.example.musclematefront.models.RP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RpParser {
    public static RP parseRP(JSONObject jsonResponse) {

        try {
            JSONObject rpObject = jsonResponse.getJSONObject("RP");

            RP rp = new RP(rpObject.optInt("level"),rpObject.optInt("levelPoints"),rpObject.optInt("levelPointsMax"),rpObject.optInt("totalPoints"));
            return rp;
            }
         catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}