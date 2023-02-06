package com.hotel.serviciosHotel.adaptador.out.db.persistence;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.TipoTarifa;
import org.springframework.data.repository.CrudRepository;

public interface TarifasCrudRepository extends CrudRepository<TipoTarifa,Integer> {
}
