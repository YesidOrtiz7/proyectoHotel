package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.RoomHistory;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface RoomHistoryPortIn {
    //RoomHistory getHistoryById(int id) throws SearchItemNotFoundException;
    List<RoomHistory> getAllHistory();
    List<RoomHistory> getHistoryByIdService(int id);
    RoomHistory saveHistory(RoomHistory historial) throws ItemAlreadyExistException;
    RoomHistory updateHistory(RoomHistory historial) throws SearchItemNotFoundException;
    boolean historyExist(int idHistorial);
}