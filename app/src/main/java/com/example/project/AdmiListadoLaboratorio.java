package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.dao.LaboratorioDao;
import com.example.project.entidad.LaboratorioEntity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdmiListadoLaboratorio extends AppCompatActivity {
    ListView listView;

    LaboratorioDao laboratorioDao;
    ArrayList<LaboratorioEntity> laboratorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_listado_laboratorio);
        setTitle("Laboratorios");
        listView = (ListView)findViewById(R.id.Listado);

        laboratorioDao = new LaboratorioDao(getApplicationContext());
        laboratorios = laboratorioDao.getList();
        final ArrayList<String> listadoEncabezado = new ArrayList<>();
        for (LaboratorioEntity laboratorio:laboratorios){
            listadoEncabezado.add(laboratorio.getNombre() +"\n ");
        }

        listView.setAdapter( new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, listadoEncabezado));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), AdmiModificarLaboratorio.class);
                ArrayList<LaboratorioEntity> laboratorio = new ArrayList<>();
                laboratorio.add(laboratorios.get(i));
                intent.putExtra("laboratorio", laboratorio);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(laboratorios != null || laboratorios.size() > 0)laboratorios.clear();
            laboratorios = laboratorioDao.getList();
            final ArrayList<String> listadoEncabezado = new ArrayList<>();
            for (LaboratorioEntity laboratorio:laboratorios){
                listadoEncabezado.add(laboratorio.getNombre() +"\n ");
            }

            listView.setAdapter( new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, listadoEncabezado));
            Toast.makeText(getApplicationContext(), "ENCARGADO MODIFICADO CON EXITO", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "MODIFICACION CANCELADA", Toast.LENGTH_LONG).show();
        }
    }
}
