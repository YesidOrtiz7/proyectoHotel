package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbltipohabitacion")
public class TipoHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoHabitacion;
    private String descripcionTipoHabitacion;

    /*@OneToMany(mappedBy = "tipoHabitacion")
    private List<Habitacion> habitaciones;*/

    public TipoHabitacion() {
    }

    public TipoHabitacion(String descripcionTipoHabitacion) {
        this.descripcionTipoHabitacion = descripcionTipoHabitacion;
    }

    public void setIdTipoHabitacion(int idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    /*public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }*/

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
