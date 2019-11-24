package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdmiListadoAsignacion extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_listado_asignacion);

        setTitle("Asignaciones");

        ArrayList<String> historial = new ArrayList<>();


        historial.add("Laboratorio 1 Juan Perez");
        historial.add("Laboratorio 2 Maria Martinez");
        historial.add("Laboratorio 3 Luis Felix");

        ArrayAdapter<String> data = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,historial);

        ListView listView = (ListView) findViewById(R.id.Asignacion);
        listView.setAdapter(data);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent historial_detalle = new Intent(getApplicationContext(), AdmiModificarAsignacion.class);
                startActivity(historial_detalle);
            }
        });
    }
}
