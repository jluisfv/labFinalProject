package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.bd.DatabaseHelper;


public class HorasExtrasDao extends DatabaseHelper {
    SQLiteDatabase database;
    ContentValues contentValues;
    public HorasExtrasDao(Context context) {
        super( context );
        database = super.getWritableDatabase();
    }
}
