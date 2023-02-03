package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

@Entity
@Table(name = "tblestadoshabitacion")
public class EstadosHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstado;
    private String NombreEstado;

    @OneToOne(mappedBy = "idEstHab")
    private Habitacion habitacion;

    public EstadosHabitacion() {
    }

    public EstadosHabitacion(String nombreEstado) {
        NombreEstado = nombreEstado;
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
}
