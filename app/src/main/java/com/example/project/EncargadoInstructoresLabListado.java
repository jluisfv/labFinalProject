package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.dao.UsuarioDao;
import com.example.project.entidad.UsuarioEntity;

import java.util.ArrayList;

public class EncargadoInstructoresLabListado extends AppCompatActivity {

    UsuarioDao usuarioDao;
    ListView listView;
    ArrayList<UsuarioEntity> usuarioEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encargado_activity_instructores_lab_listado);

        setTitle("Instructores");
        listView = (ListView) findViewById(R.id.instructoresPorLab);
        usuarioDao = new UsuarioDao(getApplicationContext());
        usuarioEntities = usuarioDao.listPorLaboratorio("0");

        ArrayList<String> historial = new ArrayList<>();

       for(UsuarioEntity user : usuarioEntities)
       {
           historial.add(user.getNombre() + " "+ user.getApellido());
       }


        ArrayAdapter<String> data = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,historial);


        listView.setAdapter(data);
    }
}
