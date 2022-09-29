package com.home.vod.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.home.vod.util.Constant;

/**
 * Created by Subhadarshani on 16-Aug-18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + Constant.TABLE_NAME + "("
                    + Constant.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constant.API_NAME + " TEXT,"
                    + Constant.PERMALINK + " TEXT,"
                    + Constant.RESPONSE + " TEXT,"
                    + Constant.LAST_FETCHED + " TEXT"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Constant.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    /*
    * @description This method helps in inserting api cache data to table.
    * */
    public void insertData(String apiName, String permalink, String response, String time) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        if(getCursorCount(apiName,permalink) != null && getCursorCount(apiName,permalink).getCount()==0){
            ContentValues values = new ContentValues();
            values.put(Constant.API_NAME, apiName);
            values.put(Constant.PERMALINK, permalink);
            values.put(Constant.RESPONSE, response);
            values.put(Constant.LAST_FETCHED, time);
            // insert row
            db.insert(Constant.TABLE_NAME, null, values);
            // close db connection

        }else{
            String where = null;
            String[] args;
            if (permalink != null && permalink.length() > 0) {
                where = Constant.API_NAME + " = ? AND " + Constant.PERMALINK + " = ?";
                args = new String[]{apiName, permalink};
            } else {
                where = Constant.API_NAME + " = ?";
                args = new String[]{apiName};
            }
            ContentValues values = new ContentValues();
            values.put(Constant.LAST_FETCHED, time);
            values.put(Constant.RESPONSE, response);
            db.update(Constant.TABLE_NAME, values, where, args);

        }

        db.close();


    }

    /*
   * @author Subhadarshani
   * @description This method helps in getting the response by giving apiName.
   * */
    public String getDataFromDB(String apiName, String permalink) {
        // get readable database as we are not inserting anything
         String response = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {Constant.RESPONSE};
        String where = null;
        String[] args;


        if (permalink != null && permalink.length() > 0) {
            where = Constant.API_NAME + " = ? AND " + Constant.PERMALINK + " = ?";
            args = new String[]{apiName, permalink};
        } else {
            where = Constant.API_NAME + " = ?";
            args = new String[]{apiName};
        }
        Cursor cursor = db.query(Constant.TABLE_NAME, columns, where, args, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        if(cursor.getCount()>0){
            response = cursor.getString(cursor.getColumnIndex(Constant.RESPONSE));
        }

        // close the db connection
        cursor.close();

        return response;
    }

    public String getLastFetchedAPITime(String apiName, String permalink) {
        // get readable database as we are not inserting anything
        String timeStamp = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {Constant.LAST_FETCHED};
        String where = null;
        String[] args;


        if (permalink != null && permalink.length() > 0) {
            where = Constant.API_NAME + " = ? AND " + Constant.PERMALINK + " = ?";
            args = new String[]{apiName, permalink};
        } else {
            where = Constant.API_NAME + " = ?";
            args = new String[]{apiName};
        }

        /*Cursor c = db.query(Constant.TABLE_NAME ,columns, " where " + Constant.API_NAME + " = ? AND " + Constant.PERMALINK +
                        " = ? ",
                new String[] { apiName, permalink, advertiserID, chefID });*/


        Cursor cursor = db.query(Constant.TABLE_NAME, columns, where, args, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        if(cursor.getCount()>0){
            timeStamp = cursor.getString(cursor.getColumnIndex(Constant.LAST_FETCHED));
        }

        // close the db connection
        cursor.close();

        return timeStamp;
    }
    public void deleteData(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from "+ Constant.TABLE_NAME);
    }
    public Cursor getCursorCount(String apiName,String permalink){
        String response = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {Constant.RESPONSE};
        String where = null;
        String[] args;


        if (permalink != null && permalink.length() > 0) {
            where = Constant.API_NAME + " = ? AND " + Constant.PERMALINK + " = ?";
            args = new String[]{apiName, permalink};
        } else {
            where = Constant.API_NAME + " = ?";
            args = new String[]{apiName};
        }
        Cursor cursor = db.query(Constant.TABLE_NAME, columns, where, args, null, null, null);
        return cursor;
    }
}
