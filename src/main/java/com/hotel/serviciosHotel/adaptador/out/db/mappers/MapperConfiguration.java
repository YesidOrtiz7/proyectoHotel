package com.hotel.serviciosHotel.adaptador.out.db.mappers;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Configuracion;
import com.hotel.serviciosHotel.dominio.entidades.BusinessConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MapperConfiguration {
    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "idEstadoPredeterminadoCierreServicio",target = "idStateDefaultToCloseService")
    })
    BusinessConfiguration toBussinesConfiguration(Configuracion configuracion);
    @InheritInverseConfiguration
    Configuracion toConfiguracion(BusinessConfiguration businessConfiguration);
}
