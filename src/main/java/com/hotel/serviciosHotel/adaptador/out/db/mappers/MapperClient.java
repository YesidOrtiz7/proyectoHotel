package com.hotel.serviciosHotel.adaptador.out.db.mappers;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Cliente;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface MapperClient {

    @Mappings({
            @Mapping(source = "idCliente", target = "idCliente"),
            @Mapping(source = "documentoCliente", target = "documentoCliente"),
            @Mapping(source = "primerNombreCliente", target = "primerNombreCliente"),
            @Mapping(source = "segundoNombreCliente", target = "segundoNombreCliente"),
            @Mapping(source = "primerApellidoCliente", target = "primerApellidoCliente"),
            @Mapping(source = "segundoApellidoCliente", target = "segundoApellidoCliente"),
            @Mapping(source = "celularCliente", target = "celularCliente"),

    })
    Client toClient(Cliente cliente);

    @InheritInverseConfiguration
    Cliente toCliente(Client client);
}
