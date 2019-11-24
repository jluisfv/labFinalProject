package com.example.project.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB.db";
    private static final int VERSION = 1;
    public static final String TABLE_USUARIOS = "TBL_USUARIOS";
    public static final String TABLE_CARRERAS = "TBL_CARRERAS";
    public static final String TABLE_EDIFICIOS = "TBL_EDIFICIOS";
    public static final String TABLE_CICLOS = "TBL_CICLOS";
    public static final String TABLE_LABORATORIOS = "TBL_LABORATORIOS";
    public static final String TABLE_ENCARGADO_ASIGNACION = "TBL_EMPLEADO_ASIGNACION";
    public static final String TABLE_DETALLE_ENCARGADO_ASIGNACION = "TBL_DET_ENC_ASIG";
    public static final String TABLE_INSTRUCTOR_ASIGNACION = "TBL_INSTRUCTOR_ASIGNACION";
    public static final String TABLE_DETALLE_INSTRUCTOR_ASIGNACION = "TBL_DET_INS_ASIG";
    public static final String TABLE_ACTIVIDAD_TURNO_INSTRUCTOR = "TBL_ACT_TURN_INS";
    public static final String TABLE_ACTIVIDADES = "TBL_ACTIVIDADES";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ACESSOS_TBL = "CREATE TABLE IF NOT EXISTS " + TABLE_USUARIOS + " ("+
                "ACC_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "ACC_NOMBRE TEXT, "+
                "ACC_APELLIDOS TEXT, "+
                "ACC_EMAIL TEXT, "+
                "ACC_CLAVE TEXT, "+
                "ACC_TIPO TEXT, "+
                "ACC_ESTADO TEXT );";
        sqLiteDatabase.execSQL(CREATE_ACESSOS_TBL);

        String CREATE_CARRERAS_TBL = "CREATE TABLE IF NOT EXISTS " + TABLE_CARRERAS + " ("+
                "CA_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "CA_NOMBRE TEXT );";
        sqLiteDatabase.execSQL(CREATE_CARRERAS_TBL);

        String CREATE_EDIFICIOS_TBL = "CREATE TABLE IF NOT EXISTS " + TABLE_EDIFICIOS + " ("+
                "EDI_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "EDI_NOMBRE TEXT, " +
                "EDI_PREFIJO TEXT, " +
                "EDI_ESTADO TEXT );";
        sqLiteDatabase.execSQL(CREATE_EDIFICIOS_TBL);

        String CREATE_LABORATORIOS_TBL = "CREATE TABLE IF NOT EXISTS " + TABLE_LABORATORIOS + " ("+
                "LAB_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "LAB_NOMBRE TEXT, "+
                "LAB_ID_EDIFICIO INTEGER, "+
                "LAB_NIVEL TEXT );";
        sqLiteDatabase.execSQL(CREATE_LABORATORIOS_TBL);

        String CREATE_CICLOS_TBL = "CREATE TABLE IF NOT EXISTS "+ TABLE_CICLOS + " ("+
                "CI_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "CI_CODIGO TEXT, "+
                "CI_FECHA_INICIO DATE, "+
                "CI_FECHA_FIN DATE, "+
                "CI_ESTADO TEXT );";
        sqLiteDatabase.execSQL(CREATE_CICLOS_TBL);

        String CREATE_EMPLEADO_ASIG_TBL = "CREATE TABLE IF NOT EXISTS "+ TABLE_ENCARGADO_ASIGNACION + " ("+
                "ENASIG_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "ENASIG_USUARIO_ID INTEGER, "+
                "ENASIG_LAB_ID INTEGER, "+
                "ENASIG_CI_ID INTEGER );";
        sqLiteDatabase.execSQL(CREATE_EMPLEADO_ASIG_TBL);

        String CREATE_INSTRUCTOR_ASIG_TBL = "CREATE TABLE IF NOT EXISTS "+ TABLE_INSTRUCTOR_ASIGNACION + " ("+
                "INSASIG_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "INSASIG_USUARIO_ID INTEGER, "+
                "INSASIG_CI_ID INTEGER, "+
                "INSASIG_FECHA_INICIO DATETIME, "+
                "INSASIG_FECHA_FIN DATETIME);";
        sqLiteDatabase.execSQL(CREATE_INSTRUCTOR_ASIG_TBL);

        String CREATE_DET_ENC_ASIG = "CREATE TABLE IF NOT EXISTS "+ TABLE_DETALLE_ENCARGADO_ASIGNACION + " ("+
                "ENCDET_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "ENCDET_DIA TEXT, "+
                "ENCDET_HORA_INICIO DATETIME, "+
                "ENCDET_HORA_FIN DATETIME, "+
                "ENCDET_ASIG_ID INTEGER);";
        sqLiteDatabase.execSQL(CREATE_DET_ENC_ASIG);

        String CREATE_DET_INS_ASIG = "CREATE TABLE IF NOT EXISTS "+ TABLE_DETALLE_INSTRUCTOR_ASIGNACION + " ("+
                "INSDET_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "INSDET_DIA TEXT, "+
                "INSDET_HORA_INICIO DATETIME, "+
                "INSDET_HORA_FIN DATETIME, "+
                "ENCDET_INSASIG_ID INTEGER,"+
                "INSDET_APROVADO TEXT, "+
                "INSDET_REVISADO TEXT);";
        sqLiteDatabase.execSQL(CREATE_DET_INS_ASIG);

        String CREATE_ACTIVIDADES = "CREATE TABLE IF NOT EXISTS "+ TABLE_ACTIVIDADES + " ("+
                "ACT_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "ACT_NOMBRE TEXT," +
                "ACT_DESCRIPCION TEXT, " +
                "ACT_ESTADO TEXT);";
        sqLiteDatabase.execSQL(CREATE_ACTIVIDADES);

        String CREATE_ACT_TURNO_ACT = "CREATE TABLE IF NOT EXISTS "+ TABLE_ACTIVIDAD_TURNO_INSTRUCTOR + " ("+
                "TURNO_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "TURNO_HORA_INICIO DATETIME," +
                "TURNO_HORA_FIN DATETIME, " +
                "TURNO_INSDET_ID INTEGER, "+
                "TURNO_ACT_ID INTEGER, "+
                "TURNO_REVISADO TEXT, "+
                "TURNO_APROVADO TEXT);";
        sqLiteDatabase.execSQL(CREATE_ACT_TURNO_ACT);

        populateDatabase(sqLiteDatabase);
        Log.println(Log.INFO , "DATABASE",  "SUCCESS");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    void populateDatabase(SQLiteDatabase sqLiteDatabase){
        ContentValues values = new ContentValues();
        values.put("ACC_NOMBRE", "jose");
        values.put("ACC_APELLIDOS", "flores");
        values.put("ACC_EMAIL", "jose@gmail.com");
        values.put("ACC_CLAVE", "12345");
        values.put("ACC_TIPO", "ADMINISTRADOR");
        values.put("ACC_ESTADO", "ACTIVO");
        sqLiteDatabase.insert(TABLE_USUARIOS, null, values);

        values.put("ACC_NOMBRE", "norma");
        values.put("ACC_APELLIDOS", "lizbet");
        values.put("ACC_EMAIL", "norma@gmail.com");
        values.put("ACC_CLAVE", "12345");
        values.put("ACC_TIPO", "ENCARGADO");
        values.put("ACC_ESTADO", "ACTIVO");
        sqLiteDatabase.insert(TABLE_USUARIOS, null, values);

        values.put("ACC_NOMBRE", "garcia");
        values.put("ACC_APELLIDOS", "garcia");
        values.put("ACC_EMAIL", "garcia@gmail.com");
        values.put("ACC_CLAVE", "12345");
        values.put("ACC_TIPO", "INSTRUCTOR");
        values.put("ACC_ESTADO", "ACTIVO");
        sqLiteDatabase.insert(TABLE_USUARIOS, null, values);

        values = new ContentValues();
        values.put("EDI_NOMBRE", "Francisco Morazan");
        values.put("EDI_PREFIJO", "FM");
        values.put("EDI_ESTADO", "ACTIVO");
        sqLiteDatabase.insert(TABLE_EDIFICIOS, null, values);
        values = new ContentValues();
        values.put("EDI_NOMBRE", "Benito Juarez");
        values.put("EDI_PREFIJO", "BJ");
        values.put("EDI_ESTADO", "ACTIVO");
        sqLiteDatabase.insert(TABLE_EDIFICIOS, null, values);
        values = new ContentValues();
        values.put("EDI_NOMBRE", "Giusepe Garibaldi");
        values.put("EDI_PREFIJO", "GG");
        values.put("EDI_ESTADO", "ACTIVO");
        sqLiteDatabase.insert(TABLE_EDIFICIOS, null, values);
        values = new ContentValues();
        values.put("EDI_NOMBRE", "Simon Bolivar");
        values.put("EDI_PREFIJO", "SB");
        values.put("EDI_ESTADO", "ACTIVO");
        sqLiteDatabase.insert(TABLE_EDIFICIOS, null, values);
        values = new ContentValues();
        values.put("EDI_NOMBRE", "Gabriela Mistral");
        values.put("EDI_PREFIJO", "GM");
        values.put("EDI_ESTADO", "ACTIVO");
        sqLiteDatabase.insert(TABLE_EDIFICIOS, null, values);

        values = new ContentValues();
        values.put("LAB_NOMBRE", "Lab-2");
        values.put("LAB_ID_EDIFICIO", "2");
        values.put("LAB_NIVEL", "Sotano");
        sqLiteDatabase.insert(TABLE_LABORATORIOS, null, values);
    }
}
