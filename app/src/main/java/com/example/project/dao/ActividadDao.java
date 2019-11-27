package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.bd.DatabaseHelper;
import com.example.project.entidad.ActividadEntity;

import java.util.ArrayList;

public class ActividadDao extends DatabaseHelper {
    SQLiteDatabase database;
    Cursor cursor;
    ContentValues contentValues;

    public ActividadDao(Context context) {
        super(context);
        database = super.getWritableDatabase();
    }
    public ArrayList< ActividadEntity> getList(){
        ArrayList< ActividadEntity> list = new ArrayList<>();
        ActividadEntity act;
        cursor = database.rawQuery("SELECT * FROM "+ TABLE_ACTIVIDADES, null);
        while (cursor.moveToNext()){
                act = cursorToEntity(cursor);
                list.add(act);
        }
        return list;
    }

    public ActividadEntity getActivity(String id)
    {
        ActividadEntity act = null;
        cursor = database.rawQuery("SELECT * FROM "+ TABLE_ACTIVIDADES + " where ACT_ID = ?", new String[]{id});
        while (cursor.moveToNext()){
            act = cursorToEntity(cursor);
        }
        return act;
    }

    public void save( ActividadEntity actividad){
        contentValues = new ContentValues();
        contentValues.put("ACT_NOMBRE", actividad.getActividad());
        contentValues.put("ACT_DESCRIPCION", actividad.getDescripcion());
        contentValues.put("ACT_ESTADO", actividad.getEstado());
        database.insert(TABLE_ACTIVIDADES, null, contentValues);
    }
    public void update(ActividadEntity actividad) {
        contentValues = new ContentValues();
        contentValues.put("ACT_NOMBRE", actividad.getActividad());
        contentValues.put("ACT_DESCRIPCION", actividad.getDescripcion());
        contentValues.put("ACT_ESTADO", actividad.getEstado());
        database.update(TABLE_ACTIVIDADES, contentValues, "ACT_ID = ?", new String[]{String.valueOf(actividad.getId())});
    }

    public ActividadEntity cursorToEntity(Cursor cursor){
        ActividadEntity actividad = new ActividadEntity(
                cursor.getInt(cursor.getColumnIndex("ACT_ID")),
                cursor.getString(cursor.getColumnIndex("ACT_NOMBRE")),
                cursor.getString(cursor.getColumnIndex("ACT_DESCRIPCION")),
                cursor.getString(cursor.getColumnIndex("ACT_ESTADO"))
        );
        return actividad;
    }
}
