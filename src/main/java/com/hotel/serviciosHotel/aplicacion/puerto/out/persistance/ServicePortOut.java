package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.Service;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface ServicePortOut {
    Service consultarServicioPorId(int id) throws SearchItemNotFoundException;
    List<Service> consultarServicios();
    Service registrarServicio(Service service) throws ItemAlreadyExistException;
    Service actualizarServicio(Service service) throws SearchItemNotFoundException;

    boolean servicioExiste(Service service);
}
