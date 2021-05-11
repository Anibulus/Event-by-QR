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
    private static final int VERSION_BD=1;
    private static final String CREATE_MESA=
            "CREATE TABLE MESA (IDMESA INTEGER, CANTIDADTOTAL INT, PRIMARY KEY (IDMESA))";
    private static final String CREATE_GRUPO=
            "CREATE TABLE GRUPO (IDGRUPO INTEGER, NOMBRE TEXT, PRIMARY KEY (IDGRUPO))";
    private static final String CREATE_INVITACION=
            "CREATE TABLE INVITACION (CODIGO INTEGER NOT NULL ,MESA INT NOT NULL,CANTIDAD INT,CANTIDADTOTAL INT, FOREIGN KEY(CODIGO) REFERENCES GRUPO(IDGRUPO), FOREIGN KEY(MESA) REFERENCES MESA(IDMESA), PRIMARY KEY(CODIGO, MESA))";
    private static final String INSERT_MESA="INSERT INTO MESA (CANTIDADTOTAL) VALUES (20),(20),(30),(25),(20),(20),(20),(25),(20),(20),(20),(25),(20),(20),(20),(25),(20),(20),(20),(20);";

    private static final String INSERT_GRUPO="INSERT INTO GRUPO(NOMBRE) VALUES \n" +
            "('Familia Barrón Palacios'),\n" +
            "('Familia Saenz Chavarria'),\n" +
            "('Familia Moreno Vega'),\n" +
            "('Familia Palacios Reynoso'),\n" +
            "('Familia Montes de Oca Palacios'),\n" +
            "('Familia Ruiz Palacios'),\n" +
            "('Familia Enriquez Reynoso'),\n" +
            "('Familia Margarito Reynoso'),\n" +
            "('Familia Pimpo Reynoso'),\n" +
            "('Familia Nito López'),\n" +
            "('Lupita G'),\n" +
            "('Tía Martha'),\n" +
            "('Papás Fer'),\n" +
            "('Familia Preza Padilla'),\n" +
            "('Familia Preza Hernandez'),\n" +
            "('Familia Preza Martínez'),\n" +
            "('Familia Ávila Preza'),\n" +
            "('Familia Preza Javier'),\n" +
            "('Martha Padilla'),\n" +
            "('Yolanda Padilla'),\n" +
            "('Blanca Padilla'),\n" +
            "('Luis Padilla'),\n" +
            "('Jaime Padilla'),\n" +
            "('Soledad Padilla'),\n" +
            "('Elena Padilla'),\n" +
            "('Saul Padilla'),\n" +
            "('Liz Preza'),\n" +
            "('Gerardo Preza'),\n" +
            "('Paulina Lopez'),\n" +
            "('Luis Gonzalez'),\n" +
            "('Hugo Jefferson'),\n" +
            "('Ale Luna'),\n" +
            "('Kore'),\n" +
            "('Rodo'),\n" +
            "('Bombero'),\n" +
            "('Marita'),\n" +
            "('Alma'),\n" +
            "('Marco A'),\n" +
            "('Marco M'),\n" +
            "('Angel'),\n" +
            "('Christian Bebé'),\n" +
            "('Esme'),\n" +
            "('Compita'),\n" +
            "('Edgar'),\n" +
            "('Gus'),\n" +
            "('Daniel'),\n" +
            "('Valeria'),\n" +
            "('Neko'),\n" +
            "('Mitzi'),\n" +
            "('Merelyn'),\n" +
            "('Karen'),\n" +
            "('Ale Jefa'),\n" +
            "('Rodri'),\n" +
            "('Richard'),\n" +
            "('Juan Jose');";
    private static final String INSERT_INVITACION="INSERT INTO INVITACION (CODIGO, MESA, CANTIDAD, CANTIDADTOTAL) VALUES \n" +
            "(1,10,0,5),\n" +
            "(2,12,0,4),\n" +
            "(3,13,0,6),\n" +
            "(4,14,0,3),\n" +
            "(5,15,0,4),\n" +
            "(6,16,0,6),\n" +
            "(7,17,0,4),\n" +
            "(8,18,0,2),\n" +
            "(9,19,0,2),\n" +
            "(10,20,0,7),\n" +
            "(11,2,0,1),\n" +
            "(12,3,0,1),\n" +
            "(13,1,0,12),\n" +
            "(14,4,0,6),\n" +
            "(15,5,0,1),\n" +
            "(16,6,0,4),\n" +
            "(17,7,0,4),\n" +
            "(18,8,0,3),\n" +
            "(19,9,0,2),\n" +
            "(20,9,0,2),\n" +
            "(21,9,0,2),\n" +
            "(22,8,0,2),\n" +
            "(23,10,0,2),\n" +
            "(24,10,0,2),\n" +
            "(25,13,0,2),\n" +
            "(26,15,0,2),\n" +
            "(27,1,0,3),\n" +
            "(28,1,0,2),\n" +
            "(29,1,0,1),\n" +
            "(30,1,0,1),\n" +
            "(31,1,0,1),\n" +
            "(32,1,0,1),\n" +
            "(33,1,0,1),\n" +
            "(34,1,0,2),\n" +
            "(35,1,0,2),\n" +
            "(36,1,0,1),\n" +
            "(37,1,0,2),\n" +
            "(38,1,0,2),\n" +
            "(39,1,0,2),\n" +
            "(40,1,0,1),\n" +
            "(41,1,0,1),\n" +
            "(42,1,0,2),\n" +
            "(43,1,0,2),\n" +
            "(44,1,0,2),\n" +
            "(45,1,0,2),\n" +
            "(46,1,0,1),\n" +
            "(47,1,0,3),\n" +
            "(48,1,0,1),\n" +
            "(49,1,0,1),\n" +
            "(50,1,0,2),\n" +
            "(51,1,0,2),\n" +
            "(52,1,0,4),\n" +
            "(53,1,0,2),\n" +
            "(54,1,0,2),\n" +
            "(55,1,0,2);";


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
    }//Fin de on upgrade

    public Invitacion getInvitado(int codigo){
        Invitacion i=null;
        SQLiteDatabase conexion = this.getReadableDatabase();
        Cursor res = conexion.rawQuery( "select MESA, CODIGO, MESA.CANTIDADTOTAL, GRUPO.NOMBRE, INVITACION.CANTIDAD, INVITACION.CANTIDADTOTAL AS INVITADOS from INVITACION JOIN MESA ON INVITACION.MESA=MESA.IDMESA JOIN GRUPO ON INVITACION.CODIGO=GRUPO.IDGRUPO where INVITACION.CODIGO="+codigo, null );
        res.moveToFirst();
        if(res.isAfterLast() == false) {
            //Llena de informacion nos objetos y los inserta en el array
            Mesa m=new Mesa(res.getInt(res.getColumnIndex("MESA")),res.getInt(res.getColumnIndex("CANTIDADTOTAL")));
            Grupo g=new Grupo(res.getInt(res.getColumnIndex("CODIGO")),res.getString(res.getColumnIndex("NOMBRE")));
            i=new Invitacion(g,m,res.getInt(res.getColumnIndex("INVITADOS")), res.getInt(res.getColumnIndex("CANTIDAD")));
            res.moveToNext();
        }
        return i;
    }//Fin de consulta individual

    //Cuantos pueden entrar con el QR y cuantos realmente entraron
    public void agregarInvitados(int codigo, int asisten){
        SQLiteDatabase conexion=this.getWritableDatabase();
        if(conexion!=null){
            conexion.execSQL("UPDATE INVITACION set CANTIDAD="+asisten+" where CODIGO="+codigo+";");
            conexion.close();
        }//
    }//Fin de agregar invitados

    public List<Invitacion> getListadoInvitados(){
        SQLiteDatabase conexion = this.getReadableDatabase();
        Cursor res = conexion.rawQuery( "select MESA, CODIGO, MESA.CANTIDADTOTAL, GRUPO.NOMBRE, INVITACION.CANTIDAD, INVITACION.CANTIDADTOTAL AS INVITADOS from INVITACION INNER JOIN MESA ON INVITACION.MESA=MESA.IDMESA INNER JOIN GRUPO ON INVITACION.CODIGO=GRUPO.IDGRUPO order by INVITACION.CANTIDAD desc", null );
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
        conexion.close();
        return list;
    }

    public int getTotalInvitados(){
        SQLiteDatabase conexion = this.getReadableDatabase();
        Cursor res = conexion.rawQuery( "SELECT SUM(CANTIDADTOTAL) AS INVITADOS FROM INVITACION;", null );
        res.moveToFirst();
        conexion.close();
        return res.getInt(res.getColumnIndex("INVITADOS"));
    }

    public int getTotalAsistidos(){
        SQLiteDatabase conexion = this.getReadableDatabase();
        Cursor res = conexion.rawQuery( "SELECT SUM(CANTIDAD) AS INVITADOS FROM INVITACION;", null );
        res.moveToFirst();
        conexion.close();
        return res.getInt(res.getColumnIndex("INVITADOS"));
    }
}//Fin de la clase
