package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EncargadoHorariosInstructores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_encargado_horarios_instructores );

        setTitle("Horarios por Instructor");

        ArrayList<String> horarios = new ArrayList<>();


        horarios.add("Juan Perez");


        ArrayAdapter<String> data = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,horarios);

        ListView listView = findViewById(R.id.Instructor);
        listView.setAdapter(data);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent detalle = new Intent(getApplicationContext(), EncargadoModificarHorarios.class);
                startActivity(detalle);
            }
        });
    }
}
