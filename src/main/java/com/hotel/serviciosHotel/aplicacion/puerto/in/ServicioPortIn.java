package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Service;

import java.util.List;

public interface ServicioPortIn {
    Service consultarServicioPorId(int id);
    List<Service> consultarServicios();


    Service registrarServicio(Service service);


    Service actualizarServicioHabitacionOcupada(Service service);
    Service actualizarServicioParaCerrarServicio(Service service);
    Service actualizarHabitacionServicio(int service, int numeroHabitacion);
    Service actualizarTarifaServicio(int service,int idTarifa);

    Service cerrarServicio(Service service);
    Service cerrarServicioPorIdServicio(int idService);


    Service ampliarServicio(Service service, int dia, int hora, int minutos);
}
