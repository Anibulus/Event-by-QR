package com.example.eventwqr.Modelo;

public class Invitacion {
    private Grupo familia;
    private Mesa mesa;
    private int cantidadInvitados;
    private int asisten;

    public Invitacion(Grupo familia, Mesa mesa, int cantidadInvitados) {
        this.familia = familia;
        this.mesa = mesa;
        this.cantidadInvitados = cantidadInvitados;
    }

    public Invitacion(Grupo familia, Mesa mesa, int cantidadInvitados, int asistido) {
        this.familia = familia;
        this.mesa = mesa;
        this.cantidadInvitados = cantidadInvitados;
        this.asisten=asistido;
    }

    public int getAsisten() {
        return asisten;
    }

    public void setAsisten(int asisten) {
        this.asisten = asisten;
    }

    public Grupo getFamilia() {
        return familia;
    }

    public void setFamilia(Grupo familia) {
        this.familia = familia;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public int getCantidadInvitados() {
        return cantidadInvitados;
    }

    public void setCantidadInvitados(int cantidadInvitados) {
        this.cantidadInvitados = cantidadInvitados;
    }
}
