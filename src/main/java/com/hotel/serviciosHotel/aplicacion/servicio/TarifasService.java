package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.TarifaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.TarifasPortOut;
import com.hotel.serviciosHotel.dominio.entidades.RateType;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifasService implements TarifaPortIn {

    private TarifasPortOut portOut;

    @Autowired
    public void setPortOut(TarifasPortOut portOut) {
        this.portOut = portOut;
    }

    @Override
    public RateType registrarTarifa(RateType rate) {
        return portOut.saveRate(rate);
    }

    @Override
    public List<RateType> obtenerTarifas() {
        List<RateType> rates=portOut.getRates();
        if (rates==null){
            return null;
        }else {
            return rates;
        }
    }

    @Override
    public RateType obtenerTarifaPorId(int id) throws SearchItemNotFoundException {
        Optional<RateType> rate=portOut.getRateById(id);
        if (rate!=null){
            return rate.get();
        }else {
            throw new SearchItemNotFoundException("tarifa con el id "+id+" no existe");
        }
    }

    @Override
    public RateType actualizarTarifa(RateType rate) {
        return portOut.updateRate(rate);
    }

    @Override
    public boolean eliminarTarifa(RateType rate) {
        return portOut.deleteRate(rate);
    }
}
