package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.HospedajePortIn;
import com.hotel.serviciosHotel.dominio.entidades.Service;

public class HospedajeService implements HospedajePortIn {
    @Override
    public Service generarServicio() {
        return null;
    }

    @Override
    public boolean cerrarServicio() {
        return false;
    }

    @Override
    public Service ampliarServicio() {
        return null;
    }
}
