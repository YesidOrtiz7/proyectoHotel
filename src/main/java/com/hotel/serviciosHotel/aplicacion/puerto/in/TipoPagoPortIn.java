package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.PaymentType;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface TipoPagoPortIn {
    PaymentType obtenerTipoPagoPorId(int id) throws SearchItemNotFoundException;
    List<PaymentType> obtenerTipoPagos();
}
