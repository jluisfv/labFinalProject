package com.example.project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.dao.EdificioDao;
import com.example.project.dao.LaboratorioDao;
import com.example.project.entidad.EdificioEntity;
import com.example.project.entidad.LaboratorioEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.CoreComponentFactory;

public class AdmiModificarLaboratorio extends AppCompatActivity {
    Button btnGuardar, btnCancelar;
    TextView tvNombre;
    EditText edtplanta;
    LaboratorioDao laboratorioDao;
    EdificioDao edificioDao;
    ArrayList<LaboratorioEntity> laboratorio;
    ArrayList<EdificioEntity> listEdificios;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_modificar_laboratorio);
        setTitle("Modificar Laboratorio");
        btnGuardar = findViewById(R.id.btnGuardar );
        btnCancelar = findViewById( R.id.btnCancelar );
        tvNombre = findViewById(R.id.textView32);
        edtplanta = findViewById(R.id.editText27);

        laboratorioDao = new LaboratorioDao(getApplicationContext());
        edificioDao = new EdificioDao(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        laboratorio = (ArrayList<LaboratorioEntity>) bundle.getSerializable("laboratorio");
        listEdificios = edificioDao.getList();

        ArrayList<String> encabezadoLista = new ArrayList<>();
        String edificioSeleccionado = "";
        encabezadoLista.add("");
        for (EdificioEntity edificio: listEdificios) {
            encabezadoLista.add(edificio.getNombre());
            if(edificio.getId() == laboratorio.get(0).getIdEdificio()) edificioSeleccionado = edificio.getNombre();
        }
        spinner = findViewById(R.id.spinnerLab);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, encabezadoLista);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(edificioSeleccionado));
        tvNombre.setText(laboratorio.get(0).getNombre());
        edtplanta.setText(laboratorio.get(0).getNivel());

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarSpinner(spinner)) {
                    if (validarEdt(edtplanta)) {
                        int nEdi = spinner.getSelectedItemPosition() - 1;
                        LaboratorioEntity lab = new LaboratorioEntity(
                                laboratorio.get(0).getId(),
                                laboratorio.get(0).getNombre(),
                                listEdificios.get(nEdi).getId(),
                                edtplanta.getText().toString()
                        );
                        laboratorioDao.update(lab);
                        Toast.makeText(getApplicationContext(), "GUARDADO CON EXITO", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
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
        if (edt.getText().toString().isEmpty()){
            edt.setError("Campo Requerido");
            edt.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validarSpinner(Spinner spinner){
        if(spinner.getSelectedItemPosition() == 0){
            spinner.requestFocus();
            Toast.makeText(getApplicationContext(), "SELECCIONE UN EDIFICIO", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
