package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

@Entity
@Table(name = "tbltipopago")
public class TipoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPago;

    private String descripcionPago;

    public TipoPago() {
    }

    public TipoPago(String descripcionPago) {
        this.descripcionPago = descripcionPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public String getDescripcionPago() {
        return descripcionPago;
    }

    public void setDescripcionPago(String descripcionPago) {
        this.descripcionPago = descripcionPago;
    }
}
