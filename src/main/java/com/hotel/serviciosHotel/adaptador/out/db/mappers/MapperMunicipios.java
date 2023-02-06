package com.hotel.serviciosHotel.adaptador.out.db.mappers;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Municipios;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MapperMunicipios {
    @Mappings({
            @Mapping(source = "idMunicipios",target = "idMunicipios"),
            @Mapping(source = "nombreMun",target = "nombreMun")
    })
    Municipios toMunicipiosPersistence(com.hotel.serviciosHotel.dominio.entidades.Municipios municipio);

    @InheritInverseConfiguration
    com.hotel.serviciosHotel.dominio.entidades.Municipios toMunicipiosEntidades(Municipios municipio)
        ;
}
