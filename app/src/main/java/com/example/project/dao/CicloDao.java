package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.bd.DatabaseHelper;
import com.example.project.entidad.CicloEntity;
import java.text.ParseException;
import java.util.ArrayList;

public class CicloDao extends DatabaseHelper {
    SQLiteDatabase database;
    ContentValues contentValues;

    public CicloDao(Context context) {
        super(context);
        database = super.getWritableDatabase();
    }

    public ArrayList<CicloEntity> getList(){
        ArrayList<CicloEntity> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_CICLOS, null);
        if(cursor.moveToFirst()){
            do {
                list.add(cursorToEntity(cursor));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public void save(CicloEntity ciclo){
        contentValues = new ContentValues();
        contentValues.put("CI_CODIGO", ciclo.getCodigo());
        contentValues.put("CI_FECHA_INICIO", ciclo.getFechaInicio());
        contentValues.put("CI_FECHA_FIN", ciclo.getFechaFin());
        contentValues.put("CI_ESTADO", ciclo.getEstado());
        database.insert(TABLE_CICLOS, null, contentValues);
    }

    public void update(CicloEntity ciclo){
        contentValues = new ContentValues();
        contentValues.put("CI_CODIGO", ciclo.getCodigo());
        contentValues.put("CI_FECHA_INICIO", (ciclo.getFechaInicio()));
        contentValues.put("CI_FECHA_FIN", (ciclo.getFechaFin()));
        contentValues.put("CI_ESTADO", ciclo.getEstado());
        database.update(TABLE_CICLOS, contentValues, "CI_ID = ?", new String[]{ String.valueOf(ciclo.getId())});
    }

    public void delete(CicloEntity ciclo){
        database.delete(TABLE_CICLOS, "CI_ID = ?", new String[]{String.valueOf(ciclo.getId())});
    }

    public CicloEntity cursorToEntity(Cursor cursor){
        CicloEntity laboratorio = new CicloEntity(
                cursor.getInt(cursor.getColumnIndex("CI_ID")),
                cursor.getString(cursor.getColumnIndex("CI_CODIGO")),
                cursor.getString(cursor.getColumnIndex("CI_FECHA_INICIO")),
                cursor.getString(cursor.getColumnIndex("CI_FECHA_FIN")),
                cursor.getString(cursor.getColumnIndex("CI_ESTADO"))
        );
        return laboratorio;
    }
}
