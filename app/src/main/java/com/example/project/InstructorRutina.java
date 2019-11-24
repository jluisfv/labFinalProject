package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.TimeZone;

public class InstructorRutina extends AppCompatActivity {
    Button b1,b2,b3,b4;
    Calendar calendario = Calendar.getInstance();
    int hora, minutos;
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_activity_rutina);

        calendario.setTimeZone( TimeZone.getTimeZone( "America/El_Salvador" ));
        hora =calendario.get(Calendar.HOUR);
        minutos = calendario.get(Calendar.MINUTE);

        e1 = findViewById( R.id.editText2 );
        e2 = findViewById( R.id.editText3 );

        b1 = findViewById( R.id.button );
        b2 = findViewById( R.id.btnCancelar );
        b3 = findViewById( R.id.button3 );
        b4 = findViewById( R.id.button4 );

        Spinner spinner = (Spinner) findViewById(R.id.spinner8);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.actividad_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText( hora + ":" + minutos );
            }
        } );

        b2.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                e2.setText( hora + ":" + minutos );
            }
        } );

        b3.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(),"Solicitud Enviada",Toast.LENGTH_LONG ).show();
            }
        } );

        b4.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent( getApplicationContext(),InstructorMainActivity.class );
                startActivity( regresar );
            }
        } );
    }
}
