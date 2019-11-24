package com.example.project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.dao.UsuarioDao;
import com.example.project.entidad.UsuarioEntity;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class AdmiModificarEncargado extends AppCompatActivity {
    Button btnGuardar, btnCancelar;
    SQLiteDatabase bd;
    TextView tvencar;
    EditText edtNombre, edtApellido, edtCorreo, edtContra;
    RadioButton ra,ri;
    UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_modificar_encargado);
        setTitle("Modificar Encargado");
        usuarioDao = new UsuarioDao(getApplicationContext());
        btnGuardar = findViewById( R.id.btnGuardar );
        btnCancelar = findViewById( R.id.btnCancelar );

        edtNombre = findViewById(R.id.edtNombre);
        edtApellido = findViewById(R.id.edtApellido);
        edtCorreo = findViewById(R.id.editText4);
        edtContra = findViewById(R.id.editText5);
        ra = findViewById(R.id.radioButton);
        ri = findViewById(R.id.radioButton2);
        Bundle bundle = getIntent().getExtras();

        final ArrayList<UsuarioEntity> encargado = (ArrayList<UsuarioEntity>) bundle.getSerializable("encargado");

        edtNombre.setText(encargado.get(0).getNombre());
        edtApellido.setText(encargado.get(0).getApellido());
        edtCorreo.setText(encargado.get(0).getEmail());
        edtContra.setText(encargado.get(0).getClave());
        if (encargado.get(0).getEstado().equals("ACTIVO")){
            ra.setChecked(true);
        }else{
            ri.setChecked(true);
        }

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(validarEdt(edtNombre)){
                        if(validarEdt(edtApellido)){
                            if(validarEdt(edtCorreo)){
                                if(validarEdt(edtContra)){
                                    usuarioDao.update(new UsuarioEntity(
                                            encargado.get(0).getId(),//Los obtengo de l alisto porque no se modificaran
                                            edtNombre.getText().toString(),
                                            edtApellido.getText().toString(),
                                            edtCorreo.getText().toString(),
                                            edtContra.getText().toString(),
                                            encargado.get(0).getTipo(),
                                            (ra.isChecked())? "ACTIVO":"INACTIVO"
                                    ));

                                    Intent intent = new Intent();
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            }

                        }
                    }

            }
        } );

        btnCancelar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
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
