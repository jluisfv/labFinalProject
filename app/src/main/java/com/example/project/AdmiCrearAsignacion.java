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

import com.example.project.dao.AsignacionEncargadoDao;
import com.example.project.dao.CicloDao;
import com.example.project.dao.DetAsigEncargadoDao;
import com.example.project.dao.LaboratorioDao;
import com.example.project.dao.UsuarioDao;
import com.example.project.entidad.AsignacionEncargadoEntity;
import com.example.project.entidad.CicloEntity;
import com.example.project.entidad.DetAsigEncargadoEntity;
import com.example.project.entidad.LaboratorioEntity;
import com.example.project.entidad.UsuarioEntity;

import java.util.ArrayList;
import java.util.Calendar;

public class AdmiCrearAsignacion extends AppCompatActivity {
    EditText edtHEntrada, edtHSalida;
    Button btnGuardar, btnCancelar;
    CheckBox c1,c2,c3,c4,c5,c6,c7;

    LaboratorioDao laboratorioDao;
    UsuarioDao usuarioDao;
    AsignacionEncargadoDao asignacionDao;
    DetAsigEncargadoDao detalleDao;
    CicloDao cicloDao;

    ArrayList<UsuarioEntity> encargados;
    ArrayList<LaboratorioEntity> laboratorios;
    ArrayList<CicloEntity> ciclos;

    Spinner spinnerLabs;
    Spinner spinnerEncar;
    Spinner spinnerCiclos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.admi_activity_crear_asignacion);
        setTitle("Crear Asignación");
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
        spinnerLabs = findViewById(R.id.spinner6);
        spinnerEncar = findViewById(R.id.spinner7);
        spinnerCiclos = findViewById(R.id.spinner8);

        laboratorioDao = new LaboratorioDao(getApplicationContext());
        usuarioDao = new UsuarioDao(getApplicationContext());
        asignacionDao = new AsignacionEncargadoDao(getApplicationContext());
        detalleDao = new DetAsigEncargadoDao(getApplicationContext());
        cicloDao = new CicloDao(getApplicationContext());


        laboratorios = laboratorioDao.getList();
        encargados = usuarioDao.list("ENCARGADO");
        ciclos = cicloDao.getList();

        ArrayList<String> encabezadoLabs = new ArrayList<>();
        for(LaboratorioEntity lab:laboratorios){
            encabezadoLabs.add(lab.getNombre());
        }
        ArrayList<String> encabezadoEncar = new ArrayList<>();
        for(UsuarioEntity encargado:encargados){
            encabezadoEncar.add(encargado.getNombre());
        }
        ArrayList<String> encabezadoCiclos = new ArrayList<>();
        for(CicloEntity ciclo:ciclos){
            encabezadoCiclos.add(ciclo.getCodigo());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, encabezadoLabs);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLabs.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, encabezadoEncar);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEncar.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, encabezadoCiclos);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCiclos.setAdapter(adapter3);

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
                int idLab = laboratorios.get(spinnerLabs.getSelectedItemPosition()).getId();
                int idEnc = encargados.get(spinnerEncar.getSelectedItemPosition()).getId();
                int idCiclo = ciclos.get(spinnerCiclos.getSelectedItemPosition()).getId();
                String entrada = edtHEntrada.getText().toString();
                String salida = edtHSalida.getText().toString();

                int idGenerado = (int) asignacionDao.save(
                        new AsignacionEncargadoEntity(0, idEnc, idLab, idCiclo)
                );

                if(c1.isChecked()){ //Lunes
                    detalleDao.save(new DetAsigEncargadoEntity(0, 1,entrada, salida, idGenerado));
                }
                if(c2.isChecked()){//Martes
                    detalleDao.save(new DetAsigEncargadoEntity(0, 2,entrada, salida, idGenerado));
                }
                if(c3.isChecked()){//Miercoles
                    detalleDao.save(new DetAsigEncargadoEntity(0, 3,entrada, salida, idGenerado));
                }
                if(c4.isChecked()){//Jueves
                    detalleDao.save(new DetAsigEncargadoEntity(0, 4,entrada, salida, idGenerado));
                }
                if(c5.isChecked()){//Viernes
                    detalleDao.save(new DetAsigEncargadoEntity(0, 5,entrada, salida, idGenerado));
                }
                if(c6.isChecked()){//Sabado
                    detalleDao.save(new DetAsigEncargadoEntity(0, 6,entrada, salida, idGenerado));
                }
                if(c7.isChecked()){//Domingo
                    detalleDao.save(new DetAsigEncargadoEntity(0, 7,entrada, salida, idGenerado));
                }
                Toast.makeText( getApplicationContext(),"Datos Almacenados", Toast.LENGTH_LONG ).show();
                Intent intent = new Intent(getApplicationContext(), AdmiListadoAsignacion.class);
                startActivity(intent);
            }
        });

        btnCancelar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent( getApplicationContext(), AdmiMainActivity.class );
                startActivity( regresar );
            }
        } );
    }
}
