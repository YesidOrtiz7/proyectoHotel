package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;

import java.util.List;
import java.util.Optional;

public interface ReceptionistPortOut {
    public ReceptionistEntity saveRecepcionist(ReceptionistEntity receptionist);
    public ReceptionistEntity updateRecepcionist(ReceptionistEntity receptionist);
    public Optional<ReceptionistEntity> getRecepcionistById(Integer id);
    public Optional<ReceptionistEntity> getRecepcionistByDocument(String document);
    public List<ReceptionistEntity> getRecepcionist();
    public boolean deleteRecepcionistById(Integer id);
    public boolean deleteRecepcionist(ReceptionistEntity receptionist);
}
