package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

@Entity
@Table(name = "tblhabitacion")
public class Habitacion {

    private int numHabitacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEstHab",referencedColumnName = "idEstado")
    private EstadosHabitacion idEstHab;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TipoHabitacion",referencedColumnName = "idTipoHabitacion")
    private TipoHabitacion tipoHabitacion;
    private int numeroCamas;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHabitacion;

    public Habitacion(int numHabitacion, EstadosHabitacion idEstHab, TipoHabitacion tipoHabitacion, int numeroCamas) {
        this.numHabitacion = numHabitacion;
        this.idEstHab = idEstHab;
        this.tipoHabitacion = tipoHabitacion;
        this.numeroCamas = numeroCamas;
    }

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
