package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AdmiModificarEdificio extends AppCompatActivity {
    Button btnGuardar,btnCancelar;
    EditText edpref,edplanta;
    RadioButton ra,ri;
    TextView tved;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admi_modificar_edificio );
        edpref = findViewById(R.id.editText16);
        edplanta = findViewById(R.id.editText21);
        ra = findViewById(R.id.radioButton7);
        ri = findViewById(R.id.radioButton8);
        tved = findViewById(R.id.textView31);
        btnCancelar = findViewById( R.id.btnCancelar );
        btnGuardar = findViewById( R.id.btnGuardar );

        //BD objbase = new BD(getApplicationContext(),"DB",null,1);
        //bd = objbase.getWritableDatabase();

        Bundle bb = getIntent().getExtras();
        final String nam = bb.getString("nomedif");
        tved.setText("Edificio:"+" "+nam);

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prefijo = edpref.getText().toString();
                String estado = (ra.isChecked())?"activo": "inactivo";
                bd.execSQL("UPDATE edificios SET correlativoedif='"+prefijo+"', estado='"+estado+"' where nombreedif='"+nam+"'");
                Toast.makeText( getApplicationContext(),"Datos Almacenados",Toast.LENGTH_LONG ).show();

            }
        } );

        btnCancelar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent( getApplicationContext(),AdmiListadoEdificio.class );
                startActivity( regresar );
            }
        } );
    }
}
