package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Municipios;

import java.util.List;

public interface MunicipioPortIn {
    Municipios registrarMunicipio(Municipios municipios);
    List<Municipios> obtenerMunicipios();
    Municipios obtenerMunicipioPorId(int id);
    Municipios actualizarMunicipio(Municipios municipio);
    boolean eliminarMunicipio(Municipios municipio);
}
