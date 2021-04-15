package com.example.eventwqr.Modelo;

public class Invitacion {
    private Grupo familia;
    private Mesa mesa;
    private int cantidadInvitados;

    public Invitacion(Grupo familia, Mesa mesa, int cantidadInvitados) {
        this.familia = familia;
        this.mesa = mesa;
        this.cantidadInvitados = cantidadInvitados;
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
