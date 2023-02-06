package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.Receptionist;

import java.util.List;
import java.util.Optional;

public interface ReceptionistPortOut {
    public Receptionist saveRecepcionist(Receptionist receptionist);
    public Receptionist updateRecepcionist(Receptionist receptionist);
    public Optional<Receptionist> getRecepcionistById(Integer id);
    public Optional<Receptionist> getRecepcionistByDocument(String document);
    public List<Receptionist> getRecepcionist();
    public boolean deleteRecepcionistById(Integer id);
    public boolean deleteRecepcionist(Receptionist receptionist);
}
