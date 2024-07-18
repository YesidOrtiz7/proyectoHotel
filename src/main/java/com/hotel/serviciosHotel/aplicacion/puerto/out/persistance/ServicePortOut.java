package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.Service;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;

import java.util.List;

public interface ServicePortOut {
    Service consultarServicioPorId(int id);
    List<Service> consultarServicios();
    Service registrarServicio(Service service) throws ItemAlreadyExistException;
    Service actualizarServicio(Service service);

    boolean servicioExiste(Service service);
}
