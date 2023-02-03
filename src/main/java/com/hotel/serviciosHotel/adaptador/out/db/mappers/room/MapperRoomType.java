package com.hotel.serviciosHotel.adaptador.out.db.mappers.room;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.TipoHabitacion;
import com.hotel.serviciosHotel.dominio.entidades.RoomType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MapperRoomType {
    @Mappings({
            @Mapping(source = "idTipoHabitacion",target = "idRoomType"),
            @Mapping(source = "descripcionTipoHabitacion",target = "roomTypeDescription")
    })
    RoomType toRoomType(TipoHabitacion tipoHabitacion);

    @InheritInverseConfiguration
    TipoHabitacion toTipoHabitacion(RoomType type);
}
