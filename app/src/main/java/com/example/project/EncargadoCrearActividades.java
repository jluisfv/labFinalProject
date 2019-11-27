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
    int id = -1;
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
        ActividadEntity act;


        //se obtiene el id si es editar se usa la misma actividad
        if(getIntent()!=null && getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
                String acti = getIntent().getExtras().getString("actividad");
                if(acti != null)
                {
                    act = actividad.getActivity(acti);
                    id = act.getId();
                    edtActividad.setText(act.getActividad());
                    edtDetalle.setText(act.getDescripcion());
                    if(act.getEstado().equals("ACTIVO"))
                    {
                        r1.setChecked(true);
                    }else {
                        r2.setChecked(true);
                    }
                }


        }

        btnGuardar.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                if(validar(edtActividad)){
                    if(validar(edtDetalle)){
                        ActividadEntity actividadEntity = new ActividadEntity(
                                id,
                                edtActividad.getText().toString(),
                                edtDetalle.getText().toString(),
                                (r1.isChecked())?"ACTIVO": "INACTIVO");
                        if(id != -1)
                        {
                            actividad.update(actividadEntity);
                        }else {
                            actividad.save(actividadEntity);
                        }

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
