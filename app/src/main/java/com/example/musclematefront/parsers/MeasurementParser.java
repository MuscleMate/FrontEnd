package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Measurement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeasurementParser {
    public static List<Measurement> parseMeasurements(JSONObject jsonResponse) {
        List<Measurement> measurements = new ArrayList<>();

        try {
            JSONArray measurementsArray = jsonResponse.getJSONArray("measurements");

            for (int i = 0; i < measurementsArray.length(); i++) {
                JSONObject measurementObject = measurementsArray.getJSONObject(i);

                if (measurementObject.has("name") && measurementObject.has("size")) {
                    String name = measurementObject.getString("name");
                    JSONObject sizeObject = measurementObject.getJSONObject("size");
                    int size = sizeObject.getInt("size");
                    String id = sizeObject.getString("_id");
                    String dateString = sizeObject.getString("date");
                    Date date = parseDate(dateString);

                    Measurement measurement = new Measurement(name, size, id, date);
                    measurements.add(measurement);
                } else {
                    System.err.println("Measurement is missing required fields: name or size");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return measurements;
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