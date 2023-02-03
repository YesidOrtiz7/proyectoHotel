package com.hotel.serviciosHotel.dominio.entidades;

import jakarta.persistence.*;

public class RateType {
    private int idTipoTarifa;
    private String descripcionTarifa;

    public RateType() {
    }

    public RateType(int idTipoTarifa, String descripcionTarifa) {
        this.idTipoTarifa = idTipoTarifa;
        this.descripcionTarifa = descripcionTarifa;
    }

    public int getIdTipoTarifa() {
        return idTipoTarifa;
    }

    public void setIdTipoTarifa(int idTipoTarifa) {
        this.idTipoTarifa = idTipoTarifa;
    }

    public String getDescripcionTarifa() {
        return descripcionTarifa;
    }

    public void setDescripcionTarifa(String descripcionTarifa) {
        this.descripcionTarifa = descripcionTarifa;
    }
}
