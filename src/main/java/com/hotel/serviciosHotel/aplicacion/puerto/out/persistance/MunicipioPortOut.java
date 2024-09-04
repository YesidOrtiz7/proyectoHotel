package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.Municipios;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;
import java.util.Optional;

public interface MunicipioPortOut {
    Municipios registrarMunicipio(Municipios municipio) throws ItemAlreadyExistException;
    List<Municipios> obtenerMunicipios();
    Municipios obtenerMunicipioPorId(int id) throws SearchItemNotFoundException;
    Municipios actualizarMunicipios(Municipios municipio) throws SearchItemNotFoundException;
    boolean eliminarMunicipio(Municipios municipios) throws SearchItemNotFoundException;
}
