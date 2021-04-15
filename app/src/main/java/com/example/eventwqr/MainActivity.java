package com.example.eventwqr;

import android.content.Intent;
import android.os.Bundle;

import com.example.eventwqr.DAO.BD;
import com.example.eventwqr.Modelo.Invitacion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtQR;
    TextView txtFecha;
    TextView txtCodigo;
    TextView txtMesa;
    TextView txtEvento;
    TextView txtCantidad;

    FloatingActionButton fab;
    FloatingActionButton fabInvitados;

    static BD conexion= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        conexion=new BD(getApplicationContext());
        txtQR=findViewById(R.id.txtQR);
        txtCodigo=findViewById(R.id.txtID);
        txtEvento=findViewById(R.id.txtEvento);
        txtFecha=findViewById(R.id.txtFecha);
        txtCantidad=findViewById(R.id.txtCantidad);
        txtMesa=findViewById(R.id.txtMesa);
        fab = findViewById(R.id.fab);
        fabInvitados= findViewById(R.id.faInvitados);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lectorQR();
            }
        });
        fabInvitados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent invitados=new Intent(getApplicationContext(),Invitados.class);
                startActivity(invitados);
            }
        });
    }//Fin on create


    //Cuando se lee el codigo QR
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Llamar a la informacion capturada
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String datos=result.getContents();

        //Se muestran los datos del qr en el Activity
        txtQR.setText(datos);
        txtFecha.setText("Fecha: "+(datos.substring(3,5)+"/"+datos.substring(5,7)+"/"+datos.substring(7,11)));
        txtEvento.setText("Evento: "+datos.substring(11,21));
        txtCantidad.setText("Cantidad: "+datos.substring(21,23));
        txtCodigo.setText("Código: "+datos.substring(24,25));
        //TODO mesa

        //Manda a guardar la informacion
        conexion.agregarInvitados(01,02,03,02);
        Toast.makeText(getApplicationContext(), "qSe ha guardado con exito", Toast.LENGTH_SHORT).show();
    }//Fin de on result

    //Escaneo QR
    protected void lectorQR()
    {
        new IntentIntegrator(this).initiateScan();
    }

    //Metodos default
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}//Fin de la clase
