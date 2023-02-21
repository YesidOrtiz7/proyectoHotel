package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;
import com.hotel.serviciosHotel.dominio.entidades.RoomType;

import java.util.List;

public interface EstadoHabitacionPortIn {
    RoomStatus registrarEstadoHabitacion(RoomStatus status);
    List<RoomStatus> obtenerEstadoHabitaciones();
    RoomStatus obtenerEstadoHabitacionPorId(int id);
    RoomStatus actualizarEstadoHabitacion(RoomStatus status);
    boolean eliminarEstadoHabitacion(RoomStatus status);
    boolean eliminarEstadoHabitacion(int idStatus);
}
