package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.bd.DatabaseHelper;
import com.example.project.entidad.DetHorasEntity;

import java.util.ArrayList;

public class DetHorasDao extends DatabaseHelper {

    SQLiteDatabase database;
    ContentValues contentValues;
    public DetHorasDao(Context context) {
        super(context);
        database = super.getWritableDatabase();
    }

    public ArrayList<DetHorasEntity> findByAsing(int id){
        Cursor cursor = database.rawQuery("SELECT * FROM "+ TABLE_DETALLE_INSTRUCTOR_ASIGNACION +" WHERE INSDET_ASIG_ID = ?", new String[]{String.valueOf(id)});
        ArrayList<DetHorasEntity> list = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                list.add(cursorToEntity(cursor));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public void save(DetHorasEntity Horas){
        contentValues = new ContentValues();
        contentValues.put("INSDET_DIA", Horas.getDia());
        contentValues.put("INSDET_HORA_INICIO", Horas.getHoraInicio());
        contentValues.put("INSDET_HORA_FIN",Horas.getHoraFin());
        contentValues.put("INSDET_ASIG_ID", Horas.getIdInstructor());
        database.insert(TABLE_DETALLE_INSTRUCTOR_ASIGNACION,null, contentValues);
    }

    public void update(DetHorasEntity Horas){
        contentValues = new ContentValues();
        contentValues.put("INSDET_DIA", Horas.getDia());
        contentValues.put("INSDET_HORA_INICIO", Horas.getHoraInicio());
        contentValues.put("INSDET_HORA_FIN", Horas.getHoraFin());
        contentValues.put("INSDET_ASIG_ID", Horas.getIdInstructor());
        database.update(TABLE_DETALLE_INSTRUCTOR_ASIGNACION, contentValues, "INSDET_ID = ?", new String[]{String.valueOf(Horas.getId())});
    }

    public void delete(DetHorasEntity Horas){
        database.delete(TABLE_DETALLE_INSTRUCTOR_ASIGNACION, "INSDET_ID = ?", new String[]{String.valueOf(Horas.getId())});
    }

    public DetHorasEntity cursorToEntity(Cursor cursor){
        DetHorasEntity asigDet = new DetHorasEntity(
                cursor.getInt(cursor.getColumnIndex("INSDET_ID")),
                cursor.getInt(cursor.getColumnIndex("INSDET_DIA")),
                cursor.getString(cursor.getColumnIndex("INSDET_HORA_INICIO")),
                cursor.getString(cursor.getColumnIndex("INSDET_HORA_FIN")),
                cursor.getInt(cursor.getColumnIndex("INSDET_ASIG_ID"))
        );
        return asigDet;
    }

    public void deleteByIdAsig(int asigDet) {
        database.delete(TABLE_DETALLE_INSTRUCTOR_ASIGNACION, "INSDET_ASIG_ID = ?", new String[]{String.valueOf(asigDet )});
    }
}
