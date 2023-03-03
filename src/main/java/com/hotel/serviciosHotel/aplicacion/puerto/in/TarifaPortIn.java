package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Municipios;
import com.hotel.serviciosHotel.dominio.entidades.RateType;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface TarifaPortIn {
    RateType registrarTarifa(RateType rate);
    List<RateType> obtenerTarifas();
    RateType obtenerTarifaPorId(int id) throws SearchItemNotFoundException;
    RateType actualizarTarifa(RateType rate);
    boolean eliminarTarifa(RateType rate);
}
