package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.dao.CicloDao;
import com.example.project.entidad.CicloEntity;

import java.text.ParseException;
import java.util.ArrayList;

public class AdmiListadoCiclo extends AppCompatActivity {

    CicloDao cicloDao;
    ListView listView;
    ArrayList<CicloEntity> ciclos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.admi_activity_listado_ciclo);
        setTitle("Listado de Ciclos");
        listView = findViewById(R.id.Ciclo);

        cicloDao = new CicloDao(getApplicationContext());
        ciclos = cicloDao.getList();
        ArrayList<String> listadoEncabezado = new ArrayList<>();
        for (CicloEntity ciclo : ciclos){
            listadoEncabezado.add(ciclo.getCodigo());
        }

        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listadoEncabezado));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<CicloEntity> ciclo = new ArrayList<>();
                ciclo.add(ciclos.get(i));
                Intent intent = new Intent(getApplicationContext(), AdmiModificarCiclo.class);
                intent.putExtra("ciclo", ciclo);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if (ciclos != null || ciclos.size() != 0) ciclos.clear();
            ciclos = cicloDao.getList();
            ArrayList<String> listadoEncabezado = new ArrayList<>();
            for (CicloEntity ciclo : ciclos){
                listadoEncabezado.add(ciclo.getCodigo());
            }
            listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listadoEncabezado));
            Toast.makeText(getApplicationContext(), "Modificación realizada con exito.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Modificación Cancelada", Toast.LENGTH_LONG).show();
        }
    }
}
