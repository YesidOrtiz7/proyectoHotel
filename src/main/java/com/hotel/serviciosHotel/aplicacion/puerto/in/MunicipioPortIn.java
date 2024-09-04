package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Municipios;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.InvalidCharacterException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface MunicipioPortIn {
    Municipios registrarMunicipio(Municipios municipios) throws ItemAlreadyExistException, InvalidCharacterException;
    List<Municipios> obtenerMunicipios();
    Municipios obtenerMunicipioPorId(int id) throws SearchItemNotFoundException;
    Municipios actualizarMunicipio(Municipios municipio) throws SearchItemNotFoundException,InvalidCharacterException;
    boolean eliminarMunicipio(Municipios municipio) throws SearchItemNotFoundException;
}
