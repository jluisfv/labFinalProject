package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.project.dao.AsignacionEncargadoDao;
import com.example.project.dao.CicloDao;
import com.example.project.dao.DetAsigEncargadoDao;
import com.example.project.dao.DetHorasDao;
import com.example.project.dao.HorasDao;
import com.example.project.dao.LaboratorioDao;
import com.example.project.dao.UsuarioDao;
import com.example.project.entidad.AsignacionEncargadoEntity;
import com.example.project.entidad.CicloEntity;
import com.example.project.entidad.DetAsigEncargadoEntity;
import com.example.project.entidad.DetHorasEntity;
import com.example.project.entidad.InstructorEntity;
import com.example.project.entidad.LaboratorioEntity;
import com.example.project.entidad.UsuarioEntity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class EncargadoCrearHorario extends AppCompatActivity {
    EditText edtHEntrada, edtHSalida, edtInicio, edtFFin;
    Calendar calendario = Calendar.getInstance( );
    Button btnGuardar, btnCancelar;
    Spinner spinner11,spinner,spinner3;
    CheckBox c1,c2,c3,c4,c5,c6,c7;

    LaboratorioDao laboratorioDao;
    UsuarioDao usuarioDao;
    CicloDao cicloDao;
    HorasDao horasDao;
    DetHorasDao detHorasDao;
    InstructorEntity instructor;

    ArrayList<LaboratorioEntity> laboratorios;
    ArrayList<CicloEntity> ciclos;
    ArrayList<UsuarioEntity> instructores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_encargado_crear_horario );
        setTitle( "Crear horarios" );

        edtHEntrada = findViewById( R.id.edtHEntrada );
        edtHSalida = findViewById( R.id.edtHSalida );

        edtFFin = findViewById( R.id.edtFFin );
        edtInicio = findViewById( R.id.edtInicio );

        btnCancelar = findViewById( R.id.btnCancelar );
        btnGuardar = findViewById( R.id.btnGuardar );

        spinner = findViewById(R.id.spinner);
        spinner11 = findViewById(R.id.spinner11);
        spinner3 = findViewById(R.id.spinner3);

        c1 = findViewById(R.id.checkBox8);
        c2 = findViewById(R.id.checkBox9);
        c3 = findViewById(R.id.checkBox10);
        c4 = findViewById(R.id.checkBox11);
        c5 = findViewById(R.id.checkBox13);
        c6 = findViewById(R.id.checkBox12);
        c7 = findViewById(R.id.checkBox14);

        laboratorioDao = new LaboratorioDao(getApplicationContext());
        usuarioDao = new UsuarioDao(getApplicationContext());
        horasDao = new HorasDao(getApplicationContext());
        detHorasDao = new DetHorasDao(getApplicationContext());
        cicloDao = new CicloDao(getApplicationContext());

        instructores = usuarioDao.list("INSTRUCTOR");
        ciclos = cicloDao.getList();
        laboratorios = laboratorioDao.getList();

        ArrayList<String> encabezadoLabs = new ArrayList<>();
        for(LaboratorioEntity lab:laboratorios){
            encabezadoLabs.add(lab.getNombre());
        }
        ArrayList<String> encabezadoIns = new ArrayList<>();
        for(UsuarioEntity encargado:instructores){
            encabezadoIns.add(encargado.getNombre());
        }
        ArrayList<String> encabezadoCiclos = new ArrayList<>();
        for(CicloEntity ciclo:ciclos){
            encabezadoCiclos.add(ciclo.getCodigo());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, encabezadoLabs);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner11.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, encabezadoIns);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, encabezadoCiclos);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        edtHSalida.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance( );
                int hour = mcurrentTime.get( Calendar.HOUR_OF_DAY );
                int minute = mcurrentTime.get( Calendar.MINUTE );

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog( EncargadoCrearHorario.this, new TimePickerDialog.OnTimeSetListener( ) {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edtHSalida.setText( selectedHour + ":" + selectedMinute );
                    }
                }, hour, minute, true );
                mTimePicker.setTitle( "Select Time" );
                mTimePicker.show( );

            }

        } );

        edtHEntrada.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance( );
                int hour = mcurrentTime.get( Calendar.HOUR_OF_DAY );
                int minute = mcurrentTime.get( Calendar.MINUTE );

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog( EncargadoCrearHorario.this, new TimePickerDialog.OnTimeSetListener( ) {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edtHEntrada.setText( selectedHour + ":" + selectedMinute );
                    }
                }, hour, minute, true );
                mTimePicker.setTitle( "Select Time" );
                mTimePicker.show( );

            }
        } );

        edtInicio.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( EncargadoCrearHorario.this, date2, calendario.get( Calendar.YEAR ), calendario.get( Calendar.MONTH ), calendario.get( Calendar.DAY_OF_MONTH ) ).show( );
            }
        } );

        edtFFin.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( EncargadoCrearHorario.this, date, calendario.get( Calendar.YEAR ), calendario.get( Calendar.MONTH ), calendario.get( Calendar.DAY_OF_MONTH ) ).show( );
            }

        } );

        btnGuardar.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                int idLab = laboratorios.get(spinner11.getSelectedItemPosition()).getId();
                int idEnc = instructores.get(spinner.getSelectedItemPosition()).getId();
                int idCiclo = ciclos.get(spinner3.getSelectedItemPosition()).getId();
                String entrada = edtHEntrada.getText().toString();
                String salida = edtHSalida.getText().toString();

                if(TextUtils.isEmpty(spinner.getSelectedItem().toString())){
                    Toast.makeText(getApplicationContext(), "Selecione un Laboratorio", Toast.LENGTH_LONG).show();
                }else
                if(TextUtils.isEmpty(spinner3.getSelectedItem().toString())){
                    Toast.makeText(getApplicationContext(), "Selecione un Instructor", Toast.LENGTH_LONG).show();
                }else
                if(TextUtils.isEmpty(spinner11.getSelectedItem().toString())){
                    Toast.makeText(getApplicationContext(), "Selecione el Ciclo", Toast.LENGTH_LONG).show();
                }else
                if(validate(edtInicio)){
                    if(validate( edtFFin)){
                        if (validate(edtHEntrada)){
                            if (validate(edtHSalida)){
                                int idGenerado = (int) horasDao.save(
                                        new InstructorEntity(0, idEnc, idLab, idCiclo)
                                );

                                if(c1.isChecked()){ //Lunes
                                    detHorasDao.save(new DetHorasEntity(0, 1,entrada, salida, idGenerado));
                                }
                                if(c2.isChecked()){//Martes
                                    detHorasDao.save(new DetHorasEntity(0, 2,entrada, salida, idGenerado));
                                }
                                if(c3.isChecked()){//Miercoles
                                    detHorasDao.save(new DetHorasEntity(0, 3,entrada, salida, idGenerado));
                                }
                                if(c4.isChecked()){//Jueves
                                    detHorasDao.save(new DetHorasEntity(0, 4,entrada, salida, idGenerado));
                                }
                                if(c5.isChecked()){//Viernes
                                    detHorasDao.save(new DetHorasEntity(0, 5,entrada, salida, idGenerado));
                                }
                                if(c6.isChecked()){//Sabado
                                    detHorasDao.save(new DetHorasEntity(0, 6,entrada, salida, idGenerado));
                                }
                                if(c7.isChecked()){//Domingo
                                    detHorasDao.save(new DetHorasEntity(0, 7,entrada, salida, idGenerado));
                                }
                                Toast.makeText( getApplicationContext(),"Datos Almacenados", Toast.LENGTH_LONG ).show();
                                Intent intent = new Intent(getApplicationContext(), EncargadoHorarios.class);
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        } );
        btnCancelar.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent cancelar = new Intent( getApplicationContext( ), EncargadoMainActivity.class );
                startActivity( cancelar );

            }
        } );
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener( ) {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            calendario.set( Calendar.YEAR, year );
            calendario.set( Calendar.MONTH, month );
            calendario.set( Calendar.DAY_OF_MONTH, dayOfMonth );
            actualizarInput( );

        }
    };

    private void actualizarInput() {
        String formatoDeFecha = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat( formatoDeFecha, Locale.US );

        edtFFin.setText( sdf.format( calendario.getTime( ) ) );

    }

    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener( ) {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            calendario.set( Calendar.YEAR, year );
            calendario.set( Calendar.MONTH, month );
            calendario.set( Calendar.DAY_OF_MONTH, dayOfMonth );
            actualizarInput1( );

        }
    };

    private void actualizarInput1() {
        String formatoDeFecha = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat( formatoDeFecha, Locale.US );

        edtInicio.setText( sdf.format( calendario.getTime( ) ) );

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
