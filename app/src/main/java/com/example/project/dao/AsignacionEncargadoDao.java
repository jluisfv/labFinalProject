package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.bd.DatabaseHelper;
import com.example.project.entidad.AsignacionEncargadoEntity;

import java.util.ArrayList;

public class AsignacionEncargadoDao extends DatabaseHelper {

    SQLiteDatabase database;
    ContentValues contentValues;
    public AsignacionEncargadoDao(Context context) {
        super(context);
        database = super.getWritableDatabase();
    }

    public AsignacionEncargadoEntity findById(int id){
        Cursor cursor = database.rawQuery("SELECT * FROM "+ TABLE_ENCARGADO_ASIGNACION +" WHERE ENASIG_ID = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()){
            return cursorToEntity(cursor);
        }
        return null;
    }

    public ArrayList<AsignacionEncargadoEntity> getList(){
        ArrayList<AsignacionEncargadoEntity> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ TABLE_ENCARGADO_ASIGNACION, null);
        if(cursor.moveToFirst()){
            do {
                list.add(cursorToEntity(cursor));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public long save(AsignacionEncargadoEntity asignacionEmpleado){
        contentValues = new ContentValues();
        contentValues.put("ENASIG_USUARIO_ID", asignacionEmpleado.getIdEmpleado());
        contentValues.put("ENASIG_LAB_ID", asignacionEmpleado.getIdLab());
        contentValues.put("ENASIG_CI_ID", asignacionEmpleado.getIdCiclo());
        return database.insert(TABLE_ENCARGADO_ASIGNACION, null, contentValues);
    }

    public void update(AsignacionEncargadoEntity asignacionEmpleado){
        contentValues = new ContentValues();
        contentValues.put("ENASIG_USUARIO_ID", asignacionEmpleado.getIdEmpleado());
        contentValues.put("ENASIG_LAB_ID", asignacionEmpleado.getIdLab());
        contentValues.put("ENASIG_CI_ID", asignacionEmpleado.getIdCiclo());
        database.update(TABLE_ENCARGADO_ASIGNACION, contentValues, "ENASIG_ID = ?", new String[]{String.valueOf(asignacionEmpleado.getId())});
    }

    public void delete(AsignacionEncargadoEntity asignacionEmpleado){
        database.delete(TABLE_ENCARGADO_ASIGNACION, "ENASIG_ID = ?", new String[]{String.valueOf(asignacionEmpleado.getId())});

    }

    public AsignacionEncargadoEntity cursorToEntity(Cursor cursor){
        AsignacionEncargadoEntity asig = new AsignacionEncargadoEntity(
                cursor.getInt(cursor.getColumnIndex("ENASIG_ID")),
                cursor.getInt(cursor.getColumnIndex("ENASIG_USUARIO_ID")),
                cursor.getInt(cursor.getColumnIndex("ENASIG_LAB_ID")),
                cursor.getInt(cursor.getColumnIndex("ENASIG_CI_ID"))
        );
        return asig;
    }
}
