package com.hotel.serviciosHotel.adaptador.out.db.mappers.room;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Habitacion;
import com.hotel.serviciosHotel.dominio.entidades.Room;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring",uses = {MapperRoomStatus.class, MapperRoomType.class})
public interface MapperRoom {
    @Mappings({
            @Mapping(source = "roomNumber",target = "numHabitacion"),
            @Mapping(source = "idRoomStatus",target = "idEstHab"),
            @Mapping(source = "roomType",target = "tipoHabitacion"),
            @Mapping(source = "bedsNumber",target = "numeroCamas"),
            @Mapping(source = "idRoom",target = "idHabitacion"),
            @Mapping(source = "roomPriceNight",target = "precioHabitacionNoche"),
    })
    Habitacion toHabitacion(Room room);

    @InheritInverseConfiguration
    Room toRoom(Habitacion habitacion);
}
