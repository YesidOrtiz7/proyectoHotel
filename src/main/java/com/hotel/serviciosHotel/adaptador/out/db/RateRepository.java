package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.MapperTarifas;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.TarifasCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.TipoTarifa;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.TarifasPortOut;
import com.hotel.serviciosHotel.dominio.entidades.RateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RateRepository implements TarifasPortOut {
    @Autowired
    private TarifasCrudRepository repository;
    @Autowired
    private MapperTarifas mapper;

    @Override
    public RateType saveRate(RateType tarifa) {
        TipoTarifa query=repository.save(mapper.toTarifas(tarifa));
        if (repository.existsById(tarifa.getIdTipoTarifa())){
            return null;
        }else {
            return mapper.toRateType(query);
        }
    }

    @Override
    public List<RateType> getRates() {
        Iterable<TipoTarifa> tarifas=repository.findAll();
        List<RateType> response=new ArrayList<>();
        for (TipoTarifa tarifa:tarifas){
            response.add(
                    mapper.toRateType(tarifa)
            );
        }
        return response;
    }

    @Override
    public Optional<RateType> getRateById(int id) {
        Optional<TipoTarifa> query=repository.findById(id);
        if (query.isEmpty()||query==null){
            return Optional.empty();
        }else {
            return Optional.of(
                    mapper.toRateType(
                            query.get()
                    )
            );
        }
    }

    @Override
    public RateType updateRate(RateType rate) {
        if (repository.existsById(rate.getIdTipoTarifa())){
            TipoTarifa response=mapper.toTarifas(rate);
            return mapper.toRateType(repository.save(response));
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteRate(RateType rate) {
        if (repository.existsById(rate.getIdTipoTarifa())){
            repository.delete(mapper.toTarifas(rate));
            return true;
        }else {
            return false;
        }
    }
}
