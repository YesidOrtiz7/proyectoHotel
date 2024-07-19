package com.hotel.serviciosHotel.adaptador.out.db.persistence;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Configuracion;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConfiguracionCrudRepository extends CrudRepository<Configuracion,Integer> {
    @Override
    Optional<Configuracion> findById(Integer id);
    @Override
    boolean existsById(Integer id);
    @Override
    Iterable<Configuracion> findAll();
    @Override
    void deleteById(Integer id);
    @Override
    void delete(Configuracion configuracion);
}
