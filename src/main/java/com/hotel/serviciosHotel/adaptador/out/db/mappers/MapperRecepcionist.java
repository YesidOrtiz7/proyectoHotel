package com.hotel.serviciosHotel.adaptador.out.db.mappers;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Recepcionista;
import com.hotel.serviciosHotel.dominio.entidades.Receptionist;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface MapperRecepcionist {

    @Mappings({
            @Mapping(source = "idRecep", target = "idRecep"),
            @Mapping(source = "docRecep", target = "docRecep"),
            @Mapping(source = "nombresRecep", target = "receptionistNames"),
            @Mapping(source = "apellidosRecep", target = "receptionistLastNames"),
            @Mapping(source = "nombreUsuario", target = "userName"),
            @Mapping(source = "contrasena", target = "password"),
            @Mapping(source = "recepEstado", target = "receptionistState"),

    })
    Receptionist toReceptionist(Recepcionista cliente);

    @InheritInverseConfiguration
    Recepcionista toRecepcionista(Receptionist client);
}
