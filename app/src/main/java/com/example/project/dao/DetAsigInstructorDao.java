package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project.bd.DatabaseHelper;
import com.example.project.entidad.DetAsigEncargadoEntity;
import com.example.project.entidad.DetAsigInstructorEntity;

public class DetAsigInstructorDao extends DatabaseHelper {
    SQLiteDatabase database;
    ContentValues contentValues;

    public DetAsigInstructorDao(Context context) {
        super(context);
        database = super.getWritableDatabase();

    }

    public void save(DetAsigEncargadoEntity detAsigEncargadoEntity)
    {
        contentValues = new ContentValues();
        contentValues.put("INSDET_DIA",detAsigEncargadoEntity.getDia());
        contentValues.put("INSDET_HORA_INICIO",detAsigEncargadoEntity.getHoraInicio());
        contentValues.put("INSDET_HORA_FIN",detAsigEncargadoEntity.getHoraFin());
        contentValues.put("ENCDET_INSASIG_ID",detAsigEncargadoEntity.getIdAsignacion());

        database.insert(TABLE_DETALLE_INSTRUCTOR_ASIGNACION,null,contentValues);
    }

    public DetAsigInstructorEntity cursorToEntity(Cursor cursor){
        DetAsigInstructorEntity asigDet = new DetAsigInstructorEntity(
                cursor.getInt(cursor.getColumnIndex("INSDET_ID")),
                cursor.getString(cursor.getColumnIndex("INSDET_DIA")),
                cursor.getString(cursor.getColumnIndex("INSDET_HORA_INICIO")),
                cursor.getString(cursor.getColumnIndex("INSDET_HORA_FIN")),
                cursor.getInt(cursor.getColumnIndex("ENCDET_INSASIG_ID"))
        );
        return asigDet;
    }
}
