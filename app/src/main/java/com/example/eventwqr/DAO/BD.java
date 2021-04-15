package com.example.eventwqr.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eventwqr.Modelo.*;

import java.security.cert.CRLReason;
import java.util.ArrayList;
import java.util.List;

public class BD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD="BD_Evento.bd";
    private static final int VERSION_BD=1;
    private static final String CREATE_MESA=
            "CREATE TABLE MESA (IDMESA INT PRIMARY KEY, CANTIDADTOTAL INT)";
    private static final String CREATE_GRUPO=
            "CREATE TABLE GRUPO (IDGRUPO INT PRIMARY KEY, NOMBRE TEXT)";
    private static final String CREATE_INVITACION=
            "CREATE TABLE INVITACION (CODIGO INT PRIMARY KEY,MESA INT,CANTIDAD INT,CANTIDADTOTAL INT, FOREIGN KEY(CODIGO) REFERENCES GRUPO(IDGRUPO), FOREIGN KEY(MESA) REFERENCES MESA(IDMESA))";

    public BD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MESA);
        db.execSQL(CREATE_GRUPO);
        db.execSQL(CREATE_INVITACION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("------------Quise entrar a borrar");
        if(oldVersion<=newVersion){
            //TODO actuaizar base de datos
            //db.deleteDatabase(new File("BD_Evento.bd"));
            //db.execSQL("DROP TABLE INVITACION; DROP TABLE MESA; DROP TABLE GRUPO;");
            //Tutorial "DROP TABLE IF EXISTS"+CREATE_MESA;
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
                try {
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
        Cursor res = conexion.rawQuery( "select * from INVITADOS", null );
        List<Invitacion> list = new ArrayList<>();

        res.moveToFirst();
        while(res.isAfterLast() == false) {
            //Llena de informacion nos objetos y los inserta en el array
            Mesa m=new Mesa(res.getInt(res.getColumnIndex("IDMESA")),res.getInt(res.getColumnIndex("CANTIDADTOTAL")));
            Grupo g=new Grupo(res.getInt(res.getColumnIndex("IDGRUPO")),res.getString(res.getColumnIndex("NOMBRE")));
            Invitacion i=new Invitacion(g,m,res.getInt(res.getColumnIndex("CANTIDADTOTAL")));
            list.add(i);
            res.moveToNext();
        }
        return list;
    }
}//Fin de la clase
