package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.InvalidCharacterException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface RecepcionistaPortIn {
    boolean existenciaRecepcionista(int id);
    ReceptionistEntity registrarRecepcionista(ReceptionistEntity receptionist) throws ItemAlreadyExistException, InvalidCharacterException;
    ReceptionistEntity actualizarRecepcionista(ReceptionistEntity receptionist) throws SearchItemNotFoundException, InvalidCharacterException;
    ReceptionistEntity obtenerRecepcionistaPorId(Integer id) throws SearchItemNotFoundException;
    ReceptionistEntity obtenerRecepcionistaPorDocumento(String document) throws SearchItemNotFoundException;
    List<ReceptionistEntity> obtenerRecepcionistas();
    boolean eliminarRecepcionistaPorId(Integer id) throws SearchItemNotFoundException;
    boolean eliminarRecepcionista(ReceptionistEntity receptionist) throws SearchItemNotFoundException;
}
