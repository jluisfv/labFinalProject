package com.example.project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.project.dao.UsuarioDao;
import com.example.project.entidad.UsuarioEntity;

import androidx.appcompat.app.AppCompatActivity;

public class AdmiCrearEncargado extends AppCompatActivity {
    Button btnGuardar, btnCancelar;

    EditText edtNombre, edtApellido, edtUsuario, edtClave;
    RadioButton rbActivo,rbInactivo;
    UsuarioEntity usuarioEntity;
    UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_crear_encargado);
        setTitle("Crear Encargado");

        edtNombre = findViewById(R.id.edtNombre);
        edtApellido = findViewById(R.id.edtApellido);
        edtUsuario = findViewById(R.id.edtEmail);
        edtClave = findViewById(R.id.edtClave);
        rbActivo = findViewById(R.id.rbAct);
        rbInactivo = findViewById(R.id.rbInc);
        btnGuardar = findViewById( R.id.btnGuardar );
        btnCancelar = findViewById( R.id.btnCancelar );

        usuarioDao = new UsuarioDao(getApplicationContext());

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validarEdt(edtNombre)){
                    if(validarEdt(edtApellido)){
                        if(validarEdt(edtUsuario)){
                            if(validarEdt(edtClave)){

                                usuarioEntity = new UsuarioEntity(
                                        0,
                                        edtNombre.getText().toString(),
                                        edtApellido.getText().toString(),
                                        edtUsuario.getText().toString(),
                                        edtClave.getText().toString(),
                                        "ENCARGADO",
                                        "ACTIVO");
                                usuarioDao.save(usuarioEntity);
                                Toast.makeText(getApplicationContext(), "CREADO EXITOSAMENTE", Toast.LENGTH_LONG).show();
                                Intent result = new Intent( getApplicationContext(), AdmiListadoEncargados.class);
                                startActivity(result);
                            }
                        }
                    }
                }
            }
        } );

        btnCancelar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent( getApplicationContext(), AdmiMainActivity.class ) ;
                startActivity( regresar );
            }
        } );

    }

    public boolean validarEdt(EditText edt){
        boolean res = true;
        if (edt.getText().toString().isEmpty()){
            edt.setError("Campo Requerido");
            edt.requestFocus();
            res = false;
        }
        return res;
    }
}
