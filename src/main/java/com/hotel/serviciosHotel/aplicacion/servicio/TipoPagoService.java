package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.TipoPagoPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.PaymentPortOut;
import com.hotel.serviciosHotel.dominio.entidades.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPagoService implements TipoPagoPortIn {
    private PaymentPortOut portOut;

    @Autowired
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
