package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.project.dao.CicloDao;
import com.example.project.entidad.CicloEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AdmiCrearCiclo extends AppCompatActivity {
    EditText edtFInicio,edtFFin, edtciclo;
    RadioButton raac,rinn;
    Calendar calendario = Calendar.getInstance();
    Button btnGuardar,btnCancelar;

    CicloDao cicloDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_ciclo );
        setTitle("Crear Ciclo");
        cicloDao = new CicloDao(getApplicationContext());

        edtFFin = findViewById(R.id.edtFFin );
        edtFInicio = findViewById( R.id.edtFInicio );
        edtciclo = findViewById(R.id.edtCiclo);
        btnGuardar = findViewById( R.id.btnGuardar );
        btnCancelar = findViewById( R.id.btnCancelar );
        raac = findViewById(R.id.radioButton5);
        raac.setChecked(true);
        rinn = findViewById(R.id.radioButton6);

        edtFInicio.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( AdmiCrearCiclo.this,date2, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        } );

        edtFFin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( AdmiCrearCiclo.this,date, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH)).show();
            }

        } );

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validarEdt(edtciclo)){
                    if(validarEdt(edtFInicio)){
                        if(validarEdt(edtFFin)){

                                cicloDao.save(new CicloEntity(
                                        0,
                                        edtciclo.getText().toString(),
                                        edtFInicio.getText().toString(),
                                        edtFFin.getText().toString(),
                                        (raac.isChecked())?"ACTIVO":"INACTIVO"
                                ));
                                Toast.makeText(getApplicationContext(), "CICLO GUARDADO EXITOSAMENTE", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent(getApplicationContext(), AdmiListadoCiclo.class);
                                startActivity(intent);
                        }
                    }
                }

            }
        } );


        btnCancelar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent( getApplicationContext(), AdmiMainActivity.class );
                startActivity( regresar );
            }
        } );
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, month);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }
    };

    private void actualizarInput() {
        String formatoDeFecha = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        edtFFin.setText(sdf.format(calendario.getTime()));
    }

    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, month);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput2();
        }
    };

    private void actualizarInput2() {
        String formatoDeFecha = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        edtFInicio.setText(sdf.format(calendario.getTime()));
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
