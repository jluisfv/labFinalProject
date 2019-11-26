package com.example.project.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.bd.DatabaseHelper;
import com.example.project.entidad.UsuarioEntity;

import java.util.ArrayList;

public class UsuarioDao extends DatabaseHelper{

    private Cursor cursor;
    private ContentValues contentValues;
    SQLiteDatabase database;


    public UsuarioDao(Context context) {
        super(context);
        database = super.getWritableDatabase();
    }

    public UsuarioEntity findById(int id){
        cursor = database.rawQuery("SELECT * FROM "+ TABLE_USUARIOS+" WHERE ACC_ID = ?",
                new String[]{String.valueOf(id)});
        if (cursor.moveToFirst())
        {
            return cursorToEntity(cursor);//Devuelve el objeto
        }
        return null;//sino null
    }

    public UsuarioEntity findAcceso(String usuario, String clave){
        cursor = database.rawQuery("SELECT * FROM "+ TABLE_USUARIOS+" WHERE ACC_EMAIL = lower(?) AND ACC_CLAVE = lower(?)",
                new String[]{ usuario, clave});
        if (cursor.moveToFirst())
        {
            return cursorToEntity(cursor);//Devuelve el objeto
        }
        return null;//sino null
    }

    public ArrayList<UsuarioEntity> list(){
        ArrayList<UsuarioEntity> lista = new ArrayList<>();
        UsuarioEntity usuario;
        cursor = database.rawQuery("SELECT * FROM "+ TABLE_USUARIOS, null);
        if (cursor.moveToFirst()){
            do{
                usuario = cursorToEntity(cursor);
                lista.add(usuario);
            }while (cursor.moveToNext());
        }
        return lista;
    }

    public ArrayList<UsuarioEntity> listPorLaboratorio(String idLab){
        ArrayList<UsuarioEntity> lista = new ArrayList<>();
        UsuarioEntity usuario;
        cursor = database.rawQuery("SELECT * FROM "+ TABLE_INSTRUCTOR_ASIGNACION + " a INNER join "+TABLE_USUARIOS+" u" +
                                        " on a.INSASIG_USUARIO_ID = u.ACC_ID where a.INSASIG_LAB_ID = ? ", new String[]{ idLab });
        if (cursor.moveToFirst()){
            do{
                usuario = cursorToEntity(cursor);
                lista.add(usuario);
            }while (cursor.moveToNext());
        }
        return lista;
    }


    public ArrayList<UsuarioEntity> list(String tipo){
        ArrayList<UsuarioEntity> lista = new ArrayList<>();
        UsuarioEntity usuario;
        cursor = database.rawQuery("SELECT * FROM "+ TABLE_USUARIOS, null);
        if (cursor.moveToFirst()){
            do{
                usuario = cursorToEntity(cursor);
                if(usuario.getTipo().equals(tipo)){
                    lista.add(usuario);
                }
            }while (cursor.moveToNext());
        }
        return lista;
    }

    public void save(UsuarioEntity usuario){
        contentValues = new ContentValues();
        contentValues.put("ACC_NOMBRE", usuario.getNombre());
        contentValues.put("ACC_APELLIDOS", usuario.getApellido());
        contentValues.put("ACC_EMAIL", usuario.getEmail());
        contentValues.put("ACC_CLAVE", usuario.getClave());
        contentValues.put("ACC_TIPO", usuario.getTipo());
        contentValues.put("ACC_ESTADO", usuario.getEstado());

        database.insert(TABLE_USUARIOS, null, contentValues);
    }

    public void update(UsuarioEntity usuario){
        contentValues = new ContentValues();
        contentValues.put("ACC_NOMBRE", usuario.getNombre());
        contentValues.put("ACC_APELLIDOS", usuario.getApellido());
        contentValues.put("ACC_EMAIL", usuario.getEmail());
        contentValues.put("ACC_CLAVE", usuario.getClave());
        contentValues.put("ACC_ESTADO", usuario.getTipo());

        database.update(TABLE_USUARIOS, contentValues, "ACC_ID = ?", new String[]{String.valueOf(usuario.getId())});
    }

    public void delete(UsuarioEntity usuario){
        database.delete(TABLE_USUARIOS, "ACC_ID = ?", new String[]{String.valueOf(usuario.getId())});
    }

    public UsuarioEntity cursorToEntity(Cursor cursor){
        UsuarioEntity usuarioEntity;

            usuarioEntity = new UsuarioEntity(
                    cursor.getInt(cursor.getColumnIndex("ACC_ID")),
                    cursor.getString(cursor.getColumnIndex("ACC_NOMBRE")),
                    cursor.getString(cursor.getColumnIndex("ACC_APELLIDOS")),
                    cursor.getString(cursor.getColumnIndex("ACC_EMAIL")),
                    cursor.getString(cursor.getColumnIndex("ACC_CLAVE")),
                    cursor.getString(cursor.getColumnIndex("ACC_TIPO")),
                    cursor.getString(cursor.getColumnIndex("ACC_ESTADO"))
            );

        return usuarioEntity;
    }
}
