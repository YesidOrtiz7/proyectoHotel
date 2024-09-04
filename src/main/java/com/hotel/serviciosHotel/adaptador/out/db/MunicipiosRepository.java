package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.MapperMunicipios;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.MunicipiosCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Municipios;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.MunicipioPortOut;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.InvalidCharacterException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
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
    public com.hotel.serviciosHotel.dominio.entidades.Municipios registrarMunicipio(com.hotel.serviciosHotel.dominio.entidades.Municipios municipio)
        throws ItemAlreadyExistException {
        if (repository.existsById(municipio.getIdMunicipios())){
            throw new ItemAlreadyExistException("El municipio ya existe");
        }

        return mapper.toMunicipiosEntidades(
                repository.save(
                        mapper.toMunicipiosPersistence(municipio)
                )
        );

    }

    @Override
    public com.hotel.serviciosHotel.dominio.entidades.Municipios actualizarMunicipios(com.hotel.serviciosHotel.dominio.entidades.Municipios municipio)
        throws SearchItemNotFoundException{

        if (!repository.existsById(municipio.getIdMunicipios())){
            throw new SearchItemNotFoundException("El municipio a actualizar no existe");
        }
        return mapper.toMunicipiosEntidades(
                repository.save(
                        mapper.toMunicipiosPersistence(municipio)
                )
        );

    }

    @Override
    public boolean eliminarMunicipio(com.hotel.serviciosHotel.dominio.entidades.Municipios municipios)
        throws SearchItemNotFoundException{
        if (!repository.existsById(municipios.getIdMunicipios())){
            throw new SearchItemNotFoundException("El municipio no existe");
        }
        repository.delete(
                mapper.toMunicipiosPersistence(municipios)
        );
        return true;
    }

    @Override
    public List<com.hotel.serviciosHotel.dominio.entidades.Municipios> obtenerMunicipios() {
        Iterable<Municipios> query=repository.findAll();
        List<com.hotel.serviciosHotel.dominio.entidades.Municipios> response=new ArrayList<>();
        query.iterator().forEachRemaining((municipio->{response.add(mapper.toMunicipiosEntidades(municipio));}));
        return response;
    }

    @Override
    public com.hotel.serviciosHotel.dominio.entidades.Municipios obtenerMunicipioPorId(int id) throws SearchItemNotFoundException{
        Optional<Municipios> municipio=repository.findById(id);
        if (municipio.isEmpty()){
            throw new SearchItemNotFoundException("El municipio no existe");
        }
        return mapper.toMunicipiosEntidades(municipio.get());
    }
}
