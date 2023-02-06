package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Receptionist;

import java.util.List;
import java.util.Optional;

public interface RecepcionistaPortIn {
    public Receptionist registrarRecepcionista(Receptionist receptionist);
    public Receptionist actualizarRecepcionista(Receptionist receptionist);
    public Receptionist obtenerRecepcionistaPorId(Integer id);
    public Receptionist obtenerRecepcionistaPorDocumento(String document);
    public List<Receptionist> obtenerRecepcionistas();
    public boolean eliminarRecepcionistaPorId(Integer id);
    public boolean eliminarRecepcionista(Receptionist receptionist);
}
