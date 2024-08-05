package com.hotel.serviciosHotel.adaptador.out.db.mappers;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.room.MapperRoom;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.HistorialHabitaciones;
import com.hotel.serviciosHotel.dominio.entidades.RoomHistory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {MapperRoom.class, MapperService.class})
public interface MapperRoomHistory {
    @Mappings({
            @Mapping(source = "idRecord",target = "idHistorial"),
            @Mapping(source = "idService",target = "idServicio"),
            @Mapping(source = "idRoom",target = "idHabitacion"),
            @Mapping(source = "sinceDate",target = "desde"),
            @Mapping(source = "tillDate",target = "hasta"),
    })
    HistorialHabitaciones toHistorial(RoomHistory history);
    @InheritInverseConfiguration
    RoomHistory toHistory(HistorialHabitaciones historial);
}
