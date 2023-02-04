package com.hotel.serviciosHotel.adaptador.out.db.persistence;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientesCrudRepository extends CrudRepository<Cliente,Integer> {

    @Override
    Optional<Cliente> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<Cliente> findAll();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Cliente entity);
    Optional<Cliente> findByDocumentoCliente(String document);

}
