package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ReceptionistPortOut {
    boolean receptionistExist(int id);
    ReceptionistEntity saveRecepcionist(ReceptionistEntity receptionist) throws ItemAlreadyExistException;
    ReceptionistEntity updateRecepcionist(ReceptionistEntity receptionist) throws SearchItemNotFoundException;
    ReceptionistEntity getRecepcionistById(Integer id) throws SearchItemNotFoundException;
    ReceptionistEntity getRecepcionistByDocument(String document) throws SearchItemNotFoundException;
    ArrayList<ReceptionistEntity> getRecepcionist();
    boolean deleteRecepcionistById(Integer id) throws SearchItemNotFoundException;
    boolean deleteRecepcionist(ReceptionistEntity receptionist) throws SearchItemNotFoundException;
}
