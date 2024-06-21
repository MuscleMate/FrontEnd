package com.example.musclematefront.parsers;

import com.example.musclematefront.models.Notification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class NotificationParser {

    public static List<Notification> parseNotifications(JSONObject jsonResponse) {
        List<Notification> notifications = new ArrayList<>();

        try {
            JSONArray notificationsArray = jsonResponse.getJSONArray("notifications");

            for (int i = 0; i < notificationsArray.length(); i++) {
                JSONObject notificationObject = notificationsArray.getJSONObject(i);

                if (notificationObject.has("senderID") && notificationObject.has("receiverID") && notificationObject.has("message")) {
                    String senderID = notificationObject.getString("senderID");
                    String receiverID = notificationObject.getString("receiverID");
                    String message = notificationObject.getString("message");
                    boolean isRead = notificationObject.getBoolean("isRead");
                    String notificationID = notificationObject.getString("_id");
                    Date date = parseDate(notificationObject.getString("date"));

                    Notification notification = new Notification(senderID, receiverID, message, isRead, notificationID, date);
                    notifications.add(notification);
                } else {
                    System.err.println("Notification is missing required fields: senderID, receiverID, or message");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return notifications;
    }

    private static Date parseDate(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE d MMM HH:mm", Locale.US);

        try {
            Date date = inputFormat.parse(dateString);
            String formattedDateString = outputFormat.format(date);
            return outputFormat.parse(formattedDateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}