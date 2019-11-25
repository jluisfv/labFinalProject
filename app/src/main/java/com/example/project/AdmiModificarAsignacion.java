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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class AdmiModificarAsignacion extends AppCompatActivity {

    EditText edtHEntrada, edtHSalida, edtlab;
    Button btnGuardar, btnCancelar;
    CheckBox c1,c2,c3,c4,c5,c6,c7;
    Spinner spinnerLabs;
    Spinner spinnerCiclos;

    LaboratorioDao laboratorioDao;
    AsignacionEncargadoDao asignacionDao;
    DetAsigEncargadoDao detalleDao;
    CicloDao cicloDao;

    AsignacionEncargadoEntity asignacion;
    ArrayList<LaboratorioEntity> laboratorios;
    ArrayList<CicloEntity> ciclos;
    int idAsig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_modificar_asignacion);
        setTitle("Modificar Asignación");

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
        spinnerLabs = findViewById(R.id.spinnerLabs);
        spinnerCiclos = findViewById(R.id.spinnerCiclos);
        laboratorioDao = new LaboratorioDao(getApplicationContext());
        asignacionDao = new AsignacionEncargadoDao(getApplicationContext());
        detalleDao = new DetAsigEncargadoDao(getApplicationContext());
        cicloDao = new CicloDao(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        asignacion = asignacionDao.findById(bundle.getInt("asignacion"));

        laboratorios = laboratorioDao.getList();
        ciclos = cicloDao.getList();
        String labDefault = "";
        String cicloDefault = "";
        ArrayList<String> encabezadoLabs = new ArrayList<>();
        for(LaboratorioEntity lab:laboratorios){
            encabezadoLabs.add(lab.getNombre());
            if(lab.getId() == asignacion.getIdLab()) labDefault = lab.getNombre();
        }
        ArrayList<String> encabezadoCiclos = new ArrayList<>();
        for(CicloEntity ciclo:ciclos){
            encabezadoCiclos.add(ciclo.getCodigo());
            if(ciclo.getId() == asignacion.getIdCiclo()) cicloDefault = ciclo.getCodigo();
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, encabezadoLabs);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLabs.setAdapter(adapter1);
        spinnerLabs.setSelection(adapter1.getPosition(labDefault));

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, encabezadoCiclos);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCiclos.setAdapter(adapter3);
        spinnerCiclos.setSelection(adapter3.getPosition(cicloDefault));

        for (DetAsigEncargadoEntity detalle : detalleDao.findByIdAsig(asignacion.getId())){
            if(detalle.getDia()== 1) c1.setChecked(true);
            if(detalle.getDia()== 2) c2.setChecked(true);
            if(detalle.getDia()== 3) c3.setChecked(true);
            if(detalle.getDia()== 4) c4.setChecked(true);
            if(detalle.getDia()== 5) c5.setChecked(true);
            if(detalle.getDia()== 6) c6.setChecked(true);
            if(detalle.getDia()== 7) c7.setChecked(true);
            edtHEntrada.setText(detalle.getHoraInicio());
            edtHSalida.setText(detalle.getHoraFin());
        }

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

                int idLab = laboratorios.get(spinnerLabs.getSelectedItemPosition()).getId();
                int idCiclo = ciclos.get(spinnerCiclos.getSelectedItemPosition()).getId();
                String entrada = edtHEntrada.getText().toString();
                String salida = edtHSalida.getText().toString();
                asignacionDao.update(new AsignacionEncargadoEntity(asignacion.getId(), asignacion.getIdEmpleado(), idLab, idCiclo));
                detalleDao.deleteByIdAsig(asignacion.getId());
                if(c1.isChecked()){ //Lunes
                    detalleDao.save(new DetAsigEncargadoEntity(0, 1,entrada, salida, asignacion.getId()));
                }
                if(c2.isChecked()){//Martes
                    detalleDao.save(new DetAsigEncargadoEntity(0, 2,entrada, salida, asignacion.getId()));
                }
                if(c3.isChecked()){//Miercoles
                    detalleDao.save(new DetAsigEncargadoEntity(0, 3,entrada, salida, asignacion.getId()));
                }
                if(c4.isChecked()){//Jueves
                    detalleDao.save(new DetAsigEncargadoEntity(0, 4,entrada, salida, asignacion.getId()));
                }
                if(c5.isChecked()){//Viernes
                    detalleDao.save(new DetAsigEncargadoEntity(0, 5,entrada, salida, asignacion.getId()));
                }
                if(c6.isChecked()){//Sabado
                    detalleDao.save(new DetAsigEncargadoEntity(0, 6,entrada, salida, asignacion.getId()));
                }
                if(c7.isChecked()){//Domingo
                    detalleDao.save(new DetAsigEncargadoEntity(0, 7,entrada, salida, asignacion.getId()));
                }
                Toast.makeText(getApplicationContext(),"Datos modificados", Toast.LENGTH_LONG ).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
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
}
