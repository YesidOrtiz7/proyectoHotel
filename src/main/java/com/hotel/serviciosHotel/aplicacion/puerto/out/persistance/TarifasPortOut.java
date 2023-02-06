package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;


import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.TipoTarifa;
import com.hotel.serviciosHotel.dominio.entidades.RateType;

import java.util.List;
import java.util.Optional;

public interface TarifasPortOut {
    RateType saveRate(RateType rate);
    List<RateType> getRates();
    Optional<RateType> getRateById(int id);
    RateType updateRate(RateType rate);
    boolean deleteRate(RateType rate);
}
