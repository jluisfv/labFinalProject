package com.example.project;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdmiAsignarLab extends AppCompatActivity {
    Button btnGuardar, btnCancelar;
    EditText edlabs,edencargad,edentrada,edsalida;
    SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admi_activity_asignar_lab);
        edlabs = findViewById(R.id.editText9);
        edencargad = findViewById(R.id.editText10);
        edentrada = findViewById(R.id.editText12);
        edsalida = findViewById(R.id.editText13);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);

        //BD objbase = new BD(getApplicationContext(),"DB",null,1);
        //bd = objbase.getWritableDatabase();

       btnGuardar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String lab = edlabs.getText().toString();
               String encar = edencargad.getText().toString();
               String entrada = edentrada.getText().toString();
               String salida = edsalida.getText().toString();

               if(!lab.equals("")){
                   if(!encar.equals("")){
                       if (!entrada.equals("")){
                           if (!salida.equals("")){
                               String consulta = "select * from laboratorios where nombrelab = '"+lab+"'";
                              Cursor c = bd.rawQuery(consulta,null);
                               if (c.moveToFirst()){
                                   consulta = "select * from users where nombreuser = '"+encar+"'";
                                   c = bd.rawQuery(consulta,null);
                                   if(c.moveToFirst()){
                                       String tipo = c.getString(0);
                                       if(tipo.equals("encagado")){
                                            consulta = "select * from laboratorios where nombrelab = '"+lab+"'";
                                            c = bd.rawQuery(consulta,null);
                                            if(c.moveToFirst()){
                                                consulta = "insert into encarlabs(nombrelab, nombreeencar, horaentrada, hoasalida) values('"+lab+"','"+encar+"','"+entrada+"','"+salida+"')";
                                                Toast.makeText(getApplicationContext(), "se asigno el encargado", Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(getApplicationContext(), "no existe el laboratorio", Toast.LENGTH_SHORT).show();
                                            }
                                       } else{
                                           Toast.makeText(getApplicationContext(), "usuario no posee el rol", Toast.LENGTH_SHORT).show();
                                       }
                                   }else{
                                       Toast.makeText(getApplicationContext(), "no se enconto el usuario", Toast.LENGTH_SHORT).show();
                                   }

                               }else {
                                   Toast.makeText(getApplicationContext(), "no existe laboratorio", Toast.LENGTH_SHORT).show();
                               }
                           }else {
                               Toast.makeText(getApplicationContext(), "digite hora de salida", Toast.LENGTH_SHORT).show();
                           }
                       }else{
                           Toast.makeText(getApplicationContext(), "digite hora de entrada", Toast.LENGTH_SHORT).show();
                       }
                   }else {
                       Toast.makeText(getApplicationContext(), "digite nombre de encargado", Toast.LENGTH_SHORT).show();
                   }
               }else{
                   Toast.makeText(getApplicationContext(), "digite un laboratorio ", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
}
