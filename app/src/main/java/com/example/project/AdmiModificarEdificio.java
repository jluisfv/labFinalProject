package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.project.dao.EdificioDao;
import com.example.project.entidad.EdificioEntity;

import java.util.ArrayList;

public class AdmiModificarEdificio extends AppCompatActivity {
    Button btnGuardar,btnCancelar;
    EditText edtPref,edtNombre;
    RadioButton ra,ri;
    EdificioDao edificioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.admi_activity_modificar_edificio);
        setTitle("Modificar Edificio");
        edificioDao = new EdificioDao(getApplicationContext());

        edtPref = findViewById(R.id.edtPrefijo);
        edtNombre = findViewById(R.id.edtNombre);
        ra = findViewById(R.id.radioButton7);
        ri = findViewById(R.id.radioButton8);

        btnCancelar = findViewById( R.id.btnCancelar );
        btnGuardar = findViewById( R.id.btnGuardar );
        Bundle bundle = getIntent().getExtras();
        final ArrayList<EdificioEntity> edificio = (ArrayList<EdificioEntity>) bundle.getSerializable("edificio");
        edtPref.setText(edificio.get(0).getPrefijo());
        edtNombre.setText(edificio.get(0).getNombre());
        if(edificio.get(0).getEstado().equals("ACTIVO")){
            ra.setChecked(true);
        }else{
            ri.setChecked(true);
        }

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarEdt(edtNombre)){
                    if(validarEdt(edtPref)){

                        EdificioEntity edi = new EdificioEntity(
                                edificio.get(0).getId(),
                                edtNombre.getText().toString(),
                                edtPref.getText().toString(),
                                (ra.isChecked())? "ACTIVO":"INACTIVO"
                        );
                        edificioDao.update(edi);
                        Intent regresar = new Intent();
                        setResult(RESULT_OK, regresar);
                        finish();
                    }
                }

            }
        } );

        btnCancelar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent();
                setResult(RESULT_CANCELED, regresar);
                finish();
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
