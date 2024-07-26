package com.hotel.serviciosHotel.adaptador.out.db.mappers;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.room.MapperRoom;
import com.hotel.serviciosHotel.adaptador.out.db.mappers.room.MapperRoomType;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Servicio;
import com.hotel.serviciosHotel.dominio.entidades.Service;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",uses = {MapperTipoPago.class, MapperRoomType.class,
MapperRecepcionist.class,MapperClient.class, MapperMunicipios.class, MapperRoom.class})
public interface MapperService {
    @Mappings({
            @Mapping(source = "idServicio",target = "idService"),
            @Mapping(source = "idRecep",target = "idRecep"),
            @Mapping(source = "idCliente",target = "idClient"),
            @Mapping(source = "idHabitacion",target = "idRoom"),
            @Mapping(source = "idTipoTarifa",target = "idRateType"),
            @Mapping(source = "cliProcedencia",target = "cliProcedencia"),
            @Mapping(source = "cliDestino",target = "cliDestino"),
            @Mapping(source = "idTipoPago",target = "idTipoPago"),
            @Mapping(source = "pago",target = "payment"),
            @Mapping(source = "fechaEntrada",target = "fechaEntrada"),
            @Mapping(source = "fechaSalida",target = "fechaSalida"),
            @Mapping(source = "estado",target = "state"),
            @Mapping(source = "pagado",target = "itsPaid")
    })
    Service toService(Servicio servicio);

    @InheritInverseConfiguration
    Servicio toServicio(Service service);
}
