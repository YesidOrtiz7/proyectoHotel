package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.PaymentType;

import java.util.List;

public interface TipoPagoPortIn {
    PaymentType obtenerTipoPagoPorId(int id);
    List<PaymentType> obtenerTipoPagos();
}
