package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.bd.DatabaseHelper;
import com.example.project.entidad.EdificioEntity;
import com.example.project.entidad.LaboratorioEntity;

import java.util.ArrayList;

public class EdificioDao extends DatabaseHelper {
    SQLiteDatabase database;
    Cursor cursor;
    ContentValues contentValues;
    public EdificioDao(Context context) {
        super(context);
        database = super.getWritableDatabase();
    }

    public ArrayList<EdificioEntity> getList(){
        ArrayList<EdificioEntity> list = new ArrayList<>();
        EdificioEntity edi;
        cursor = database.rawQuery("SELECT * FROM "+ TABLE_EDIFICIOS, null);
        if (cursor.moveToFirst()){
            do {
                edi = cursorToEntity(cursor);
                list.add(edi);
            }while(cursor.moveToNext());
        }
        return list;
    }

    public void save(EdificioEntity edificio){
        contentValues = new ContentValues();
        contentValues.put("EDI_ID", edificio.getId());
        contentValues.put("EDI_NOMBRE", edificio.getNombre());
        contentValues.put("EDI_PREFIJO", edificio.getPrefijo());
        contentValues.put("EDI_ESTADO", edificio.getEstado());
        database.insert(TABLE_EDIFICIOS, null, contentValues);
    }

    public EdificioEntity cursorToEntity(Cursor cursor){
        EdificioEntity edificio = new EdificioEntity(
                cursor.getInt(cursor.getColumnIndex("EDI_ID")),
                cursor.getString(cursor.getColumnIndex("EDI_NOMBRE")),
                cursor.getString(cursor.getColumnIndex("EDI_PREFIJO")),
                cursor.getString(cursor.getColumnIndex("EDI_ESTADO"))
        );
        return edificio;
    }

}