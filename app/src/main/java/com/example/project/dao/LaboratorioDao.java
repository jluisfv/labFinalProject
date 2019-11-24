package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.bd.DatabaseHelper;
import com.example.project.entidad.LaboratorioEntity;

import java.util.ArrayList;

public class LaboratorioDao extends DatabaseHelper {
    SQLiteDatabase database;
    Cursor cursor;
   ContentValues contentValues;
    public LaboratorioDao(Context context) {
        super(context);
        database = super.getWritableDatabase();
    }

    public ArrayList<LaboratorioEntity> getList(){
        ArrayList<LaboratorioEntity> list = new ArrayList<>();
        cursor = database.rawQuery("SELECT * FROM "+ TABLE_LABORATORIOS, null);
        if (cursor.moveToFirst()){
            do {
                list.add(cursorToEntity(cursor));
            }while(cursor.moveToNext());
        }
        return list;
    }

    public void save(LaboratorioEntity laboratorio){
            contentValues = new ContentValues();
            contentValues.put("LAB_NOMBRE", laboratorio.getNombre());
            contentValues.put("LAB_ID_EDIFICIO", laboratorio.getIdEdificio());
            contentValues.put("LAB_NIVEL", laboratorio.getNivel());
            database.insert(TABLE_LABORATORIOS, null, contentValues);
    }

    public LaboratorioEntity cursorToEntity(Cursor cursor){
        LaboratorioEntity laboratorio = new LaboratorioEntity(
                cursor.getInt(cursor.getColumnIndex("LAB_ID")),
                cursor.getString(cursor.getColumnIndex("LAB_NOMBRE")),
                cursor.getInt(cursor.getColumnIndex("LAB_ID_EDIFICIO")),
                cursor.getString(cursor.getColumnIndex("LAB_NIVEL"))
        );
        return laboratorio;
    }

    public void update(LaboratorioEntity laboratorio) {
        contentValues = new ContentValues();
        contentValues.put("LAB_NOMBRE", laboratorio.getNombre());
        contentValues.put("LAB_ID_EDIFICIO", laboratorio.getIdEdificio());
        contentValues.put("LAB_NIVEL", laboratorio.getNivel());
        database.update(TABLE_LABORATORIOS, contentValues, "LAB_ID = ?", new String[] {String.valueOf(laboratorio.getId())});
    }
}
