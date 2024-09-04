package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.MunicipioPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.MunicipioPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Municipios;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.InvalidCharacterException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService implements MunicipioPortIn {

    private MunicipioPortOut portOut;

    @Autowired
    public void setPortOut(MunicipioPortOut portOut) {
        this.portOut = portOut;
    }

    @Override
    public Municipios registrarMunicipio(Municipios municipios)
            throws InvalidCharacterException, ItemAlreadyExistException {
        if (municipios.getNombreMun().isBlank()){
            throw new InvalidCharacterException("El nombre del municipio no puede estar en blanco");
        }
        return portOut.registrarMunicipio(municipios);
    }

    @Override
    public List<Municipios> obtenerMunicipios() {
        return portOut.obtenerMunicipios();
    }

    @Override
    public Municipios obtenerMunicipioPorId(int id) throws SearchItemNotFoundException {
        return portOut.obtenerMunicipioPorId(id);
    }

    @Override
    public Municipios actualizarMunicipio(Municipios municipio) throws InvalidCharacterException,SearchItemNotFoundException {
        if (municipio.getNombreMun().isBlank()){
            throw new InvalidCharacterException("El nombre del municipio no puede estar en blanco");
        }
        return portOut.actualizarMunicipios(municipio);
    }

    @Override
    public boolean eliminarMunicipio(Municipios municipio)
        throws SearchItemNotFoundException{
        return portOut.eliminarMunicipio(municipio);
    }
}
