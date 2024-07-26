package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Service;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.GenericException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface ServicioPortIn {
    Service consultarServicioPorId(int id) throws SearchItemNotFoundException;
    List<Service> consultarServicios();


    Service registrarServicio(Service service) throws SearchItemNotFoundException, ItemAlreadyExistException, GenericException;


    Service actualizarServicioHabitacionOcupada(Service service) throws SearchItemNotFoundException, GenericException;
    Service actualizarServicioParaCerrarServicio(Service service) throws SearchItemNotFoundException, GenericException;
    Service actualizarHabitacionServicio(int service, int numeroHabitacion) throws SearchItemNotFoundException;
    Service actualizarTarifaServicio(int service,int idTarifa) throws SearchItemNotFoundException;

    Service cerrarServicioPorIdServicio(int idService) throws SearchItemNotFoundException, GenericException;


    Service ampliarServicio(Service service, int dia, int hora, int minutos) throws SearchItemNotFoundException, GenericException;

    Service pagarServicio(int idService) throws SearchItemNotFoundException;
}
