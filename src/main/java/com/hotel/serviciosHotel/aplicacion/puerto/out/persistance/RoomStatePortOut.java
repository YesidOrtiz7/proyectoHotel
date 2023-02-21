package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;


import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;

import java.util.List;
import java.util.Optional;

public interface RoomStatePortOut {
    RoomStatus registrarEstado(RoomStatus status);
    List<RoomStatus> obtenerEstados();
    Optional<RoomStatus> obtenerEstadoPorId(int id);
    RoomStatus actualizarEstado(RoomStatus state);
    boolean eliminarEstado(RoomStatus state);
    boolean eliminarEstadoPorId(int idState);
    boolean estadoExistePorId(int idState);
}
