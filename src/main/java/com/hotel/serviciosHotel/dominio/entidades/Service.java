package com.hotel.serviciosHotel.dominio.entidades;

import java.util.Date;


public class Service {
    private int idService;
    private Receptionist idRecep;
    private Client idClient;
    private Room idRoom;
    private RateType idRateType;
    private Municipios cliProcedencia;
    private Municipios cliDestino;
    private PaymentType idTipoPago;
    private int payment;
    private Date fechaEntrada;
    private Date fechaSalida;

    public Service(int idService, Receptionist idRecep, Client idClient, Room idRoom, RateType idRateType, Municipios cliProcedencia, Municipios cliDestino, PaymentType idTipoPago, int payment, Date fechaEntrada, Date fechaSalida) {
        this.idService = idService;
        this.idRecep = idRecep;
        this.idClient = idClient;
        this.idRoom = idRoom;
        this.idRateType = idRateType;
        this.cliProcedencia = cliProcedencia;
        this.cliDestino = cliDestino;
        this.idTipoPago = idTipoPago;
        this.payment = payment;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public Service() {
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public Receptionist getIdRecep() {
        return idRecep;
    }

    public void setIdRecep(Receptionist idRecep) {
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

    public PaymentType getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(PaymentType idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
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
