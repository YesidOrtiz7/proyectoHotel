package com.hotel.serviciosHotel.adaptador.out.db.mappers.room;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.EstadosHabitacion;
import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MapperRoomStatus {
    @Mappings({
            @Mapping(source = "idEstado",target = "idStatus"),
            @Mapping(source = "nombreEstado",target = "statusName")
    })
    RoomStatus toRoomStatus(EstadosHabitacion estado);

    @InheritInverseConfiguration
    //@Mapping(target = "habitaciones",ignore = true)
    EstadosHabitacion toEstadosHabitacion(RoomStatus status);
}
