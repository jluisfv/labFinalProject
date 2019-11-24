package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.dao.EdificioDao;
import com.example.project.dao.LaboratorioDao;
import com.example.project.entidad.EdificioEntity;
import com.example.project.entidad.LaboratorioEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class AdmiCrearLaboratorio extends AppCompatActivity {
    EditText edtLab, edtPlanta;
    Button btnGuardar, btnCancelar;
    LaboratorioDao laboratorioDao;
    EdificioDao edificioDao;
    Spinner spinner;

    LaboratorioEntity laboratorio;
    ArrayList<EdificioEntity> listEdificios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_crear_laboratorio);
        setTitle("Crear Laboratorio");
        laboratorioDao = new LaboratorioDao(getApplicationContext());
        edificioDao = new EdificioDao(getApplicationContext());

        listEdificios = edificioDao.getList();
        ArrayList<String> encabezadoLista = new ArrayList<>();

        for (EdificioEntity edificio: listEdificios){
            encabezadoLista.add(edificio.getNombre());
        }

        spinner = findViewById(R.id.spinnerEdificios);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, encabezadoLista);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        edtLab = findViewById(R.id.edtLab);
        edtPlanta = findViewById(R.id.edtPlanta);
        btnGuardar = findViewById( R.id.btnGuardar );
        btnCancelar = findViewById( R.id.btnCancelar );


        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarEdt(edtLab)){

                        if(validarEdt(edtPlanta)){
                            int nEdi = listEdificios.get(spinner.getSelectedItemPosition()).getId();
                            laboratorio = new LaboratorioEntity(
                                    0,
                                    edtLab.getText().toString(),
                                    nEdi,
                                    edtPlanta.getText().toString()
                            );
                            laboratorioDao.save(laboratorio);
                            Toast.makeText( getApplicationContext(),"GUARDADO CON EXITO", Toast.LENGTH_LONG ).show();
                            Intent intent = new Intent(getApplicationContext(), AdmiListadoLaboratorio.class);
                            startActivity(intent);
                        }

                }
            }
        });
        btnCancelar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(getApplicationContext(), AdmiMainActivity.class);
                startActivity(regresar);
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

    public boolean validarSpinner(Spinner spinner){
        if(spinner.getSelectedItemPosition() == 0){
            spinner.requestFocus();
            Toast.makeText(getApplicationContext(), "SELECCIONE UN EDIFICIO", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
