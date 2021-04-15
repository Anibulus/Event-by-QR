package com.example.eventwqr.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eventwqr.Modelo.*;

import java.io.File;
import java.security.cert.CRLReason;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class BD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD="BD_Evento.bd";
    private static final int VERSION_BD=2;
    private static final String CREATE_MESA=
            "CREATE TABLE MESA (IDMESA INTEGER, CANTIDADTOTAL INT, PRIMARY KEY (IDMESA))";
    private static final String CREATE_GRUPO=
            "CREATE TABLE GRUPO (IDGRUPO INTEGER, NOMBRE TEXT, PRIMARY KEY (IDGRUPO))";
    private static final String CREATE_INVITACION=
            "CREATE TABLE INVITACION (CODIGO INTEGER NOT NULL ,MESA INT NOT NULL,CANTIDAD INT,CANTIDADTOTAL INT, FOREIGN KEY(CODIGO) REFERENCES GRUPO(IDGRUPO), FOREIGN KEY(MESA) REFERENCES MESA(IDMESA), PRIMARY KEY(CODIGO, MESA))";
    private static final String INSERT_MESA="INSERT INTO MESA (IDMESA, CANTIDADTOTAL) VALUES (1,25),(2,20),(3,30),(4,25);";
    private static final String INSERT_GRUPO="INSERT INTO GRUPO(IDGRUPO, NOMBRE) VALUES (1,'PREZA'),(2,'PADILLA'),(3,'UTJ'),(4,'PREPA 5');";
    private static final String INSERT_INVITACION="INSERT INTO INVITACION (CODIGO, MESA, CANTIDAD, CANTIDADTOTAL) VALUES (1,1,0,5),(2,2,1,10),(3,3,5,7),(4,4,2,10);";


    public BD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MESA);
        db.execSQL(CREATE_GRUPO);
        db.execSQL(CREATE_INVITACION);
        db.execSQL(INSERT_MESA);
        db.execSQL(INSERT_GRUPO);
        db.execSQL(INSERT_INVITACION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<=newVersion){
            db.execSQL("DROP TABLE INVITACION");
            db.execSQL("DROP TABLE GRUPO");
            db.execSQL("DROP TABLE MESA");

            db.execSQL(CREATE_MESA);
            db.execSQL(CREATE_GRUPO);
            db.execSQL(CREATE_INVITACION);
            db.execSQL(INSERT_MESA);
            db.execSQL(INSERT_GRUPO);
            db.execSQL(INSERT_INVITACION);
        }
    }

    //Cuantos pueden entrar con el QR y cuantos realmente entraron
    public void agregarInvitados(int codigo, int mesa, int cantidad, int asisten){
        SQLiteDatabase conexion=this.getWritableDatabase();
        if(conexion!=null){
            Cursor res=conexion.rawQuery("SELECT * FROM INVITACION WHERE CODIGO="+codigo+" and MESA="+mesa,null);
            System.out.println(res!=null);
            System.out.println("--------------------");
            res.moveToFirst();
            System.out.println(res.getInt(res.getColumnIndex("CODIGO")));
            if(res!=null)
            {
                System.out.println("---------------");
                //TODO redireccionar a que modifiquen el registro
            }
            else
            {
                try {
                    System.out.println("-------------------------------");
                    conexion.execSQL("INSERT INTO INVITACION VALUES ('"+codigo+"','"+mesa+"','"+cantidad+"','"+asisten+"')");
                }catch(Exception e){
                    System.out.printf("---------Error aqui"+e.getMessage());
                }

            }
            /*while(res.isAfterLast() == false) {
                res.getString(res.getColumnIndex("name"));
                res.moveToNext();
            }*/


            conexion.close();
        }//
    }//Fin de agregar invitados

    public List<Invitacion> getListadoInvitados(){
        SQLiteDatabase conexion = this.getReadableDatabase();
        Cursor res = conexion.rawQuery( "select MESA, CODIGO, MESA.CANTIDADTOTAL, GRUPO.NOMBRE, INVITACION.CANTIDAD, INVITACION.CANTIDADTOTAL AS INVITADOS from INVITACION INNER JOIN MESA ON INVITACION.MESA=MESA.IDMESA INNER JOIN GRUPO ON INVITACION.CODIGO=GRUPO.IDGRUPO", null );
        List<Invitacion> list = new ArrayList<>();

        res.moveToFirst();
        while(res.isAfterLast() == false) {
            //Llena de informacion nos objetos y los inserta en el array
            Mesa m=new Mesa(res.getInt(res.getColumnIndex("MESA")),res.getInt(res.getColumnIndex("CANTIDADTOTAL")));
            Grupo g=new Grupo(res.getInt(res.getColumnIndex("CODIGO")),res.getString(res.getColumnIndex("NOMBRE")));
            Invitacion i=new Invitacion(g,m,res.getInt(res.getColumnIndex("INVITADOS")), res.getInt(res.getColumnIndex("CANTIDAD")));
            list.add(i);
            res.moveToNext();
        }
        return list;
    }
}//Fin de la clase
