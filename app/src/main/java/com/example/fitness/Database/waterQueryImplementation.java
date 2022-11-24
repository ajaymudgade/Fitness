package com.example.fitness.Database;

import static com.example.fitness.util.Constants.WATER_DATE;
import static com.example.fitness.util.Constants.WATER_NAME;
import static com.example.fitness.util.Constants.WATER_TABLE_NAME;
import static com.example.fitness.util.Constants.WATER_TIME;
import static com.example.fitness.util.Constants.WATER_USERID;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.fitness.Model.waterModel;

import java.util.ArrayList;
import java.util.List;

public class waterQueryImplementation implements QueryContract.WaterQuery{
    private DbManager databaseHelper = DbManager.getInstance();
    @Override
    public void createWater(waterModel water, QueryResponse<Boolean> response) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesForWater(water);

        try {
            String id = String.valueOf(sqLiteDatabase.insertOrThrow(WATER_TABLE_NAME, null, contentValues));
            if(!id.isEmpty()) {
                response.onSuccess(true);
                water.setUserId(id);
            }
            else
                response.onFailure("Failed to create water. Unknown Reason!");
        } catch (SQLiteException e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }

    }

    @Override
    public void readAllWater(QueryResponse<List<waterModel>> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        List<waterModel> waterList = new ArrayList<>();

        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(WATER_TABLE_NAME, null, null, null, null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                do {
                    waterModel watermod = getWaterFromCursor(cursor);
                    waterList.add(watermod);
                } while (cursor.moveToNext());

                response.onSuccess(waterList);
            } else
                response.onFailure("There are no water in database");

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
            if(cursor!=null)
                cursor.close();
        }
    }

    @Override
    public void updateWater(waterModel water, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesForWater(water);

        try {
            long rowCount = sqLiteDatabase.update(WATER_TABLE_NAME, contentValues,
                    WATER_USERID + " =? ", new String[]{String.valueOf(water.getUserId())});
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
    public void deleteWater(String userid, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            long rowCount = sqLiteDatabase.delete(WATER_TABLE_NAME, WATER_USERID + " =? ",
                    new String[]{String.valueOf(userid)});

            if(rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("Failed to delete water. Unknown reason");
        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }

    private ContentValues getContentValuesForWater(waterModel waterModel){
        ContentValues contentValues = new ContentValues();

        contentValues.put(WATER_USERID, waterModel.getUserId());
        contentValues.put(WATER_NAME, waterModel.getWaterOfGlass());
        contentValues.put(WATER_DATE, waterModel.getDate());
        contentValues.put(WATER_TIME, waterModel.getTime());

        return contentValues;
    }

    private waterModel getWaterFromCursor(Cursor cursor){
        String userid = cursor.getString(cursor.getColumnIndexOrThrow(WATER_USERID));
        String waterName = cursor.getString(cursor.getColumnIndexOrThrow(WATER_NAME));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(WATER_DATE));
        String time = cursor.getString(cursor.getColumnIndexOrThrow(WATER_TIME));

        return new waterModel(userid, waterName, date, time);
    }

}
