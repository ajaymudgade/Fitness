package com.example.fitness.Database;

import static com.example.fitness.util.Constants.USER_EMAIL;
import static com.example.fitness.util.Constants.USER_NAME;
import static com.example.fitness.util.Constants.USER_PASSWORD;
import static com.example.fitness.util.Constants.USER_PHONE;
import static com.example.fitness.util.Constants.USER_TABLE_NAME;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.fitness.Model.userModel;

import java.util.ArrayList;
import java.util.List;

public class userQueryImplementation implements QueryContract.UserQuery{

    private DbManager databaseHelper = DbManager.getInstance();

    @Override
    public void createUser(userModel user, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesForStudent(user);

        try {
            String id = String.valueOf(sqLiteDatabase.insertOrThrow(USER_TABLE_NAME, null, contentValues));
            if(!id.isEmpty()) {
                response.onSuccess(true);
                user.setName(id);
            }
            else
                response.onFailure("Failed to create user. Unknown Reason!");
        } catch (SQLiteException e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void readUser(String name, QueryResponse<userModel> response) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(USER_TABLE_NAME, null,
                    USER_NAME + " =? ", new String[]{String.valueOf(name)},
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()) {
                userModel userMod = getUserModelFromCursor(cursor);
                response.onSuccess(userMod);
            }
            else
                response.onFailure("user not found with this Name in database");

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
            if(cursor!=null)
                cursor.close();
        }

    }

    @Override
    public void readAllUser(QueryResponse<List<userModel>> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        List<userModel> userList = new ArrayList<>();

        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(USER_TABLE_NAME, null, null, null, null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                do {
                    userModel usermod = getUserModelFromCursor(cursor);
                    userList.add(usermod);
                } while (cursor.moveToNext());

                response.onSuccess(userList);
            } else
                response.onFailure("There are no student in database");

        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
            if(cursor!=null)
                cursor.close();
        }
    }

    @Override
    public void updateUser(userModel user, QueryResponse<Boolean> response) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesForStudent(user);

        try {
            long rowCount = sqLiteDatabase.update(USER_TABLE_NAME, contentValues,
                    USER_NAME + " =? ", new String[]{String.valueOf(user.getName())});
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
    public void deleteUser(String name, QueryResponse<Boolean> response) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            long rowCount = sqLiteDatabase.delete(USER_TABLE_NAME, USER_NAME + " =? ",
                    new String[]{String.valueOf(name)});

            if(rowCount>0)
                response.onSuccess(true);
            else
                response.onFailure("Failed to delete user. Unknown reason");
        } catch (Exception e){
            response.onFailure(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }

    }


    private ContentValues getContentValuesForStudent(userModel usermodel){
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_NAME, usermodel.getName());
        contentValues.put(USER_PHONE, usermodel.getMobile());
        contentValues.put(USER_EMAIL, usermodel.getEmail());
        contentValues.put(USER_PASSWORD, usermodel.getPassword());

        return contentValues;
    }

    private userModel getUserModelFromCursor(Cursor cursor){
        String name = cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME));
        String phone = cursor.getString(cursor.getColumnIndexOrThrow(USER_PHONE));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(USER_EMAIL));
        String password = cursor.getString(cursor.getColumnIndexOrThrow(USER_PASSWORD));

        return new userModel(name, phone, email, password);
    }

}
