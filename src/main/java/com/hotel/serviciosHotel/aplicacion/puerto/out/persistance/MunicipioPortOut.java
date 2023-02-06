package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.Municipios;

import java.util.List;
import java.util.Optional;

public interface MunicipioPortOut {
    Municipios registrarMunicipio(Municipios municipio);
    List<Municipios> obtenerMunicipios();
    Optional<Municipios> obtenerMunicipioPorId(int id);
    Municipios actualizarMunicipios(Municipios municipio);
    boolean eliminarMunicipio(Municipios municipios);
}
