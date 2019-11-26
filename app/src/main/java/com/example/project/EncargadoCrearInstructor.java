package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.dao.UsuarioDao;
import com.example.project.entidad.UsuarioEntity;

public class EncargadoCrearInstructor extends AppCompatActivity {
    EditText nombre,apellido,correo,clave;
    RadioButton activo, inactivo;
    Button btnCancelar,btnGuardar;
    UsuarioEntity usuarioEntity;
    UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encargado_activity_crear_instructor);

        usuarioDao = new UsuarioDao(getApplicationContext());

        nombre = findViewById(R.id.editText8);
        apellido = findViewById(R.id.editText7);
        correo = findViewById(R.id.editText);
        clave = findViewById(R.id.editText6);
        activo = findViewById(R.id.rdbEstadoActivo);
        inactivo = findViewById(R.id.rdbEstadoInactivo);


        setTitle("Crear instructor");


        btnCancelar = findViewById( R.id.btnCancelar );
        btnGuardar = findViewById( R.id.btnGuardar );

        btnGuardar.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {

                if(validate(nombre)){
                    if(validate(apellido)){
                        if(validate(correo)){
                            if(validate(clave)){


                String estado = "";
                if(activo.isChecked())
                {
                    estado="Activo";
                }else{
                    estado="Inactivo";
                }

                usuarioEntity = new UsuarioEntity(
                        0,
                        nombre.getText().toString(),
                        apellido.getText().toString(),
                        correo.getText().toString(),
                        clave.getText().toString(),
                        "INSTRUCTOR",
                        "ACTIVO");
                usuarioDao.save(usuarioEntity);

                Toast.makeText( getApplicationContext(),"Datos Almacenados",Toast.LENGTH_LONG ).show();
                            }}}}
            }
        } );

        btnCancelar.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent cancelar = new Intent(getApplicationContext(),EncargadoMainActivity.class);
                startActivity( cancelar );
            }
        } );
    }
    boolean validate(EditText edt){
        boolean result = true;
        if (TextUtils.isEmpty(edt.getText())){
            result = false;
            edt.setError("Campo Requerido");
            edt.requestFocus();
        }
        return result;
    }
}
