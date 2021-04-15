package com.example.eventwqr.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eventwqr.Modelo.*;

import java.util.ArrayList;

public class BD extends SQLiteOpenHelper {
    private static final String NOMBRE="BD_Evento.bd";
    private static final int VERSION_BD=1;
    private static final String QUERY=
            "CREATE TABLE MESA (IDMESA INT PRIMARY KEY, CANTIDADTOTAL INT); " +
            "CREATE TABLE GRUPO (IDGRUPO INT PRIMARY KEY, NOMBRE TEXT); " +
            "CREATE TABLE INVITACION (CODIGO INT PRIMARY KEY,MESA INT,CANTIDAD INT,CANTIDADTOTAL INT, FOREIGN KEY(CODIGO) REFERENCES GRUPO(IDGRUPO), FOREIGN KEY(MESA) REFERENCES MESA(IDMESA) );";

    public BD(Context context) {
        super(context, NOMBRE, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<=newVersion){
            //TODO actuaizar base de datos
            //db.deleteDatabase(new File("BD_Evento.bd"));
            //db.execSQL("DROP TABLE INVITACION; DROP TABLE MESA; DROP TABLE GRUPO;");
            //db.execSQL(QUERY);
        }
    }

    //Cuantos pueden entrar con el QR y cuantos realmente entraron
    public void agregarInvitados(int codigo, int mesa, int cantidad, int asisten){
        SQLiteDatabase conexion=this.getWritableDatabase();
        if(conexion!=null){
            Cursor res=conexion.rawQuery("SELECT * FROM INVITACION WHERE CODIGO="+codigo,null);
            System.out.println(res);
            res.moveToFirst();
            if(res!=null)
            {

            }
            else
            {
                conexion.execSQL("INSERT INTO INVITACION VALUES ('"+codigo+"','"+mesa+"','"+cantidad+"','"+asisten+"')");
            }
            /*while(res.isAfterLast() == false) {
                res.getString(res.getColumnIndex("name"));
                res.moveToNext();
            }*/


            conexion.close();
        }//
    }//Fin de agregar invitados

    public ArrayList<Invitacion> getListadoInvitados(){
        SQLiteDatabase conexion = this.getReadableDatabase();
        ArrayList<Invitacion> array_list = new ArrayList<Invitacion>();
        Cursor res = conexion.rawQuery( "select * from INVITADOS", null );
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            //Llena de informacion nos objetos y los inserta en el array
            Mesa m=new Mesa(res.getInt(res.getColumnIndex("IDMESA")),res.getInt(res.getColumnIndex("CANTIDADTOTAL")));
            Grupo g=new Grupo(res.getInt(res.getColumnIndex("IDGRUPO")),res.getString(res.getColumnIndex("NOMBRE")));
            Invitacion i=new Invitacion(g,m,res.getInt(res.getColumnIndex("CANTIDADTOTAL")));
            array_list.add(i);
            res.moveToNext();
        }
        return array_list;
    }
}//Fin de la clase
