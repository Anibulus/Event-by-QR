package com.example.eventwqr.Modelo;

public class Mesa {
    private int idMesa;
    private int cantidadTotal;

    public Mesa(int id){
        this.idMesa=id;
        this.cantidadTotal=0;
    }

    public Mesa(int id, int cant){
        this.idMesa=id;
        this.cantidadTotal=cant;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }
}
