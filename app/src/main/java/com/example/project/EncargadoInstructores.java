package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project.dao.LaboratorioDao;
import com.example.project.entidad.LaboratorioEntity;

import java.util.ArrayList;

public class EncargadoInstructores extends AppCompatActivity {

    LaboratorioDao laboratorioDao;
    ListView listView;
    ArrayList<LaboratorioEntity> laboratorioEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encargado_activity_instructores);
        setTitle("Laboratorio");

        listView = findViewById(R.id.listadoLabs);
        laboratorioDao = new LaboratorioDao(getApplicationContext());
        laboratorioEntities = laboratorioDao.getList();


        ArrayList<String> historial = new ArrayList<>();

        for (LaboratorioEntity lab : laboratorioEntities)
        {
            historial.add(lab.getNivel());
        }

        ArrayAdapter<String> data = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,historial);
        listView.setAdapter(data);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent historial_detalle = new Intent(getApplicationContext(), EncargadoInstructoresLabListado.class);
                startActivity(historial_detalle);
            }
        });
    }
}
