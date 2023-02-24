package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.MunicipioPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.MunicipioPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Municipios;
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
    public Municipios registrarMunicipio(Municipios municipios) {
        return portOut.registrarMunicipio(municipios);
    }

    @Override
    public List<Municipios> obtenerMunicipios() {
        return portOut.obtenerMunicipios();
    }

    @Override
    public Municipios obtenerMunicipioPorId(int id) {
        Optional<Municipios> response=portOut.obtenerMunicipioPorId(id);
        return (response==null)?null:response.get();
    }

    @Override
    public Municipios actualizarMunicipio(Municipios municipio) {
        return portOut.actualizarMunicipios(municipio);
    }

    @Override
    public boolean eliminarMunicipio(Municipios municipio) {
        return portOut.eliminarMunicipio(municipio);
    }
}
