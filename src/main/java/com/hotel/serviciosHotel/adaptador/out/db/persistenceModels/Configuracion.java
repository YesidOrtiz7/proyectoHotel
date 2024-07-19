package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

@Entity
@Table(name = "configuracion")
public class Configuracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;
    private int idEstadoPredeterminadoCierreServicio;

    public Configuracion() {
    }

    public Configuracion(int id, int idEstadoPredeterminadoCierreServicio) {
        this.id = id;
        this.idEstadoPredeterminadoCierreServicio = idEstadoPredeterminadoCierreServicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstadoPredeterminadoCierreServicio() {
        return idEstadoPredeterminadoCierreServicio;
    }

    public void setIdEstadoPredeterminadoCierreServicio(int idEstadoPredeterminadoCierreServicio) {
        this.idEstadoPredeterminadoCierreServicio = idEstadoPredeterminadoCierreServicio;
    }
}
