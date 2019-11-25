package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.bd.DatabaseHelper;
import com.example.project.entidad.DetAsigEncargadoEntity;

import java.util.ArrayList;

public class DetAsigEncargadoDao extends DatabaseHelper {

    SQLiteDatabase database;
    ContentValues contentValues;
    public DetAsigEncargadoDao(Context context) {
        super(context);
        database = super.getWritableDatabase();
    }

    public ArrayList<DetAsigEncargadoEntity> findByIdAsig(int id){
        Cursor cursor = database.rawQuery("SELECT * FROM "+ TABLE_DETALLE_ENCARGADO_ASIGNACION +" WHERE ENCDET_ASIG_ID = ?", new String[]{String.valueOf(id)});
        ArrayList<DetAsigEncargadoEntity> list = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                list.add(cursorToEntity(cursor));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public void save(DetAsigEncargadoEntity detAsigEmpleado){
        contentValues = new ContentValues();
        contentValues.put("ENCDET_DIA", detAsigEmpleado.getDia());
        contentValues.put("ENCDET_HORA_INICIO", detAsigEmpleado.getHoraInicio());
        contentValues.put("ENCDET_HORA_FIN", detAsigEmpleado.getHoraFin());
        contentValues.put("ENCDET_ASIG_ID", detAsigEmpleado.getIdAsignacion());
        database.insert(TABLE_DETALLE_ENCARGADO_ASIGNACION,null, contentValues);
    }

    public void update(DetAsigEncargadoEntity detAsigEmpleado){
        contentValues = new ContentValues();
        contentValues.put("ENCDET_DIA", detAsigEmpleado.getDia());
        contentValues.put("ENCDET_HORA_INICIO", detAsigEmpleado.getHoraInicio());
        contentValues.put("ENCDET_HORA_FIN", detAsigEmpleado.getHoraFin());
        contentValues.put("ENCDET_ASIG_ID", detAsigEmpleado.getIdAsignacion());
        database.update(TABLE_DETALLE_ENCARGADO_ASIGNACION, contentValues, "ENCDET_ID = ?", new String[]{String.valueOf(detAsigEmpleado.getId())});
    }

    public void delete(DetAsigEncargadoEntity detAsigEmpleado){
        database.delete(TABLE_DETALLE_ENCARGADO_ASIGNACION, "ENCDET_ID = ?", new String[]{String.valueOf(detAsigEmpleado.getId())});
    }

    public DetAsigEncargadoEntity cursorToEntity(Cursor cursor){
        DetAsigEncargadoEntity asigDet = new DetAsigEncargadoEntity(
                cursor.getInt(cursor.getColumnIndex("ENCDET_ID")),
                cursor.getInt(cursor.getColumnIndex("ENCDET_DIA")),
                cursor.getString(cursor.getColumnIndex("ENCDET_HORA_INICIO")),
                cursor.getString(cursor.getColumnIndex("ENCDET_HORA_FIN")),
                cursor.getInt(cursor.getColumnIndex("ENCDET_ASIG_ID"))
        );
        return asigDet;
    }

    public void deleteByIdAsig(int idAsig) {
        database.delete(TABLE_DETALLE_ENCARGADO_ASIGNACION, "ENCDET_ASIG_ID = ?", new String[]{String.valueOf(idAsig)});
    }
}
