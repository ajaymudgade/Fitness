package com.example.fitness.Model;

public class workoutModel {
    private String userId;
    private String workoutName;
    private String date;
    private String time;

    public workoutModel(String userId, String workoutName, String date, String time) {
        this.userId = userId;
        this.workoutName = workoutName;
        this.date = date;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
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
