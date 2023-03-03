package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.TipoPagoPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.PaymentPortOut;
import com.hotel.serviciosHotel.dominio.entidades.PaymentType;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
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
    public PaymentType obtenerTipoPagoPorId(int id) throws SearchItemNotFoundException {
        PaymentType response=portOut.consultarTipoPagoPorId(id);
        if (response!=null){
            return response;
        }else {
            throw new SearchItemNotFoundException("el tipo pago con el id "+id+" no existe");
        }
    }

    @Override
    public List<PaymentType> obtenerTipoPagos() {
        return portOut.obtenerTipoPagos();
    }
}
