package com.example.fitness.Model;

public class foodModel {
   private String userId;
   private String foodName;
   private String unit;
   private String date;
   private String time;

    public foodModel(String userId, String foodName, String unit, String date, String time) {
        this.userId = userId;
        this.foodName = foodName;
        this.unit = unit;
        this.date = date;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
