package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.dao.CicloDao;
import com.example.project.dao.UsuarioDao;
import com.example.project.entidad.CicloEntity;
import com.example.project.entidad.UsuarioEntity;

import java.util.ArrayList;

public class EncargadoHistorialDetalleInstructor extends AppCompatActivity {
    Spinner spinner9,spinner10;
    Button btnRegresar, btnGenerar, btnFiltro,btnFiltro2;
    TextView instructores;
    ArrayList<CicloEntity> ciclos;
    CicloDao cicloDao;
    UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encargado_activity_historial_instructor_detalle);

        btnGenerar = findViewById( R.id.btnGenerar );
        btnRegresar = findViewById( R.id.btnRegresar );
        btnFiltro = findViewById( R.id.btnFiltro );
        btnFiltro2 = findViewById( R.id.btnFiltro2 );
        spinner9 = findViewById( R.id.spinner9 );
        spinner10 = findViewById( R.id.spinner10 );
        instructores = findViewById( R.id.textView3 );

        Bundle bundle = getIntent().getExtras();
        final ArrayList<UsuarioEntity> instructor= (ArrayList<UsuarioEntity>) bundle.getSerializable("instructor");
        instructores.setText("Instructor " +  instructor.get(0).getNombre() + " " + instructor.get( 0 ).getApellido());

        cicloDao = new CicloDao(getApplicationContext());
        usuarioDao = new UsuarioDao(getApplicationContext());
        ciclos = cicloDao.getList();

        btnRegresar.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent( getApplicationContext(), EncargadoHistorialInstructor.class );
                startActivity( regresar );

            }
        } );

        btnGenerar.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {

            }
        } );

    }
}
