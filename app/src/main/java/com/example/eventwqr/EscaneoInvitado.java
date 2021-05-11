package com.example.eventwqr;

import android.content.Intent;
import android.os.Bundle;

import com.example.eventwqr.DAO.BD;
import com.example.eventwqr.Modelo.CamaraQR;
import com.example.eventwqr.Modelo.Invitacion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class EscaneoInvitado extends AppCompatActivity {

    TextView invitados, grupo, mesa, arrivaron,ID;
    EditText asisten;
    Button btnGuardar;
    int inv,asis, cod;//Personas invitadas, que ya asisitieron

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaneo_invitado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ID=findViewById(R.id.txtID);
        invitados=findViewById(R.id.txtNumeroInvitados);
        grupo=findViewById(R.id.txtGrupo);
        mesa=findViewById(R.id.txtMesa);
        asisten=findViewById(R.id.nbAsisten);
        arrivaron=findViewById(R.id.txtHanAsistido);
        btnGuardar=findViewById(R.id.btnGuardarEscaneo);
        lectorQR();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(parseInt(asisten.getText().toString())>0)
                {
                    if(parseInt(asisten.getText().toString())+asis <= inv)//Si los que registra màs los que estaban son los que estaban invitados
                    {
                        MainActivity.conexion.agregarInvitados(cod,parseInt(asisten.getText().toString())+asis);
                        MainActivity.actualizar();
                        Toast.makeText(getApplicationContext(), "Se ha guardado con éxito", Toast.LENGTH_SHORT).show();
                        finish();
                    }else
                    {
                        Toast.makeText(getApplicationContext(), "El número excede la cantidad de invitados", Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(getApplicationContext(), "Debe dar un número mayor a 0", Toast.LENGTH_SHORT).show();
            }
        });
    }//Fin de onCreate

    //Escaneo QR
    protected void lectorQR()
    {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Escanea el código QR de la invitación");
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.setCaptureActivity(CamaraQR.class);
        integrator.initiateScan();
    }


    //Cuando se lee el codigo QR
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Llamar a la informacion capturada
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String datos=result.getContents();

        //Cuando datos es vacio se regresa, sino continua
        if(datos!=null){
            if(validarQR(datos))
            {
                int cantidad=parseInt(datos.substring(22,24));//Cuantos pueden entrar dacado del QR
                cod=parseInt(datos.substring(24,26));
                Invitacion i=MainActivity.conexion.getInvitado(cod);
                if(i!=null)
                {
                    if(i.getAsisten()<i.getCantidadInvitados()) {
                        ID.setText("ID: " + i.getFamilia().getIdGrupo());
                        grupo.setText(i.getFamilia().getNombre());
                        invitados.setText(i.getCantidadInvitados() + " Invitados");
                        mesa.setText("Mesa " + i.getMesa().getIdMesa());
                        inv = i.getCantidadInvitados();
                        asis = i.getAsisten();
                        arrivaron.setText(asis>0?"Han asistido "+asis:"");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Este código ya no permite más invitados", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "No se encuentra en la Base de Datos", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            else
                {
                Toast.makeText(getApplicationContext(), "El QR escaneado no es válido", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        else
            finish();


    }//Fin de on result

    private Boolean validarQR(String cadena){
        Boolean veridico=true;
        if(cadena.length()==28)
        {
            if(!cadena.contains("INVBLUNNIEBODA"))
                veridico=false;
        }
        else
            veridico=false;
        return veridico;
    }//Fin de validacion de QR

    //No le permite regresar hasta terminar
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Debe completar el registro", Toast.LENGTH_SHORT).show();
    }
}//Fin de la clase
