package com.example.musclematefront.parsers;
import com.example.musclematefront.models.CurrentDose;
import com.example.musclematefront.models.Supplement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SupplementParser {
    public static List<Supplement> parseSupplements(JSONObject jsonResponse) {
        List<Supplement> supplements = new ArrayList<>();

        try {
            JSONArray supplementsArray = jsonResponse.getJSONArray("suplements");

            for (int i = 0; i < supplementsArray.length(); i++) {
                JSONObject supplementObject = supplementsArray.getJSONObject(i);

                if (supplementObject.has("name") && supplementObject.has("status") && supplementObject.has("currentDose")) {
                    String name = supplementObject.getString("name");
                    String status = supplementObject.getString("status");
                    JSONObject currentDoseJson = supplementObject.getJSONObject("currentDose");
                    CurrentDose currentDose = parseCurrentDose(currentDoseJson);

                    Supplement supplement = new Supplement(name, status, currentDose);
                    supplements.add(supplement);
                } else {
                    System.err.println("Supplement is missing required fields: name, status, or currentDose");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return supplements;
    }

    private static CurrentDose parseCurrentDose(JSONObject currentDoseJson) {
        try {
            int dose = currentDoseJson.getInt("dose");
            int frequency = currentDoseJson.getInt("frequency");
            String frequencyUnit = currentDoseJson.getString("frequencyUnit");
            String id = currentDoseJson.getString("_id");
            String dateString = currentDoseJson.getString("date");
            Date date = parseDate(dateString);

            return new CurrentDose(dose, frequency, frequencyUnit, id, date);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
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
