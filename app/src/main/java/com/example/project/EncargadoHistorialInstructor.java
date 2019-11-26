package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project.dao.UsuarioDao;
import com.example.project.entidad.UsuarioEntity;

import java.util.ArrayList;

public class EncargadoHistorialInstructor extends AppCompatActivity {
    UsuarioDao usuarioDao;
    ListView listView;
    ArrayList<UsuarioEntity> instructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encargado_activity_historial_instructor);

        setTitle("Historial del instructor");
        listView = findViewById(R.id.listadoHistorialInstructores);
        usuarioDao = new UsuarioDao(getApplication());

        instructor = usuarioDao.list("INSTRUCTOR");


        final ArrayList<String> instructores = new ArrayList<>();
        for(UsuarioEntity usuario:instructor){
            instructores.add("Instructor: " + usuario.getNombre() + " " + usuario.getApellido() + "\n" +
                    "Email: " + usuario.getEmail());
        }
        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, instructores));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), EncargadoHistorialDetalleInstructor.class);
                ArrayList<UsuarioEntity> usuarioSeleccionado = new ArrayList<>();
                usuarioSeleccionado.add(instructor.get(i));
                intent.putExtra("instructor", usuarioSeleccionado);
                startActivityForResult(intent, 1);
            }
        });

    }
}
