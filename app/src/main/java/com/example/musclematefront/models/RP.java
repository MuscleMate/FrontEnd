package com.example.musclematefront.models;

public class RP {
    private int level;
    private int levelPoints;
    private int levelPointsMax;

    public RP(int level, int levelPoints, int levelPointsMax) {
        this.level = level;
        this.levelPoints = levelPoints;
        this.levelPointsMax = levelPointsMax;
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
}
