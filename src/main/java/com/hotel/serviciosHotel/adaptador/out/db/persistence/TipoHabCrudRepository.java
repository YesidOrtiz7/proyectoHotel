package com.hotel.serviciosHotel.adaptador.out.db.persistence;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.TipoHabitacion;
import org.springframework.data.repository.CrudRepository;

public interface TipoHabCrudRepository extends CrudRepository<TipoHabitacion,Integer> {
    TipoHabitacion findByIdTipoHabitacion(Integer id);
}
