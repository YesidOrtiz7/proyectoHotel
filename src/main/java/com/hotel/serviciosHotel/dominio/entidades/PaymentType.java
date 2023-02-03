package com.hotel.serviciosHotel.dominio.entidades;

import jakarta.persistence.*;

public class PaymentType {
    private int idPago;
    private String descripcionPago;

    public PaymentType() {
    }

    public PaymentType(int idPago, String descripcionPago) {
        this.idPago = idPago;
        this.descripcionPago = descripcionPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getDescripcionPago() {
        return descripcionPago;
    }

    public void setDescripcionPago(String descripcionPago) {
        this.descripcionPago = descripcionPago;
    }
}
