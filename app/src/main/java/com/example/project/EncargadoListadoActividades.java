package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.dao.ActividadDao;
import com.example.project.entidad.ActividadEntity;

import java.util.ArrayList;

public class EncargadoListadoActividades extends AppCompatActivity {
    ActividadDao actividades;
    ListView listView;
    ArrayList<ActividadEntity> actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.encargado_listado_actividades );
        setTitle("Actividades");

        listView = findViewById(R.id.Actividades);
        actividades = new ActividadDao( getApplicationContext() );
        actividad = actividades.getList();

        final ArrayList<String> listadoActividades = new ArrayList<>();
        for (ActividadEntity act : actividad){
            listadoActividades.add(act.getActividad());
        }

        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listadoActividades));

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<ActividadEntity> actividades = new ArrayList<>();
                actividades.add(actividad.get( position ));
                Intent acty = new Intent(getApplicationContext(), EncargadoModificarActividades.class);
                acty.putExtra("actividad", actividades);
                startActivityForResult( acty, 2 );
            }
        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if (actividad != null || actividad.size() != 0) actividad.clear();
            actividad = actividades.getList();
            ArrayList<String> listadoEncabezado = new ArrayList<>();
            for (ActividadEntity acti : actividad){
                listadoEncabezado.add(acti.getActividad());
            }
            listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listadoEncabezado));
            Toast.makeText(getApplicationContext(), "Modificación realizada con exito.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Modificación Cancelada", Toast.LENGTH_LONG).show();
        }
    }
}
