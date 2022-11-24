package com.example.fitness.Model;

public class tableRowCount {
    private long userRow;
    private long foodRow;
    private long sleepRow;
    private long waterRow;
    private long workoutRow;

    public tableRowCount(long userRow, long foodRow, long sleepRow, long waterRow, long workoutRow) {
        this.userRow = userRow;
        this.foodRow = foodRow;
        this.sleepRow = sleepRow;
        this.waterRow = waterRow;
        this.workoutRow = workoutRow;
    }

    public long getUserRow() {
        return userRow;
    }

    public void setUserRow(long userRow) {
        this.userRow = userRow;
    }

    public long getFoodRow() {
        return foodRow;
    }

    public void setFoodRow(long foodRow) {
        this.foodRow = foodRow;
    }

    public long getSleepRow() {
        return sleepRow;
    }

    public void setSleepRow(long sleepRow) {
        this.sleepRow = sleepRow;
    }

    public long getWaterRow() {
        return waterRow;
    }

    public void setWaterRow(long waterRow) {
        this.waterRow = waterRow;
    }

    public long getWorkoutRow() {
        return workoutRow;
    }

    public void setWorkoutRow(long workoutRow) {
        this.workoutRow = workoutRow;
    }
}
