package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.RoomType;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface TipoHabitacionPortIn {
    RoomType registrarTipoHabitacion(RoomType tipo);
    List<RoomType> obtenerTipoHabitaciones();
    RoomType obtenerTipoHabitacionPorId(int id) throws SearchItemNotFoundException;
    RoomType actualizarTipoHabitacion(RoomType tipo);
    boolean eliminarTipoHabitacion(RoomType tipo);
    boolean eliminarTipoHabitacionPorId(int idTipo);
    boolean tipoHabitacionExiste(int idTipo);
}
