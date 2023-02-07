package com.hotel.serviciosHotel.adaptador.out.db.persistence;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Servicio;
import org.springframework.data.repository.CrudRepository;

public interface ServiceCrudRepository extends CrudRepository<Servicio,Integer> {
}
