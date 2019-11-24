package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project.dao.LaboratorioDao;
import com.example.project.entidad.LaboratorioEntity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdmiListadoLaboratorio extends AppCompatActivity {

    LaboratorioDao laboratorioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_listado_laboratorio);
        setTitle("Laboratorios");

        laboratorioDao = new LaboratorioDao(getApplicationContext());
        final ArrayList<String> listadoEncabezado = new ArrayList<>();

        for (LaboratorioEntity laboratorio:laboratorioDao.getList()){
            listadoEncabezado.add(laboratorio.getNombre());
        }

        final ArrayAdapter<String> data = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, listadoEncabezado);

        ListView listView = (ListView) findViewById(R.id.Listado);
        listView.setAdapter(data);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nlab = data.getItem(i);
                Intent historial_detalle = new Intent(getApplicationContext(), AdmiModificarLaboratorio.class);
                historial_detalle.putExtra("nlab",nlab);
                startActivity(historial_detalle);
            }
        });

    }
}
