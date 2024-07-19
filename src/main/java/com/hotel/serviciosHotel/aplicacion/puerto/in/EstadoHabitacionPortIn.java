package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;
import com.hotel.serviciosHotel.dominio.entidades.RoomType;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface EstadoHabitacionPortIn {
    RoomStatus registrarEstadoHabitacion(RoomStatus status);
    List<RoomStatus> obtenerEstadoHabitaciones();
    RoomStatus obtenerEstadoHabitacionPorId(int id) throws SearchItemNotFoundException;
    RoomStatus actualizarEstadoHabitacion(RoomStatus status) throws SearchItemNotFoundException;
    boolean eliminarEstadoHabitacion(RoomStatus status);
    boolean eliminarEstadoHabitacion(int idStatus);
}
