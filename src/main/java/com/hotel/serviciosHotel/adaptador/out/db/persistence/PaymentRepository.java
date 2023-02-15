package com.hotel.serviciosHotel.adaptador.out.db.persistence;

import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.TipoPago;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<TipoPago,Integer> {
}
