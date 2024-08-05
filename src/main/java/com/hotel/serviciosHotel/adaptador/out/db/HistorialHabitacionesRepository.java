package com.hotel.serviciosHotel.adaptador.out.db;
import com.hotel.serviciosHotel.adaptador.out.db.mappers.MapperRoomHistory;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.HistorialCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.HistorialHabitaciones;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.HistorialHabitacionesPortOut;
import com.hotel.serviciosHotel.dominio.entidades.RoomHistory;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class HistorialHabitacionesRepository implements HistorialHabitacionesPortOut {
    @Autowired
    private HistorialCrudRepository repository;
    @Autowired
    private MapperRoomHistory mapper;

    @Override
    public RoomHistory consultarHistorialPorId(int id) throws SearchItemNotFoundException {
        Optional<HistorialHabitaciones> response=repository.findById(id);
        if (response.isEmpty()||response==null){
            throw new  SearchItemNotFoundException("El objeto historial con el id "+id+" no existe");
        }else {
            return mapper.toHistory(response.get());
        }
    }

    @Override
    public List<RoomHistory> consultarHistorialPorIdService(int id) {
        List<HistorialHabitaciones> query=repository.findAllByServicioId(id);
        List<RoomHistory> response=new ArrayList<>();
        query.forEach(i->response.add(mapper.toHistory(i)));
        return response;
    }

    @Override
    public List<RoomHistory> consultarHistorial() {
        Iterable<HistorialHabitaciones> query=repository.findAll();
        List<RoomHistory> response=new ArrayList<>();
        query.forEach(i ->response.add(mapper.toHistory(i)));
        return response;
    }

    @Override
    public RoomHistory actualizarHistorial(RoomHistory history) throws SearchItemNotFoundException {
        if (repository.existsById(history.getIdRecord())){
            HistorialHabitaciones response =repository.save(
                    mapper.toHistorial(history)
            );
            return mapper.toHistory(response);
        }else {
            throw new SearchItemNotFoundException("El objeto historial con que esta tratando de actualizar"+
                    " no existe en la base de datos");
        }
    }

    @Override
    public RoomHistory registrarHistorial(RoomHistory historial) throws ItemAlreadyExistException {
        if (repository.existsById(historial.getIdRecord())){
            throw new ItemAlreadyExistException("El objeto historial que esta tratando de registrar"+
                    " ya existe en la base de datos");
        }else{
            HistorialHabitaciones response=repository.save(
                    mapper.toHistorial(historial)
            );
            return mapper.toHistory(response);
        }
    }

    @Override
    public boolean existeHistorial(int idHistorial) {
        return repository.existsById(idHistorial);
    }
}
