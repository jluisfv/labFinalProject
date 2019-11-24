package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project.dao.EdificioDao;
import com.example.project.dao.LaboratorioDao;
import com.example.project.entidad.EdificioEntity;

import java.util.ArrayList;

public class AdmiListadoEdificio extends AppCompatActivity {

    EdificioDao edificioDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admi_listado_edificio );
        setTitle("Edificios");
        edificioDao = new EdificioDao(getApplicationContext());
        final ArrayList<String> listadoEncabezado = new ArrayList<>();

        for (EdificioEntity edificio : edificioDao.getList()){
            listadoEncabezado.add(edificio.getNombre());
        }

        final ArrayAdapter<String> data = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, listadoEncabezado);

        ListView listView = findViewById(R.id.Edificio);
        listView.setAdapter(data);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nomedif = data.getItem(i);
                Intent historial_detalle = new Intent(getApplicationContext(), AdmiModificarEdificio.class);
                historial_detalle.putExtra("nomedif",nomedif);
                startActivity(historial_detalle);
            }
        });
    }
}
