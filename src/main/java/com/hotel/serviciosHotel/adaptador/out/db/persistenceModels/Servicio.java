package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="tblservicio")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private int idServicio;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idRecep",referencedColumnName = "idRecep")*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRecep",nullable = false)
    private Recepcionista idRecep;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente",referencedColumnName = "id_cliente")*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente",nullable = false)
    private Cliente idCliente;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idHabitacion",referencedColumnName = "idHabitacion")*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHabitacion", nullable = false)
    private Habitacion idHabitacion;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTipoTarifa",referencedColumnName = "idTipoTarifa")*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoTarifa", nullable = false)
    private TipoTarifa idTipoTarifa;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliProcedencia",referencedColumnName = "idMunicipios")*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cli_procedencia", nullable = false/*,insertable = false*/,updatable = false)
    private Municipios cliProcedencia;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliDestino",referencedColumnName = "idMunicipios")*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cli_destino", nullable = false/*,insertable = false*/,updatable = false)
    private Municipios cliDestino;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTipoPago",referencedColumnName = "idPago")*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoPago", nullable = false)
    private TipoPago idTipoPago;
    @Null
    private double pago;
    private boolean pagado;
    @NotNull
    private LocalDateTime fechaEntrada;
    @NotNull
    private LocalDateTime fechaSalida;

    private boolean estado;
    /*@OneToMany(mappedBy = "idHabitacion",cascade = CascadeType.ALL)
    private List<HistorialHabitaciones> historialHabitaciones;*/

    public Servicio() {
    }

    public Servicio(int idServicio, Recepcionista idRecep, Cliente idCliente, Habitacion idHabitacion,
                    TipoTarifa idTipoTarifa, Municipios cliProcedencia, Municipios cliDestino, TipoPago idTipoPago,
                    double pago, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, boolean estado, boolean pagado)
                     {//List<HistorialHabitaciones> historialHabitaciones)
        this.idServicio = idServicio;
        this.idRecep = idRecep;
        this.idCliente = idCliente;
        this.idHabitacion = idHabitacion;
        this.idTipoTarifa = idTipoTarifa;
        this.cliProcedencia = cliProcedencia;
        this.cliDestino = cliDestino;
        this.idTipoPago = idTipoPago;
        this.pago = pago;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estado = estado;
        this.pagado=pagado;
        //this.historialHabitaciones=historialHabitaciones;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public Recepcionista getIdRecep() {
        return idRecep;
    }

    public void setIdRecep(Recepcionista idRecep) {
        this.idRecep = idRecep;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Habitacion getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Habitacion idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public TipoTarifa getIdTipoTarifa() {
        return idTipoTarifa;
    }

    public void setIdTipoTarifa(TipoTarifa idTipoTarifa) {
        this.idTipoTarifa = idTipoTarifa;
    }

    public Municipios getCliProcedencia() {
        return cliProcedencia;
    }

    public void setCliProcedencia(Municipios cliProcedencia) {
        this.cliProcedencia = cliProcedencia;
    }

    public Municipios getCliDestino() {
        return cliDestino;
    }

    public void setCliDestino(Municipios cliDestino) {
        this.cliDestino = cliDestino;
    }

    public TipoPago getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(TipoPago idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public boolean isEstado() {
        return estado;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
/*
    public List<HistorialHabitaciones> getHistorialHabitaciones() {
        return historialHabitaciones;
    }

    public void setHistorialHabitaciones(List<HistorialHabitaciones> historialHabitaciones) {
        this.historialHabitaciones = historialHabitaciones;
    }*/
}