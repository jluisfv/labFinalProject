package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.dao.ActividadDao;
import com.example.project.entidad.ActividadEntity;

public class EncargadoCrearActividades extends AppCompatActivity {
    RadioButton r1,r2;
    EditText edtActividad,edtDetalle;
    Button btnGuardar,btnCancelar;
    ActividadDao actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.encargado_crear_actividades );

        actividad = new ActividadDao( getApplicationContext() );

        r1 = findViewById( R.id.rdbActivo );
        r2 = findViewById( R.id.rdbInactivo );

        edtActividad = findViewById( R.id.edtActividad );
        edtDetalle = findViewById( R.id.edtDecripcion );
        btnCancelar = findViewById( R.id.btnCancelar );
        btnGuardar = findViewById( R.id.btnGuardar );

        setTitle( "Crear Actividades" );


        btnGuardar.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                if(validar(edtActividad)){
                    if(validar(edtDetalle)){
                        actividad.save(new ActividadEntity(
                                        0,
                                        edtActividad.getText().toString(),
                                        edtDetalle.getText().toString(),
                                        (r1.isChecked())?"ACTIVO": "INACTIVO"
                                )
                        );
                        Toast.makeText(getApplicationContext(), "ACTIVIDAD GUARDADA CON EXITO", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), EncargadoListadoActividades.class);
                        startActivity(intent);
                    }
                }
            }
        } );

        btnCancelar.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent cancelar = new Intent( getApplicationContext(),EncargadoMainActivity.class );
                startActivity( cancelar );

            }
        } );
    }

    public boolean validar(EditText edt){
        if (edt.getText().toString().isEmpty()){
            edt.setError("Campo Requerido");
            edt.requestFocus();
            return false;
        }
        return true;
    }
}
