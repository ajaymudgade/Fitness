package com.example.fitness.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fitness.util.MyApp;

public class DbManager extends SQLiteOpenHelper {

    private static final String dbname = "Fitness.db";
    private static final String userMaster = "userMaster";
    private static final String foodTracker = "foodTracker";
    private static final String workoutTracker = "workoutTracker";
    private static final String waterTracker = "waterTracker";
    private static final String sleepTracker = "sleepTracker";

    private static DbManager dbManager;

    public DbManager() {
        super(MyApp.context, dbname, null, 1);
    }

    public static DbManager getInstance() {

        if (dbManager == null) {
            synchronized (DbManager.class){ //thread safe singleton
                if (dbManager == null)
                    dbManager = new DbManager();
            }
        }

        return dbManager;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable = "CREATE TABLE "+userMaster+"(id integer primary key autoincrement, name text, email text, mobile text, password text)";
        String foodTable = "CREATE TABLE "+foodTracker+"(id integer primary key autoincrement, userid text, foodName text, unit text, date text, time text)";
        String workoutTable = "CREATE TABLE "+workoutTracker+"(id integer primary key autoincrement, userid text, workoutName text, date text, time text)";
        String waterTable = "CREATE TABLE "+waterTracker+"(id integer primary key autoincrement, userid text, waterGlass text, date text, time text)";
        String sleepTable = "CREATE TABLE "+sleepTracker+"(id integer primary key autoincrement, userid text, sleepTime text, wakeupTime text, date text, time text)";

        db.execSQL(userTable);
        db.execSQL(foodTable);
        db.execSQL(workoutTable);
        db.execSQL(waterTable);
        db.execSQL(sleepTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+userMaster);
        db.execSQL("DROP TABLE IF EXISTS "+foodTracker);
        db.execSQL("DROP TABLE IF EXISTS "+workoutTracker);
        db.execSQL("DROP TABLE IF EXISTS "+waterTracker);
        db.execSQL("DROP TABLE IF EXISTS "+sleepTracker);
    }

    public void insertUserRecord(String p1, String p2, String p3, String p4){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name ", p1);
        cv.put("email ", p2);
        cv.put("mobile ", p3);
        cv.put("password ", p4);

        db.insert(userMaster, null, cv);
    }

    public void insertFoodRecord(String p1, String p2, String p3, String p4, String p5){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("userid ", p1);
        cv.put("foodName ", p2);
        cv.put("unit ", p3);
        cv.put("date ", p4);
        cv.put("time ", p5);

        db.insert(foodTracker, null, cv);
    }

    public void insertWorkoutRecord(String p1, String p2, String p3, String p4){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("userid ", p1);
        cv.put("workoutName ", p2);
        cv.put("date ", p3);
        cv.put("time ", p4);

        db.insert(workoutTracker, null, cv);
    }

    public void insertWaterRecord(String p1, String p2, String p3, String p4){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("userid ", p1);
        cv.put("waterGlass ", p2);
        cv.put("date ", p3);
        cv.put("time ", p4);

        db.insert(waterTracker, null, cv);
    }

    public void insertSleepRecord(String p1, String p2, String p3, String p4, String p5){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("userid ", p1);
        cv.put("sleepTime ", p2);
        cv.put("wakeupTime ", p3);
        cv.put("date ", p4);
        cv.put("time ", p5);

        db.insert(sleepTracker, null, cv);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }


/*    public ArrayList getUserData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+userMaster, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            arrayList.add(String.valueOf(cursor.getColumnIndex("name")));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public ArrayList getFoodData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+foodTracker, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            arrayList.add(String.valueOf(cursor.getColumnIndex("foodName")));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public ArrayList getWorkoutData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+workoutTracker, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            arrayList.add(String.valueOf(cursor.getColumnIndex("workoutName")));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public ArrayList getWaterData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+waterTracker, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            arrayList.add(String.valueOf(cursor.getColumnIndex("waterGlass")));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public ArrayList getSleepData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+sleepTracker, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            arrayList.add(String.valueOf(cursor.getColumnIndex("sleepTime")));
            cursor.moveToNext();
        }
        return arrayList;
    }*/


}
