package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Room;
import com.hotel.serviciosHotel.dominio.entidades.Service;

import java.util.List;

public interface ServicioPortIn {
    Service consultarServicioPorId(int id);
    List<Service> consultarServicios();
    Service registrarServicio(Service service);
    Service actualizarServicio(Service service);
    Service actualizarHabitacionServicio(Service service, int numeroHabitacion);
    Service actualizarTarifaServicio(Service service,int idTarifa);
    Service cerrarServicio(Service service);
    Service ampliarServicio(Service service);
}
