package com.example.fitness.Database;

import static com.example.fitness.util.Constants.FOOD_TABLE_NAME;
import static com.example.fitness.util.Constants.FOOD_USERID;
import static com.example.fitness.util.Constants.WORKOUT_DATE;
import static com.example.fitness.util.Constants.WORKOUT_NAME;
import static com.example.fitness.util.Constants.WORKOUT_TABLE_NAME;
import static com.example.fitness.util.Constants.WORKOUT_TIME;
import static com.example.fitness.util.Constants.WORKOUT_USERID;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.fitness.Model.workoutModel;

import java.util.ArrayList;
import java.util.List;

public class WorkoutQueryImplementation implements QueryContract.WorkoutQuery{
    private DbManager databaseHelper = DbManager.getInstance();
    @Override
    public void createWorkout(workoutModel workout, QueryResponse<Boolean> response) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesForWorkout(workout);

        try {
            String id = String.valueOf(sqLiteDatabase.insertOrThrow(WORKOUT_TABLE_NAME, null, contentValues));
            if(!id.isEmpty()) {
                response.onSuccess(true);
                workout.setUserId(id);
            }
            else
                response.onFailure("Failed to create workout. Unknown Reason!");
        } catch (SQLiteException e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }

    }

    @Override
    public void readAllWorkout(QueryResponse<List<workoutModel>> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        List<workoutModel> workoutList = new ArrayList<>();

        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(WORKOUT_TABLE_NAME, null, null, null, null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                do {
                    workoutModel workmod = getWorkoutFromCursor(cursor);
                    workoutList.add(workmod);
                } while (cursor.moveToNext());

                response.onSuccess(workoutList);
            } else
                response.onFailure("There are no workouts in database");

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
            if(cursor!=null)
                cursor.close();
        }
    }

    @Override
    public void updateWorkout(workoutModel workout, QueryResponse<Boolean> response) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesForWorkout(workout);

        try {
            long rowCount = sqLiteDatabase.update(WORKOUT_TABLE_NAME, contentValues,
                    WORKOUT_USERID + " =? ", new String[]{String.valueOf(workout.getUserId())});
            if(rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("No data is updated at all");
        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }

    }

    @Override
    public void deleteWorkout(String userid, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            long rowCount = sqLiteDatabase.delete(WORKOUT_TABLE_NAME, WORKOUT_USERID + " =? ",
                    new String[]{String.valueOf(userid)});

            if(rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("Failed to delete workout. Unknown reason");
        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }

    private ContentValues getContentValuesForWorkout(workoutModel workoutModel){
        ContentValues contentValues = new ContentValues();

        contentValues.put(WORKOUT_USERID, workoutModel.getUserId());
        contentValues.put(WORKOUT_NAME, workoutModel.getWorkoutName());
        contentValues.put(WORKOUT_DATE, workoutModel.getDate());
        contentValues.put(WORKOUT_TIME, workoutModel.getTime());

        return contentValues;
    }

    private workoutModel getWorkoutFromCursor(Cursor cursor){
        String userid = cursor.getString(cursor.getColumnIndexOrThrow(WORKOUT_USERID));
        String workoutName = cursor.getString(cursor.getColumnIndexOrThrow(WORKOUT_NAME));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(WORKOUT_DATE));
        String time = cursor.getString(cursor.getColumnIndexOrThrow(WORKOUT_TIME));

        return new workoutModel(userid, workoutName, date, time);
    }
}
