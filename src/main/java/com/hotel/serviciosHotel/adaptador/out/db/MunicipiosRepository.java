package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.MapperMunicipios;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.MunicipiosCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Municipios;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.MunicipioPortOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MunicipiosRepository implements MunicipioPortOut {
    @Autowired
    private MunicipiosCrudRepository repository;

    @Autowired
    private MapperMunicipios mapper;

    @Override
    public com.hotel.serviciosHotel.dominio.entidades.Municipios registrarMunicipio(com.hotel.serviciosHotel.dominio.entidades.Municipios municipio) {
        if (municipio.getIdMunicipios()==0&&municipio.getNombreMun()!=null && !municipio.getNombreMun().isBlank()){
            return mapper.toMunicipiosEntidades(
                    repository.save(
                            mapper.toMunicipiosPersistence(municipio)
                    )
            );
        }else {
            return null;
        }

    }

    @Override
    public com.hotel.serviciosHotel.dominio.entidades.Municipios actualizarMunicipios(com.hotel.serviciosHotel.dominio.entidades.Municipios municipio) {
        if (repository.existsById(municipio.getIdMunicipios())){
            return mapper.toMunicipiosEntidades(
                    repository.save(
                            mapper.toMunicipiosPersistence(municipio)
                    )
            );
        }else {
            return null;
        }

    }

    @Override
    public boolean eliminarMunicipio(com.hotel.serviciosHotel.dominio.entidades.Municipios municipios) {
        if (repository.existsById(municipios.getIdMunicipios())){
            repository.delete(
                    mapper.toMunicipiosPersistence(municipios)
            );
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<com.hotel.serviciosHotel.dominio.entidades.Municipios> obtenerMunicipios() {
        Iterable<Municipios> query=repository.findAll();
        List<com.hotel.serviciosHotel.dominio.entidades.Municipios> response=new ArrayList<>();
        for (Municipios municipio:query){
            response.add(
                    mapper.toMunicipiosEntidades(municipio)
            );
        }
        return response;
    }

    @Override
    public Optional<com.hotel.serviciosHotel.dominio.entidades.Municipios> obtenerMunicipioPorId(int id) {
        Optional<Municipios> municipio=repository.findById(id);
        if (municipio==null||municipio.isEmpty()){
            return Optional.empty();
        }else {
            return Optional.of(
                    mapper.toMunicipiosEntidades(municipio.get())
            );
        }
    }
}
