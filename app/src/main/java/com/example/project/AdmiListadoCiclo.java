package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project.dao.CicloDao;
import com.example.project.entidad.CicloEntity;
import com.example.project.entidad.EdificioEntity;

import java.text.ParseException;
import java.util.ArrayList;

public class AdmiListadoCiclo extends AppCompatActivity {

    CicloDao cicloDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admi_listado_ciclo );

        cicloDao = new CicloDao(getApplicationContext());
        ArrayList<String> listadoEncabezado = new ArrayList<>();

        try {
            for (CicloEntity ciclo : cicloDao.getList()){
                listadoEncabezado.add(ciclo.getCodigo());
                Log.d("TEST", "Ciclo print");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final ArrayAdapter<String> data = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listadoEncabezado);

        ListView listView = (ListView) findViewById(R.id.Ciclo);
        listView.setAdapter(data);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nombciclo = data.getItem(i);
                Intent historial_detalle = new Intent(getApplicationContext(), AdmiModificarCiclo.class);
                historial_detalle.putExtra("nombciclo",nombciclo);
                startActivity(historial_detalle);
            }
        });
    }
}
