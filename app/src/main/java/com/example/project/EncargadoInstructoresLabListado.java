package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project.R;

import java.util.ArrayList;

public class EncargadoInstructoresLabListado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encargado_activity_instructores_lab_listado);

        setTitle("Instructores");

        ArrayList<String> historial = new ArrayList<>();

        historial.add("Instructor: Juan Carlos \nHorario: Lun-Vi 8:00am-12:00pm");
        historial.add("Instructor: Mario Benitez \nHorario: Lun-Vi 9:30am-12:00pm");
        historial.add("Instructor: Karen Solis \nHorario: Lun-Mi 9:30am-11:00am");


        ArrayAdapter<String> data = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,historial);

        ListView listView = (ListView) findViewById(R.id.instructoresPorLab);
        listView.setAdapter(data);
    }
}
