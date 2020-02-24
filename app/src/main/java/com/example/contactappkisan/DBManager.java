package com.example.contactappkisan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class DBManager extends SQLiteOpenHelper {

    public static final String dbName = "mydb";

    public static final int version = 1;

    public DBManager(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS CONTACTS(_id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,OTP TEXT,TIME TEXT)";
        db.execSQL(sql);
    }

    public void insertData(String name, String otp, String time, SQLiteDatabase sqLiteDatabase) {
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("OTP", otp);
        values.put("TIME", time);
        sqLiteDatabase.insert("CONTACTS", null, values);
    }

    public Cursor Query(String[] projection, String selection, String[] selectionArgs, String sorOrder){
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables("CONTACTS");
        Cursor cursor = qb.query(getWritableDatabase(), projection, selection, selectionArgs, null, null, sorOrder);
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
