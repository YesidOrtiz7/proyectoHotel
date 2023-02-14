package com.hotel.serviciosHotel.dominio.entidades;

import jakarta.persistence.*;

public class RateType {
    private int idTipoTarifa;
    private String descripcionTarifa;
    private int porcentajeTarifa;

    public RateType() {
    }

    public RateType(int idTipoTarifa, String descripcionTarifa, int porcentajeTarifa) {
        this.idTipoTarifa = idTipoTarifa;
        this.descripcionTarifa = descripcionTarifa;
        this.porcentajeTarifa = porcentajeTarifa;
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

    public int getPorcentajeTarifa() {
        return porcentajeTarifa;
    }

    public void setPorcentajeTarifa(int porcentajeTarifa) {
        this.porcentajeTarifa = porcentajeTarifa;
    }
}
