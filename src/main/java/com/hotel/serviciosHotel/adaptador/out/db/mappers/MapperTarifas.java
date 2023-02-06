package com.hotel.serviciosHotel.adaptador.out.db.mappers;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.TipoTarifa;
import com.hotel.serviciosHotel.dominio.entidades.RateType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MapperTarifas {

    @Mappings({
            @Mapping(source = "idTipoTarifa",target = "idTipoTarifa"),
            @Mapping(source = "descripcionTarifa",target = "descripcionTarifa")
    })
    TipoTarifa toTarifas(RateType rateType);

    @InheritInverseConfiguration
    RateType toRateType(TipoTarifa tarifa);
}
