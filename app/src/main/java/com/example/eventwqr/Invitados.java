package com.example.eventwqr;

import android.os.Bundle;

import com.example.eventwqr.Modelo.Invitacion;
import com.example.eventwqr.Modelo.InvitacionAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.List;

public class Invitados extends AppCompatActivity {

    RecyclerView rv;
    InvitacionAdapter ia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitados);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv=(RecyclerView)findViewById(R.id.RVInitados);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ia=new InvitacionAdapter(MainActivity.conexion.getListadoInvitados());
        rv.setAdapter(ia);

        Toast.makeText(getApplicationContext(), "Hay un total de "+ia.getItemCount() +" registros", Toast.LENGTH_SHORT).show();

    }

}
