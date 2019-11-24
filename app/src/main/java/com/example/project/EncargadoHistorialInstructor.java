package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EncargadoHistorialInstructor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encargado_activity_historial_instructor);

        setTitle("Historial del instructor");

        ArrayList<String> historial = new ArrayList<>();

        historial.add("Maria Benitez Ma-Ju 8:00-12:00 ");
        historial.add("Carlos Rodriguez Sab 8:00-12:00 ");
        historial.add("Juan Perez Lu-Vi 9:30-11:00 ");

        ArrayAdapter<String> data = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,historial);

        ListView listView = (ListView) findViewById(R.id.listadoHistorialInstructores);
        listView.setAdapter(data);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent historial_detalle = new Intent(getApplicationContext(), EncargadoHistorialDetalleInstructor.class);
                startActivity(historial_detalle);
            }
        });

    }
}
