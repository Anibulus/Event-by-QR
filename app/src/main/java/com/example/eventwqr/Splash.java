package com.example.eventwqr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TimerTask tarea=new TimerTask() {
            @Override
            public void run() {
                Intent i=new Intent(Splash.this,MainActivity.class);
                startActivity(i);
                finish();
            }//Fin de run
        };

        Timer t=new Timer();
        t.schedule(tarea,1500);//Espera 2 segundos
    }//Fin de onCreate
}//Fin de clase
