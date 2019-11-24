package com.example.project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.CoreComponentFactory;

public class AdmiModificarLaboratorio extends AppCompatActivity {
    Button btnGuardar, btnCancelar;
    TextView tv;
    EditText edtplanta;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_modificar_laboratorio);

        btnGuardar = findViewById( R.id.btnGuardar );
        btnCancelar = findViewById( R.id.btnCancelar );
        tv = findViewById(R.id.textView32);
        edtplanta = findViewById(R.id.editText27);

        //BD objbase = new BD(getApplicationContext(),"DB",null,1);
       // bd = objbase.getWritableDatabase();

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.edificios_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Bundle b = getIntent().getExtras();
        final String nn = b.getString("nlab");
        tv.setText("Laboratorio:"+" "+nn);

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String planta = edtplanta.getText().toString();
                String corre = spinner.getSelectedItem().toString();
                String Correl = (corre.equals("Francisco Morazan"))?"fm":(corre.equals("Benito Juarez"))?"bj":(corre.equals("Gusepe Garibaldi"))?"gg":(corre.equals("Simon Bolivar"))?"sb":"gm";
                if(!planta.equals("")){
                    String consu = "update laboratorios set correlativoedif='"+ Correl +"', planta='"+planta+"' where nombrelab='"+nn+"'";
                    bd.execSQL(consu,null);
                    Toast.makeText(getApplicationContext(), "Laboratorio modificado", Toast.LENGTH_SHORT).show();
                }else{
                    edtplanta.setError("Digite nueva planta de lab.");
                    edtplanta.requestFocus();
                }
            }
        } );

        btnCancelar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj3 = new Intent( getApplicationContext(), AdmiListadoLaboratorio.class );
                startActivity( obj3 );
            }
        } );
    }
}
