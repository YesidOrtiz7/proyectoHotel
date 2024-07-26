package com.hotel.serviciosHotel.dominio.entidades;

import java.time.LocalDateTime;
import java.util.Date;


public class Service {
    private int idService;
    private ReceptionistEntity idRecep;
    private Client idClient;
    private Room idRoom;
    private RateType idRateType;
    private Municipios cliProcedencia;
    private Municipios cliDestino;
    private PaymentType idTipoPago;
    private double payment;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private boolean itsPaid;
    private boolean state;

    public Service(int idService, ReceptionistEntity idRecep, Client idClient, Room idRoom, RateType idRateType,
                   Municipios cliProcedencia, Municipios cliDestino, PaymentType idTipoPago, double payment,
                   LocalDateTime fechaEntrada, LocalDateTime fechaSalida, boolean state, boolean itsPaid) {
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
        this.state = state;
        this.itsPaid=itsPaid;
    }

    public Service() {
    }

    public RateType getIdRateType() {
        return idRateType;
    }

    public void setIdRateType(RateType idRateType) {
        this.idRateType = idRateType;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public ReceptionistEntity getIdRecep() {
        return idRecep;
    }

    public void setIdRecep(ReceptionistEntity idRecep) {
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

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
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

    public boolean isItsPaid() {
        return itsPaid;
    }

    public void setItsPaid(boolean itsPaid) {
        this.itsPaid = itsPaid;
    }
}
