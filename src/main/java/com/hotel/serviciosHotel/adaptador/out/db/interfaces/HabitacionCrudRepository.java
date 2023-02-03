package com.hotel.serviciosHotel.adaptador.out.db.interfaces;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Habitacion;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HabitacionCrudRepository extends CrudRepository<Habitacion,Integer> {
    Optional<Habitacion> findBynumHabitacion(Integer numHabitacion);

    @Override
    Optional<Habitacion> findById(Integer id);

    @Override
    boolean existsById(Integer id);

    @Override
    Iterable<Habitacion> findAll();

    @Override
    void deleteById(Integer id);

    @Override
    void delete(Habitacion habitacion);

}
