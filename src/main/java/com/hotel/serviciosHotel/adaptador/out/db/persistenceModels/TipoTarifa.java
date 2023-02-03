package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

@Entity
@Table(name = "tbltipotarifa")
public class TipoTarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoTarifa;
    private String descripcionTarifa;

    public TipoTarifa() {
    }

    public TipoTarifa(String descripcionTarifa) {
        this.descripcionTarifa = descripcionTarifa;
    }

    public int getIdTipoTarifa() {
        return idTipoTarifa;
    }

    public String getDescripcionTarifa() {
        return descripcionTarifa;
    }

    public void setDescripcionTarifa(String descripcionTarifa) {
        this.descripcionTarifa = descripcionTarifa;
    }
}
