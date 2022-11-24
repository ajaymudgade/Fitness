package com.example.fitness.Model;

public class waterModel {
    private String userId;
    private String waterOfGlass;
    private String date;
    private String time;

    public waterModel(String userId, String waterOfGlass, String date, String time) {
        this.userId = userId;
        this.waterOfGlass = waterOfGlass;
        this.date = date;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWaterOfGlass() {
        return waterOfGlass;
    }

    public void setWaterOfGlass(String waterOfGlass) {
        this.waterOfGlass = waterOfGlass;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
