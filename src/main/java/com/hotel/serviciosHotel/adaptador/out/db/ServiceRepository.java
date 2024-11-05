package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.MapperService;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.ServiceCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Servicio;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ServicePortOut;
import com.hotel.serviciosHotel.dominio.entidades.Service;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ServiceRepository implements ServicePortOut {
    @Autowired
    private ServiceCrudRepository repository;
    @Autowired
    private MapperService mapper;

    @Override
    public Service consultarServicioPorId(int id) throws SearchItemNotFoundException {
        Optional<Servicio> query=repository.findById(id);
        if (query.isEmpty()){
            throw new SearchItemNotFoundException("el servicio con el id "+id+" no existe");
        }else {
            return mapper.toService(
                    query.get()
            );
        }
    }

    @Override
    public boolean servicioExiste(Service service) {
        return repository.existsById(service.getIdService());
    }

    @Override
    public List<Service> consultarServicios() {
        Iterable<Servicio>query=repository.findAll();
        System.out.println(query);
        List<Service>response=new ArrayList<>();
        for (Servicio q:query){
            response.add(
                    mapper.toService(q)
            );
        }
        return response;
    }

    @Override
    public Service registrarServicio(Service service) throws ItemAlreadyExistException {
        if (repository.existsById(service.getIdService())){
            throw new ItemAlreadyExistException("El servicio que esta tratando de registrar tiene un ID que ya existe en"+
                    " la base de datos");
        }else {
            Servicio query=repository.save(
                    mapper.toServicio(service)
            );
            return mapper.toService(query);
        }
    }

    @Override
    public Service actualizarServicio(Service service) throws SearchItemNotFoundException {
        if (repository.existsById(service.getIdService())){
            Servicio query=repository.save(
                    mapper.toServicio(service)
            );
            return mapper.toService(query);
        }else {
            throw new SearchItemNotFoundException("El servicio no existe");
        }
    }
}
