package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AdmiCrearAsignacion extends AppCompatActivity {
    EditText edtHEntrada, edtHSalida;
    Button btnGuardar, btnCancelar;
    CheckBox c1,c2,c3,c4,c5,c6,c7;
    SQLiteDatabase bd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.admi_activity_crear_asignacion);
        setTitle("Crear Asignación");

        String tipo = "encargado";

        c1 = findViewById(R.id.checkBox);
        c2 = findViewById(R.id.checkBox2);
        c3 = findViewById(R.id.checkBox3);
        c4 = findViewById(R.id.checkBox4);
        c5 = findViewById(R.id.checkBox5);
        c6 = findViewById(R.id.checkBox6);
        c7 = findViewById(R.id.checkBox7);


        edtHEntrada = findViewById( R.id.editText18 );
        edtHSalida = findViewById( R.id.editText19 );

        btnCancelar =findViewById( R.id.btnCancelar );
        btnGuardar = findViewById( R.id.btnGuardar );



        final Spinner spinner = (Spinner) findViewById(R.id.spinner6);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.laboratorios_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Spinner encargado = (Spinner) findViewById(R.id.spinner7);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.encargados_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        encargado.setAdapter(adapter1);

        edtHSalida.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AdmiCrearAsignacion.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edtHSalida.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }

        } );

        edtHEntrada.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AdmiCrearAsignacion.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edtHEntrada.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        } );

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lab = spinner.getSelectedItem().toString();
                String encar = encargado.getSelectedItem().toString();
                String dias = (c1.isChecked())?"lunes":(c2.isChecked())?"martes":(c3.isChecked())?"miercoles":(c4.isChecked())?"jueves":(c5.isChecked())?"viernes":(c6.isChecked())?"sabado":"dominggo";
                String entrada = edtHEntrada.getText().toString();
                String salida = edtHSalida.getText().toString();
                bd.execSQL("insert into asignacion(nombrelab, encag, diasasig, hentrada, hsalida) values('"+lab+"','"+encar+"','"+dias+"','"+entrada+"','"+salida+"')");
                Toast.makeText( getApplicationContext(),"Datos Almacenados", Toast.LENGTH_LONG ).show();
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
}
