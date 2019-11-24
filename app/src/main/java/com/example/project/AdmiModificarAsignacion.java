package com.example.project;

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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AdmiModificarAsignacion extends AppCompatActivity {

    EditText edtHEntrada, edtHSalida, edtlab;
    Button btnGuardar, btnCancelar;
    CheckBox c1,c2,c3,c4,c5,c6,c7;
    SQLiteDatabase bd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_modificar_asignacion);
        //BD objbase = new BD(getApplicationContext(),"DB",null,1);
        //bd = objbase.getWritableDatabase();
        c1 = findViewById(R.id.checkBox);
        c2 = findViewById(R.id.checkBox2);
        c3 = findViewById(R.id.checkBox3);
        c4 = findViewById(R.id.checkBox4);
        c5 = findViewById(R.id.checkBox5);
        c6 = findViewById(R.id.checkBox6);
        c7 = findViewById(R.id.checkBox7);
        edtlab = findViewById(R.id.editText11);

        edtHEntrada = findViewById( R.id.editText18 );
        edtHSalida = findViewById( R.id.editText19 );

        btnCancelar =findViewById( R.id.btnCancelar );
        btnGuardar = findViewById( R.id.btnGuardar );

        final Spinner spinner = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.laboratorios_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Spinner encargado = (Spinner) findViewById(R.id.spinner5);
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
                mTimePicker = new TimePickerDialog(AdmiModificarAsignacion.this, new TimePickerDialog.OnTimeSetListener() {
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
                mTimePicker = new TimePickerDialog(AdmiModificarAsignacion.this, new TimePickerDialog.OnTimeSetListener() {
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
                String upd =edtlab.getText().toString();
                String lab = spinner.getSelectedItem().toString();
                String encar = encargado.getSelectedItem().toString();
                String dias = (c1.isChecked())?"lunes":(c2.isChecked())?"martes":(c3.isChecked())?"miercoles":(c4.isChecked())?"jueves":(c5.isChecked())?"viernes":(c6.isChecked())?"sabado":"dominggo";
                String entrada = edtHEntrada.getText().toString();
                String salida = edtHSalida.getText().toString();

                bd.execSQL("update asignacion SET nombrelab='"+lab+"',encag='"+encar+"', diasasig='"+dias+"',hentrada='"+entrada+"',hsalida='"+salida+"' where nombrelab ='"+upd+"' ");
                Toast.makeText(getApplicationContext(),"Datos modificados", Toast.LENGTH_LONG ).show();
            }
        } );

        btnCancelar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj3 = new Intent( getApplicationContext(), AdmiListadoAsignacion.class );
                startActivity( obj3 );
            }
        } );
    }
}
