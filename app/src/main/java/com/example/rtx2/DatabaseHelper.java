package com.example.rtx2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(name text, email text primary key, phone text, city text, state text, password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    //inserting
    public boolean insert(String name, String email, String phone, String city, String state, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("password",password);

        long ins = db.insert("user",null,contentValues);

        if(ins==-1){
            return false;
        }
        else {
            return true;
        }
    }

    //checking the email
    public Boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});

        if(cursor.getCount()>0){
            return false;
        }
        else{
            return true;
        }
    }

    //checking for login
    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and password=?", new String[]{email, password});

        if(cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }
}
