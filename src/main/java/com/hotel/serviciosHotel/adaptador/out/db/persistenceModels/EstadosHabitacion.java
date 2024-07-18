package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tblestadoshabitacion")
public class EstadosHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private int idEstado;
    private String NombreEstado;
    private boolean visibleEnSeleccion;
    private boolean predeterminadoParaCierre;

    @OneToMany(mappedBy = "idEstHab",cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones;

    public EstadosHabitacion() {
    }

    public EstadosHabitacion(String nombreEstado, boolean visibleEnSeleccion, boolean predeterminadoParaCierre) {
        NombreEstado = nombreEstado;
        this.visibleEnSeleccion = visibleEnSeleccion;
        this.predeterminadoParaCierre = predeterminadoParaCierre;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public String getNombreEstado() {
        return NombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        NombreEstado = nombreEstado;
    }

    public boolean isVisibleEnSeleccion() {
        return visibleEnSeleccion;
    }

    public void setVisibleEnSeleccion(boolean visibleEnSeleccion) {
        this.visibleEnSeleccion = visibleEnSeleccion;
    }

    public boolean isPredeterminadoParaCierre() {
        return predeterminadoParaCierre;
    }

    public void setPredeterminadoParaCierre(boolean predeterminadoParaCierre) {
        this.predeterminadoParaCierre = predeterminadoParaCierre;
    }
}
