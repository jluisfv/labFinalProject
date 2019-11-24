package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;


public class EncargadoAprobarActividades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encargado_activity_aprobar_actividades);

        String[] datos = new String[3];

        datos[0] = ("Instructor: Juan Carlos \nHorario: Lun-Vi 8:00am-12:00pm\nActividad realizada: Conteo");
        datos[1] = ("Instructor: Mario Benitez \nHorario: Lun-Vi 9:30am-12:00pm\nActividad realizada: Actualizacion de sw");
        datos[2] = ("Instructor: Karen Solis \nHorario: Lun-Mi 9:30am-11:00am\nActividad realizada: Horas Extras");

        ArrayCustomAdapter ac = new ArrayCustomAdapter(this,datos);

        ListView listView = (ListView) findViewById(R.id.listadoSolicitudes);
        listView.setAdapter(ac);
    }
}
