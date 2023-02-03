package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

@Entity
@Table(name = "tbltipohabitacion")
public class TipoHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoHabitacion;
    private String descripcionTipoHabitacion;

    @OneToOne(mappedBy = "tipoHabitacion")
    private Habitacion habitacion;

    public TipoHabitacion() {
    }

    public TipoHabitacion(String descripcionTipoHabitacion) {
        this.descripcionTipoHabitacion = descripcionTipoHabitacion;
    }

    public int getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public String getDescripcionTipoHabitacion() {
        return descripcionTipoHabitacion;
    }

    public void setDescripcionTipoHabitacion(String descripcionTipoHabitacion) {
        this.descripcionTipoHabitacion = descripcionTipoHabitacion;
    }
}
