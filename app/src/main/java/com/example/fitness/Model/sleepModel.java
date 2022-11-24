package com.example.fitness.Model;

public class sleepModel {
    private String userId;
    private String sleepTime;
    private String wakeUpTime;
    private String date;
    private String time;

    public sleepModel(String userId, String sleepTime, String wakeUpTime, String date, String time) {
        this.userId = userId;
        this.sleepTime = sleepTime;
        this.wakeUpTime = wakeUpTime;
        this.date = date;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getWakeUpTime() {
        return wakeUpTime;
    }

    public void setWakeUpTime(String wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
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
