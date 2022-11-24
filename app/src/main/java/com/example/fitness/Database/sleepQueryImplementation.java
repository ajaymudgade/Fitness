package com.example.fitness.Database;

import static com.example.fitness.util.Constants.SLEEP_DATE;
import static com.example.fitness.util.Constants.SLEEP_SLEEP_TIME;
import static com.example.fitness.util.Constants.SLEEP_TABLE_NAME;
import static com.example.fitness.util.Constants.SLEEP_TIME;
import static com.example.fitness.util.Constants.SLEEP_USERID;
import static com.example.fitness.util.Constants.SLEEP_WAKEUP_TIME;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.fitness.Model.sleepModel;

import java.util.ArrayList;
import java.util.List;

public class sleepQueryImplementation implements QueryContract.SleepQuery{

    private DbManager databaseHelper = DbManager.getInstance();

    @Override
    public void createSleep(sleepModel sleep, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesForSleep(sleep);

        try {
            String id = String.valueOf(sqLiteDatabase.insertOrThrow(SLEEP_TABLE_NAME, null, contentValues));
            if(!id.isEmpty()) {
                response.onSuccess(true);
                sleep.setUserId(id);
            }
            else
                response.onFailure("Failed to create sleep. Unknown Reason!");
        } catch (SQLiteException e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void readAllSleep(QueryResponse<List<sleepModel>> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        List<sleepModel> sleepList = new ArrayList<>();

        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(SLEEP_TABLE_NAME, null, null, null, null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                do {
                    sleepModel sleepmod = getSleepFromCursor(cursor);
                    sleepList.add(sleepmod);
                } while (cursor.moveToNext());

                response.onSuccess(sleepList);
            } else
                response.onFailure("There are no sleep in database");

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
            if(cursor!=null)
                cursor.close();
        }
    }

    @Override
    public void updateSleep(sleepModel sleep, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesForSleep(sleep);

        try {
            long rowCount = sqLiteDatabase.update(SLEEP_TABLE_NAME, contentValues,
                    SLEEP_USERID + " =? ", new String[]{String.valueOf(sleep.getUserId())});
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
    public void deleteSleep(String userid, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            long rowCount = sqLiteDatabase.delete(SLEEP_TABLE_NAME, SLEEP_USERID + " =? ",
                    new String[]{String.valueOf(userid)});

            if(rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("Failed to delete sleep. Unknown reason");
        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }


    private ContentValues getContentValuesForSleep(sleepModel sleepModel){
        ContentValues contentValues = new ContentValues();

        contentValues.put(SLEEP_USERID, sleepModel.getUserId());
        contentValues.put(SLEEP_SLEEP_TIME, sleepModel.getSleepTime());
        contentValues.put(SLEEP_WAKEUP_TIME, sleepModel.getWakeUpTime());
        contentValues.put(SLEEP_DATE, sleepModel.getDate());
        contentValues.put(SLEEP_TIME, sleepModel.getTime());

        return contentValues;
    }

    private sleepModel getSleepFromCursor(Cursor cursor){
        String userid = cursor.getString(cursor.getColumnIndexOrThrow(SLEEP_USERID));
        String sleepTime = cursor.getString(cursor.getColumnIndexOrThrow(SLEEP_SLEEP_TIME));
        String wakeupTime = cursor.getString(cursor.getColumnIndexOrThrow(SLEEP_WAKEUP_TIME));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(SLEEP_DATE));
        String time = cursor.getString(cursor.getColumnIndexOrThrow(SLEEP_TIME));

        return new sleepModel(userid, sleepTime, wakeupTime, date, time);
    }
}
