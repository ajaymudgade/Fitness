package com.example.fitness.Database;

import static com.example.fitness.util.Constants.FOOD_DATE;
import static com.example.fitness.util.Constants.FOOD_NAME;
import static com.example.fitness.util.Constants.FOOD_TABLE_NAME;
import static com.example.fitness.util.Constants.FOOD_TIME;
import static com.example.fitness.util.Constants.FOOD_UNIT;
import static com.example.fitness.util.Constants.FOOD_USERID;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.fitness.Model.foodModel;

import java.util.ArrayList;
import java.util.List;

public class foodQueryImplementation implements QueryContract.FoodQuery{

    private DbManager databaseHelper = DbManager.getInstance();

    @Override
    public void createFood(foodModel food, QueryResponse<Boolean> response) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesForFood(food);

        try {
            String id = String.valueOf(sqLiteDatabase.insertOrThrow(FOOD_TABLE_NAME, null, contentValues));
            if(!id.isEmpty()) {
                response.onSuccess(true);
                food.setUserId(id);
            }
            else
                response.onFailure("Failed to create food. Unknown Reason!");
        } catch (SQLiteException e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }

    }

    @Override
    public void readAllFood(QueryResponse<List<foodModel>> response) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        List<foodModel> foodList = new ArrayList<>();

        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(FOOD_TABLE_NAME, null, null, null, null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                do {
                    foodModel foodmod = getFoodFromCursor(cursor);
                    foodList.add(foodmod);
                } while (cursor.moveToNext());

                response.onSuccess(foodList);
            } else
                response.onFailure("There are no foods in database");

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
            if(cursor!=null)
                cursor.close();
        }

    }

    @Override
    public void updateFood(foodModel food, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesForFood(food);

        try {
            long rowCount = sqLiteDatabase.update(FOOD_TABLE_NAME, contentValues,
                    FOOD_USERID + " =? ", new String[]{String.valueOf(food.getUserId())});
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
    public void deleteFood(String userid, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            long rowCount = sqLiteDatabase.delete(FOOD_TABLE_NAME, FOOD_USERID + " =? ",
                    new String[]{String.valueOf(userid)});

            if(rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("Failed to delete food. Unknown reason");
        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }


    private ContentValues getContentValuesForFood(foodModel foodModel){
        ContentValues contentValues = new ContentValues();

        contentValues.put(FOOD_USERID, foodModel.getUserId());
        contentValues.put(FOOD_NAME, foodModel.getFoodName());
        contentValues.put(FOOD_UNIT, foodModel.getUnit());
        contentValues.put(FOOD_DATE, foodModel.getDate());
        contentValues.put(FOOD_TIME, foodModel.getTime());

        return contentValues;
    }

    private foodModel getFoodFromCursor(Cursor cursor){
        String userid = cursor.getString(cursor.getColumnIndexOrThrow(FOOD_USERID));
        String foodName = cursor.getString(cursor.getColumnIndexOrThrow(FOOD_NAME));
        String unit = cursor.getString(cursor.getColumnIndexOrThrow(FOOD_UNIT));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(FOOD_DATE));
        String time = cursor.getString(cursor.getColumnIndexOrThrow(FOOD_TIME));

        return new foodModel(userid, foodName, unit, date, time);
    }
}
