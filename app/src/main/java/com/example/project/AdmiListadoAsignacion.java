package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.dao.AsignacionEncargadoDao;
import com.example.project.dao.CicloDao;
import com.example.project.dao.DetAsigEncargadoDao;
import com.example.project.dao.LaboratorioDao;
import com.example.project.dao.UsuarioDao;
import com.example.project.entidad.AsignacionEncargadoEntity;
import com.example.project.entidad.DetAsigEncargadoEntity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdmiListadoAsignacion extends AppCompatActivity {

    ListView listView;
    LaboratorioDao laboratorioDao;
    UsuarioDao usuarioDao;
    AsignacionEncargadoDao asignacionDao;
    DetAsigEncargadoDao detalleDao;
    CicloDao cicloDao;
    ArrayList<AsignacionEncargadoEntity> asignaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_listado_asignacion);
        setTitle("Asignaciones");
        asignacionDao = new AsignacionEncargadoDao(getApplicationContext());
        usuarioDao = new UsuarioDao(getApplicationContext());
        laboratorioDao = new LaboratorioDao(getApplicationContext());
        cicloDao = new CicloDao(getApplicationContext());
        detalleDao = new DetAsigEncargadoDao(getApplicationContext());
        asignaciones = asignacionDao.getList();

        ArrayList<String> encabezadoAsig = new ArrayList<>();
        for(AsignacionEncargadoEntity asig:asignaciones){
            StringBuilder sb = new StringBuilder();

            sb.append("Encargado: "+ usuarioDao.findById(asig.getIdEmpleado()).getNombre() + "\n");
            sb.append("Laboratorio: "+ laboratorioDao.findById(asig.getIdLab()).getNombre() + "\n");
            sb.append("Ciclo: "+ cicloDao.findById(asig.getIdCiclo()).getCodigo() + "\n");
            String dias = "";
            String fecha = "";
            for(DetAsigEncargadoEntity det : detalleDao.findByIdAsig(asig.getId())){
                dias += (det.getDia()==1)? "Lunes ": "";
                dias += (det.getDia()==2)? "Martes ": "";
                dias += (det.getDia()==3)? "Miercoles ": "";
                dias += (det.getDia()==4)? "Jueves ": "";
                dias += (det.getDia()==5)? "Viernes ": "";
                dias += (det.getDia()==6)? "Sabado ": "";
                dias += (det.getDia()==7)? "Domingo ": "";
                fecha = "De: "+ det.getHoraInicio() +" hasta "+ det.getHoraFin();
            }
            sb.append("Dias: "+ dias + "\n");
            sb.append("Horario: "+ fecha+ "\n");
            encabezadoAsig.add(sb.toString());
        }
        listView = findViewById(R.id.Asignacion);
        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, encabezadoAsig));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent modificar = new Intent(getApplicationContext(), AdmiModificarAsignacion.class);
                modificar.putExtra("asignacion", asignaciones.get(i).getId());
                startActivityForResult(modificar, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(asignaciones != null || asignaciones.size() != 0) asignaciones.clear();
            asignaciones = asignacionDao.getList();

            ArrayList<String> encabezadoAsig = new ArrayList<>();
            for(AsignacionEncargadoEntity asig:asignaciones){
                StringBuilder sb = new StringBuilder();

                sb.append("Encargado: "+ usuarioDao.findById(asig.getIdEmpleado()).getNombre() + "\n");
                sb.append("Laboratorio: "+ laboratorioDao.findById(asig.getIdLab()).getNombre() + "\n");
                sb.append("Ciclo: "+ cicloDao.findById(asig.getIdCiclo()).getCodigo() + "\n");
                String dias = "";
                String fecha = "";
                for(DetAsigEncargadoEntity det : detalleDao.findByIdAsig(asig.getId())){
                    dias += (det.getDia()==1)? "Lunes ": "";
                    dias += (det.getDia()==2)? "Martes ": "";
                    dias += (det.getDia()==3)? "Miercoles ": "";
                    dias += (det.getDia()==4)? "Jueves ": "";
                    dias += (det.getDia()==5)? "Viernes ": "";
                    dias += (det.getDia()==6)? "Sabado ": "";
                    dias += (det.getDia()==7)? "Domingo ": "";
                    fecha = "De: "+ det.getHoraInicio() +" hasta "+ det.getHoraFin();
                }
                sb.append("Dias: "+ dias + "\n");
                sb.append("Horario: "+ fecha+ "\n");
                encabezadoAsig.add(sb.toString());
            }
            listView = findViewById(R.id.Asignacion);
            listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, encabezadoAsig));
            Toast.makeText(getApplicationContext(), "Modificado con exito", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Modificación cancelada", Toast.LENGTH_LONG).show();
        }

    }
}
