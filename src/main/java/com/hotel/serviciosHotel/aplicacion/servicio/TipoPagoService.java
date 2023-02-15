package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.TipoPagoPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.PaymentPortOut;
import com.hotel.serviciosHotel.dominio.entidades.PaymentType;

import java.util.List;

public class TipoPagoService implements TipoPagoPortIn {
    private PaymentPortOut portOut;

    public void setPortOut(PaymentPortOut portOut) {
        this.portOut = portOut;
    }

    @Override
    public PaymentType obtenerTipoPagoPorId(int id) {
        return portOut.consultarTipoPagoPorId(id);
    }

    @Override
    public List<PaymentType> obtenerTipoPagos() {
        return portOut.obtenerTipoPagos();
    }
}
