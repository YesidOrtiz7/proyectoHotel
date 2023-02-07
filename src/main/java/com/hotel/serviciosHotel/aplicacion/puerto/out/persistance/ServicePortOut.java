package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.Service;

import java.util.List;

public interface ServicePortOut {
    Service consultarServicioPorId(int id);
    List<Service> consultarServicios();
    Service registrarServicio(Service service);
    Service actualizarServicio(Service service);
}
