package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

@Entity
@Table(name = "tbltipotarifa")
public class TipoTarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoTarifa;
    private String descripcionTarifa;
    private int porcentajeTarifa;

    public TipoTarifa() {
    }

    public TipoTarifa(String descripcionTarifa, int porcentajeTarifa) {
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
