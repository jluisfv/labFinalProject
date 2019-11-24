package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import com.example.project.R;

public class EncargadoHistorialDetalleInstructor extends AppCompatActivity {

    Button btnRegresar, btnGenerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encargado_activity_historial_instructor_detalle);

        btnGenerar = findViewById( R.id.btnGenerar );
        btnRegresar = findViewById( R.id.btnRegresar );

        Spinner spinner = (Spinner) findViewById(R.id.spinner9);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meses_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner10);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.ciclo_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        btnRegresar.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent( getApplicationContext(), EncargadoHistorialInstructor.class );
                startActivity( regresar );

            }
        } );

        btnGenerar.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {

            }
        } );

    }
}
