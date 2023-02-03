package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Service;

public interface HospedajeInterface {
    public Service generarServicio();
    public boolean cerrarServicio();
    public Service ampliarServicio();
}
