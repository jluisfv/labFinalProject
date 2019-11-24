package com.example.project;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.dao.UsuarioDao;
import com.example.project.entidad.UsuarioEntity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdmiListadoEncargados extends AppCompatActivity {
    UsuarioDao usuarioDao;
    ListView listView;
    ArrayList<UsuarioEntity> encargados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_listado_encargados);
        setTitle("Encargados");
        listView = findViewById(R.id.ListaEncargados);
        usuarioDao = new UsuarioDao(getApplication());

        encargados = usuarioDao.list("ENCARGADO");
        ArrayList<String> itemLista = new ArrayList<>();
        for(UsuarioEntity usuario:encargados){
            itemLista.add(usuario.getNombre());
        }
        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, itemLista));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), AdmiModificarEncargado.class);
                ArrayList<UsuarioEntity> usuarioSeleccionado = new ArrayList<>();
                usuarioSeleccionado.add(encargados.get(i));
                intent.putExtra("encargado", usuarioSeleccionado);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            encargados.clear();
            encargados = usuarioDao.list("ENCARGADO");
            ArrayList<String> itemLista = new ArrayList<>();
            for(UsuarioEntity usuario:encargados){
                itemLista.add(usuario.getNombre());
            }
            listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, itemLista));
            Toast.makeText(getApplicationContext(), "ENCARGADO MODIFICADO CON EXITO", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "MODIFICACION CANCELADA", Toast.LENGTH_LONG).show();
        }

    }

}
