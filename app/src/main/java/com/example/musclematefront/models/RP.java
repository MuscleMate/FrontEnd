package com.example.musclematefront.models;

public class RP {
    private int level;
    private int levelPoints;
    private int levelPointsMax;
    private int totalPoints;
    public RP(int level, int levelPoints, int levelPointsMax,int totalPoints) {
        this.level = level;
        this.levelPoints = levelPoints;
        this.levelPointsMax = levelPointsMax;
        this.totalPoints = totalPoints;
    }

    // Getters and setters

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevelPoints() {
        return levelPoints;
    }

    public void setLevelPoints(int levelPoints) {
        this.levelPoints = levelPoints;
    }

    public int getLevelPointsMax() {
        return levelPointsMax;
    }

    public void setLevelPointsMax(int levelPointsMax) {
        this.levelPointsMax = levelPointsMax;
    }
    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
