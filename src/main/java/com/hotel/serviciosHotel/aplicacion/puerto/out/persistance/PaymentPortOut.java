package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.PaymentType;

import java.util.List;

public interface PaymentPortOut {
    PaymentType consultarTipoPagoPorId(int id);
    List<PaymentType> obtenerTipoPagos();
    PaymentType guardarTipoPago(PaymentType type);
    boolean eliminarTipoPago(PaymentType type);
}
