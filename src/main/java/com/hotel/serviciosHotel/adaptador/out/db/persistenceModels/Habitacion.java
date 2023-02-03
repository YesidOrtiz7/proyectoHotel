package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

@Entity
@Table(name = "tblhabitacion")
public class Habitacion {

    private int numHabitacion;

    @ManyToOne
    @JoinColumn(name = "id_est_hab",insertable = false,updatable = false)
    private EstadosHabitacion idEstHab;


    @ManyToOne
    @JoinColumn(name = "tipo_habitacion",insertable = false,updatable = false)
    private TipoHabitacion tipoHabitacion;
    private int numeroCamas;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHabitacion;

    public Habitacion() {
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public EstadosHabitacion getIdEstHab() {
        return idEstHab;
    }

    public void setIdEstHab(EstadosHabitacion idEstHab) {
        this.idEstHab = idEstHab;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getNumeroCamas() {
        return numeroCamas;
    }

    public void setNumeroCamas(int numeroCamas) {
        this.numeroCamas = numeroCamas;
    }
}
