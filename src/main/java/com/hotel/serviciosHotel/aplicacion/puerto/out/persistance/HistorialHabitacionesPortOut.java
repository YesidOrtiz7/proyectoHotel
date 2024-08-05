package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.RoomHistory;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface HistorialHabitacionesPortOut {
    RoomHistory consultarHistorialPorId(int id) throws SearchItemNotFoundException;
    List<RoomHistory> consultarHistorialPorIdService(int id);
    List<RoomHistory> consultarHistorial();
    RoomHistory actualizarHistorial(RoomHistory history) throws SearchItemNotFoundException;
    RoomHistory registrarHistorial(RoomHistory history) throws ItemAlreadyExistException;
    boolean existeHistorial(int idHistorial);
}
