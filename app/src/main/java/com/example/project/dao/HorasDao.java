package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.bd.DatabaseHelper;
import com.example.project.entidad.InstructorEntity;

import java.util.ArrayList;

public class HorasDao extends DatabaseHelper {
    SQLiteDatabase database;
    ContentValues contentValues;

    public HorasDao(Context context) {
        super( context );
        database = super.getWritableDatabase();
    }

    public InstructorEntity findById(int id){
        Cursor cursor = database.rawQuery("SELECT * FROM "+ TABLE_INSTRUCTOR_ASIGNACION+" WHERE INSASIG_ID = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()){
            return cursorToEntity(cursor);
        }
        return null;
    }


    public ArrayList<InstructorEntity > getList(){
        ArrayList<InstructorEntity > list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ TABLE_INSTRUCTOR_ASIGNACION, null);
        if(cursor.moveToFirst()){
            do {
                list.add(cursorToEntity(cursor));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public long save(InstructorEntity Horas){
        contentValues = new ContentValues();
        contentValues.put("INSASIG_USUARIO_ID", Horas.getIdInstructor());
        contentValues.put("INSASIG_LAB_ID", Horas.getIdLab());
        contentValues.put("INSASIG_CI_ID", Horas.getIdCiclo());
        return database.insert(TABLE_INSTRUCTOR_ASIGNACION, null, contentValues);
    }

    public void update(InstructorEntity Horas){
        contentValues = new ContentValues();
        contentValues.put("INSASIG_USUARIO_ID",Horas.getIdInstructor());
        contentValues.put("INSASIG_LAB_ID", Horas.getIdLab());
        contentValues.put("INSASIG_CI_ID", Horas.getIdCiclo());
        database.update(TABLE_INSTRUCTOR_ASIGNACION, contentValues, "INSASIG_ID = ?", new String[]{String.valueOf(Horas.getId())});
    }

    public void delete(InstructorEntity Horas){
        database.delete(TABLE_INSTRUCTOR_ASIGNACION, "INSASIG_ID = ?", new String[]{String.valueOf(Horas.getId())});

    }

    public InstructorEntity cursorToEntity(Cursor cursor){
        InstructorEntity  inst = new InstructorEntity (
                cursor.getInt(cursor.getColumnIndex("INSASIG_ID_ID")),
                cursor.getInt(cursor.getColumnIndex("INSASIG_ID_USUARIO_ID")),
                cursor.getInt(cursor.getColumnIndex("INSASIG_ID_LAB_ID")),
                cursor.getInt(cursor.getColumnIndex("INSASIG_ID_CI_ID"))
        );
        return inst;
    }
}
