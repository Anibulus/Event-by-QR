package com.example.eventwqr;

import android.content.Intent;
import android.os.Bundle;

import com.example.eventwqr.DAO.BD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView totalAsisten, totalInvitados;

    Button fab, fabInvitados;

    static BD conexion= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        conexion=new BD(getApplicationContext());

        totalAsisten=findViewById(R.id.txtTotalAsisten);
        totalInvitados=findViewById(R.id.txtTotalnvitados);

        fab = findViewById(R.id.fab);
        fabInvitados= findViewById(R.id.faInvitados);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent escanear=new Intent(getApplicationContext(),EscaneoInvitado.class);
                startActivity(escanear);
            }
        });
        fabInvitados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent invitados=new Intent(getApplicationContext(),Invitados.class);
                startActivity(invitados);
            }
        });
        actualizar();
    }//Fin on create


    public static void actualizar(){
        totalAsisten.setText(String.valueOf(conexion.getTotalAsistidos()));
        totalInvitados.setText(String.valueOf(conexion.getTotalInvitados()));
    }

}//Fin de la clase
