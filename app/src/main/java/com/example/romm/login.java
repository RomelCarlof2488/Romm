package com.example.romm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et1 = (EditText) findViewById(R.id.username);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        Button loginbtn = (Button) findViewById(R.id.loginbtn);

        // si admin es admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    //si es correcto
                    //Toast.makeText( MainActivity.this, "Acceso correcto",Toast.LENGTH_SHORT).show();
                    String nombre = et1.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class)
                            .putExtra("dato",nombre);//MainActivity.this
                    startActivity(intent);
                    username.getText().toString();

                }
                else{
                    Toast.makeText( login.this, "Usuario o contraseña incorrecto",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}