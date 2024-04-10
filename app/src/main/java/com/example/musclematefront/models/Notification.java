package com.example.musclematefront.models;
import java.util.Date;

public class Notification {
    private String senderID;
    private String receiverID;
    private String message;
    private boolean isRead;
    private String notificationID;
    private Date date;

    public Notification(String senderID, String receiverID, String message, boolean isRead, String notificationID, Date date) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.message = message;
        this.isRead = isRead;
        this.notificationID = notificationID;
        this.date = date;
    }

    // Getters and setters

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "senderID='" + senderID + '\'' +
                ", receiverID='" + receiverID + '\'' +
                ", message='" + message + '\'' +
                ", isRead=" + isRead +
                ", notificationID='" + notificationID + '\'' +
                ", date=" + date +
                '}';
    }
}