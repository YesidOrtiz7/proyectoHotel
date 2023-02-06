package com.hotel.serviciosHotel.adaptador.out.db.persistence.room;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.EstadosHabitacion;
import org.springframework.data.repository.CrudRepository;

public interface EstadoHabCrudRepository extends CrudRepository<EstadosHabitacion,Integer> {
    EstadosHabitacion findByIdEstado(Integer id);
}
