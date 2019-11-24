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

import com.example.project.dao.EdificioDao;
import com.example.project.entidad.EdificioEntity;

import java.util.ArrayList;

public class AdmiListadoEdificio extends AppCompatActivity {

    ListView listView;
    EdificioDao edificioDao;
    ArrayList<EdificioEntity> edificios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.admi_activity_listado_edificio);
        listView = findViewById(R.id.Edificio);
        setTitle("Edificios");
        edificioDao = new EdificioDao(getApplicationContext());

        edificios = edificioDao.getList();
        final ArrayList<String> listadoEncabezado = new ArrayList<>();

        for (EdificioEntity edificio : edificios){
            listadoEncabezado.add(edificio.getNombre());
        }
        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, listadoEncabezado));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), AdmiModificarEdificio.class);
                ArrayList<EdificioEntity> edificio = new ArrayList<>();
                edificio.add(edificios.get(i));
                intent.putExtra("edificio", edificio);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if (edificios != null || edificios.size() != 0 ) edificios.clear();
            edificios = edificioDao.getList();
            final ArrayList<String> listadoEncabezado = new ArrayList<>();
            for (EdificioEntity edificio : edificios){
                listadoEncabezado.add(edificio.getNombre());
            }
            listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, listadoEncabezado));

            Toast.makeText(getApplicationContext(), "Modificación realizada con exito.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Modificación Cancelada", Toast.LENGTH_LONG).show();
        }

    }
}
