package com.hotel.serviciosHotel.dominio.entidades;

import java.util.Date;


public class Service {
    private int idServicio;
    private Recepcionist idRecep;
    private Client idClient;
    private Room idRoom;
    private RateType idRateType;
    private Municipios cliProcedencia;
    private Municipios cliDestino;
    private int idTipoPago;
    private int pago;
    private Date fechaEntrada;
    private Date fechaSalida;

    public Service(int idServicio, Recepcionist idRecep, Client idClient, Room idRoom, RateType idRateType, Municipios cliProcedencia, Municipios cliDestino, int idTipoPago, int pago, Date fechaEntrada, Date fechaSalida) {
        this.idServicio = idServicio;
        this.idRecep = idRecep;
        this.idClient = idClient;
        this.idRoom = idRoom;
        this.idRateType = idRateType;
        this.cliProcedencia = cliProcedencia;
        this.cliDestino = cliDestino;
        this.idTipoPago = idTipoPago;
        this.pago = pago;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public Service() {
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public Recepcionist getIdRecep() {
        return idRecep;
    }

    public void setIdRecep(Recepcionist idRecep) {
        this.idRecep = idRecep;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Room getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Room idRoom) {
        this.idRoom = idRoom;
    }

    public RateType getIdTipoTarifa() {
        return idRateType;
    }

    public void setIdTipoTarifa(RateType idRateType) {
        this.idRateType = idRateType;
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

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
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
