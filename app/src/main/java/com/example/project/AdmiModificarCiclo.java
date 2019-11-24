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
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.dao.CicloDao;
import com.example.project.entidad.CicloEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AdmiModificarCiclo extends AppCompatActivity {
    EditText edtFInicio,edtFFin;
    TextView tv;
    RadioButton rac,rin;
    Calendar calendario = Calendar.getInstance();
    Button btnGuardar,btnCancelar;
    CicloDao cicloDao;
    ArrayList<CicloEntity> ciclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.admi_activity_modificar_ciclo);
        setTitle("Modificar Ciclo");
        cicloDao = new CicloDao(getApplicationContext());

        edtFFin = findViewById(R.id.edtFFin );
        edtFInicio = findViewById( R.id.edtFInicio );
        rac = findViewById(R.id.radioButton5);
        rin = findViewById(R.id.radioButton6);
        btnGuardar = findViewById( R.id.btnGuardar );
        btnCancelar = findViewById( R.id.btnCancelar );
        tv = findViewById(R.id.textView29);

        Bundle bundle = getIntent().getExtras();
        ciclo = (ArrayList<CicloEntity>) bundle.getSerializable("ciclo");

        tv.setText("CICLO: "+ ciclo.get(0).getCodigo());

        edtFInicio.setText(ciclo.get(0).getFechaInicio());
        edtFFin.setText(ciclo.get(0).getFechaFin());
        if(ciclo.get(0).getEstado().equals("ACTIVO")){
            rac.setChecked(true);
        }else{
            rin.setChecked(true);
        }

        edtFInicio.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( AdmiModificarCiclo.this,date1, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        } );

        edtFFin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( AdmiModificarCiclo.this,date2, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH),calendario.get(Calendar.DAY_OF_MONTH)).show();
            }

        } );

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CicloEntity ci = new CicloEntity(
                        ciclo.get(0).getId(),
                        ciclo.get(0).getCodigo(),
                        edtFInicio.getText().toString(),
                        edtFFin.getText().toString(),
                        (rac.isChecked())? "ACTIVO":"INACTIVO"
                );
                cicloDao.update(ci);
                Intent regresar = new Intent();
                setResult(RESULT_OK, regresar);
                finish();

            }
        });


        btnCancelar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent();
                setResult(RESULT_CANCELED, regresar);
                finish();
            }
        });
    }

    DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, month);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput(edtFInicio);
        }
    };

    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, month);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput(edtFFin);
        }
    };

    private void actualizarInput(EditText edt) {
        String formatoDeFecha = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);
        edt.setText(sdf.format(calendario.getTime()));
    }

    public boolean validarEdt(EditText edt){
        if (edt.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos", Toast.LENGTH_LONG).show();
            edt.requestFocus();
            return false;
        }
        return true;
    }

}
