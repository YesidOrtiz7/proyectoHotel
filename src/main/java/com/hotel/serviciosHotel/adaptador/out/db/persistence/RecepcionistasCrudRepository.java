package com.hotel.serviciosHotel.adaptador.out.db.persistence;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Recepcionista;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecepcionistasCrudRepository extends CrudRepository<Recepcionista,Integer> {

    @Override
    Optional<Recepcionista> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<Recepcionista> findAll();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Recepcionista entity);

    Optional<Recepcionista> findByDocRecep(String document);

}
