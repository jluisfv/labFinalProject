package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.dao.UsuarioDao;
import com.example.project.entidad.UsuarioEntity;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    SQLiteDatabase bd;
    Button btnLogin;
    EditText edtUser;
    EditText edtPass;

    UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnGuardar );
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);

        usuarioDao = new UsuarioDao(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate(edtUser)) {
                   if(validate(edtPass)) {
                       logearse(edtUser.getText().toString(), edtPass.getText().toString());
                   }
                }

            }
        });
    }

    void logearse(String user, String pass){
        UsuarioEntity usuarioEntity = usuarioDao.findAcceso(user, pass);
        if (usuarioEntity != null){

            SharedPreferences mPrefs = getSharedPreferences("Global", Context.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            mPrefs.edit().clear().apply();
            prefsEditor.putInt("idUsuario", usuarioEntity.getId());
            prefsEditor.putString("nombreUsuario", usuarioEntity.getNombre());
            prefsEditor.putString("apellidoUsuario", usuarioEntity.getApellido());
            prefsEditor.putString("tipoUsuario", usuarioEntity.getTipo());
            prefsEditor.apply();
            String hola = mPrefs.getString("nombreUsuario","Hola");
            if(usuarioEntity.getTipo().equalsIgnoreCase("ADMINISTRADOR")){
                Intent intent = new Intent(getApplicationContext(), AdmiMainActivity.class);
                startActivity(intent);
            }else if(usuarioEntity.getTipo().equalsIgnoreCase("ENCARGADO")){
                Intent intent = new Intent(getApplicationContext(), EncargadoMainActivity.class);
                startActivity(intent);
            }else if(usuarioEntity.getTipo().equalsIgnoreCase("INSTRUCTOR")){
                Intent intent = new Intent(getApplicationContext(), InstructorMainActivity.class);
                startActivity(intent);
            }

        }else{
            Toast.makeText(getApplicationContext(), "CREDENCIALES INVALIDAS", Toast.LENGTH_LONG).show();
        }
    }

    boolean validate(EditText edt){
        boolean result = true;
        if (TextUtils.isEmpty(edt.getText())){
            result = false;
            edt.setError("Campo Requerido");
            edt.requestFocus();
        }
        return result;
    }
}
