package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.project.dao.EdificioDao;
import com.example.project.entidad.EdificioEntity;

public class AdmiCrearEdificio extends AppCompatActivity {
    Button guard,btnCancelar;
    EditText ededificio, edprefijo;
    RadioButton r1,r2;
    EdificioDao edificioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.admi_activity_crear_edificio);
        setTitle("Crear Edificio");
        edificioDao = new EdificioDao(getApplicationContext());

        ededificio = findViewById(R.id.editText15);
        edprefijo = findViewById(R.id.editText16);
        r1 = findViewById(R.id.radioButton7);
        r1.setChecked(true);
        r2 = findViewById(R.id.radioButton8);
        btnCancelar = findViewById(R.id.btnCancelar);
        guard = findViewById(R.id.btnGuardar);

        guard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validarEdt(ededificio)){
                    if(validarEdt(edprefijo)){
                        edificioDao.save(new EdificioEntity(
                                0,
                                ededificio.getText().toString(),
                                edprefijo.getText().toString(),
                                (r1.isChecked())?"ACTIVO": "INACTIVO"
                                )
                        );
                        Toast.makeText(getApplicationContext(), "EDIFICIO GUARDADO CON EXITO", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AdmiListadoEdificio.class);
                        startActivity(intent);
                    }
                }

            }
        } );

        btnCancelar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent( getApplicationContext(),AdmiMainActivity.class );
                startActivity( regresar );
            }
        } );
    }

    public boolean validarEdt(EditText edt){
        if (edt.getText().toString().isEmpty()){
            edt.setError("Campo Requerido");
            edt.requestFocus();
            return false;
        }
        return true;
    }
}
