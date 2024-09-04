package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tblhabitacion")
public class Habitacion {

    private int numHabitacion;

    /*@ManyToOne
    @JoinColumn(name = "fk_id_est_hab",insertable = false,updatable = false,referencedColumnName = "id_estado")*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstHab", nullable = false)
    private EstadosHabitacion idEstHab;


    /*@ManyToOne
    @JoinColumn(name = "fk_tipo_habitacion",insertable = false,updatable = false,referencedColumnName = "idTipoHabitacion")*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoHabitacion",nullable = false)
    private TipoHabitacion tipoHabitacion;
    private double precioHabitacion24Horas;
    private int numeroCamas;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHabitacion;
    @OneToMany(mappedBy = "idHabitacion",cascade = CascadeType.ALL)
    private List<HistorialHabitaciones> historialHabitaciones;

    public Habitacion() {
    }

    public Habitacion(int numHabitacion, EstadosHabitacion idEstHab,
                      TipoHabitacion tipoHabitacion, double precioHabitacion24Horas,
                      int numeroCamas, int idHabitacion) {
        this.numHabitacion = numHabitacion;
        this.idEstHab = idEstHab;
        this.tipoHabitacion = tipoHabitacion;
        this.precioHabitacion24Horas = precioHabitacion24Horas;
        this.numeroCamas = numeroCamas;
        this.idHabitacion = idHabitacion;
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

    public double getPrecioHabitacion24Horas() {
        return precioHabitacion24Horas;
    }

    public void setPrecioHabitacion24Horas(double precioHabitacion24Horas) {
        this.precioHabitacion24Horas = precioHabitacion24Horas;
    }

    @Override
    public String toString() {
        return "id habitacion: "+this.idHabitacion+"\n"+
                "numero habitacion: "+this.numHabitacion+"\n"+
                "estado habitacion:[ "+this.idEstHab.getIdEstado()+",\n"+
                this.idEstHab.getNombreEstado()+"]\n"+
                "tipo habitacion: ["+this.tipoHabitacion.getIdTipoHabitacion()+",\n"+
                this.tipoHabitacion.getDescripcionTipoHabitacion()+"]\n"+
                "numero camas: "+this.getNumeroCamas();
    }

}
