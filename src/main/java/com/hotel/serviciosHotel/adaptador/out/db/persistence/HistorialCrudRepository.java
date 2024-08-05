package com.hotel.serviciosHotel.adaptador.out.db.persistence;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.HistorialHabitaciones;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistorialCrudRepository extends CrudRepository<HistorialHabitaciones,Integer> {
    @Query("SELECT h FROM HistorialHabitaciones h WHERE h.idServicio.idServicio = :idServicio")
    List<HistorialHabitaciones> findAllByServicioId(@Param("idServicio") int idServicio);
}
