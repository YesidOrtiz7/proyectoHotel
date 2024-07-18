package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.MapperPaymentType;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.PaymentRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Habitacion;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.TipoPago;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.PaymentPortOut;
import com.hotel.serviciosHotel.dominio.entidades.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentTypeRepository implements PaymentPortOut {
    @Autowired
    private PaymentRepository repository;

    @Autowired
    private MapperPaymentType mapper;

    public void setMapper(MapperPaymentType mapper) {
        this.mapper = mapper;
    }

    public void setRepository(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public PaymentType consultarTipoPagoPorId(int id) {
        Optional<TipoPago> response=repository.findById(id);
        if (response==null){
            return null;
        }else {
            return mapper.toPaymentType(
                    response.get()
            );
        }
    }

    @Override
    public List<PaymentType> obtenerTipoPagos() {
        Iterable<TipoPago> p=repository.findAll();
        List<PaymentType> response=new ArrayList<>();
        for (TipoPago pago:p){
            response.add(
                    mapper.toPaymentType(pago)
            );
        }
        return response;
    }

    @Override
    public PaymentType guardarTipoPago(PaymentType type) {
        if (type.getIdPago()==0||!repository.existsById(type.getIdPago())){
            TipoPago tipo = mapper.toTipoPago(type);
            return mapper.toPaymentType(
                    repository.save(tipo)
            );
        }else {
            return null;
        }
    }

    @Override
    public boolean eliminarTipoPago(PaymentType type) {
        if (repository.existsById(type.getIdPago())){
            repository.delete(
                    mapper.toTipoPago(type)
            );
            return true;
        }else {
            return false;
        }
    }
}
