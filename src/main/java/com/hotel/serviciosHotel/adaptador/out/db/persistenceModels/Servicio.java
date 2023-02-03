package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="tblservicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private int idServicio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idRecep",referencedColumnName = "idRecep")
    private Recepcionista idRecep;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente",referencedColumnName = "id_cliente")
    private Cliente idCliente;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idHabitacion",referencedColumnName = "idHabitacion")
    private Habitacion idHabitacion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTipoTarifa",referencedColumnName = "idTipoTarifa")
    private TipoTarifa idTipoTarifa;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliProcedencia",referencedColumnName = "idMunicipios")
    private Municipios cliProcedencia;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliDestino",referencedColumnName = "idMunicipios")
    private Municipios cliDestino;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTipoPago",referencedColumnName = "idPago")
    private TipoPago idTipoPago;
    private int pago;
    private Date fechaEntrada;
    private Date fechaSalida;

    public Servicio() {
    }

    public Servicio(Recepcionista idRecep, Cliente idCliente, Habitacion idHabitacion, TipoTarifa idTipoTarifa, Municipios cliProcedencia, Municipios cliDestino, TipoPago idTipoPago, int pago, Date fechaEntrada, Date fechaSalida) {
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

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
