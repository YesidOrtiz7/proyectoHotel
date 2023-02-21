package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.room.MapperRoomStatus;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.room.EstadoHabCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.EstadosHabitacion;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.RoomStatePortOut;
import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomStatusRepository implements RoomStatePortOut {
    @Autowired
    private EstadoHabCrudRepository repository;
    @Autowired
    private MapperRoomStatus mapper;

    @Override
    public RoomStatus registrarEstado(RoomStatus status) {
        if(status.getIdStatus()==0 && status.getStatusName() != null && !status.getStatusName().isBlank()){
            return mapper.toRoomStatus(
                    repository.save(
                            mapper.toEstadosHabitacion(status)
                    )
            );
        }else {
            return null;
        }

    }

    @Override
    public List<RoomStatus> obtenerEstados() {
        Iterable<EstadosHabitacion> query=repository.findAll();
        List<RoomStatus> response=new ArrayList<>();
        for (EstadosHabitacion estado:query){
            response.add(
                    mapper.toRoomStatus(estado)
            );
        }
        return response;
    }

    @Override
    public Optional<RoomStatus> obtenerEstadoPorId(int id) {
        Optional<EstadosHabitacion> query=repository.findById(id);
        if (query==null||query.isEmpty()){
            return Optional.empty();
        }else {
            return Optional.of(
                    mapper.toRoomStatus(query.get())
            );
        }
    }

    @Override
    public RoomStatus actualizarEstado(RoomStatus state) {
        if (repository.existsById(state.getIdStatus())){
            return mapper.toRoomStatus(
                    repository.save(
                            mapper.toEstadosHabitacion(state)
                    )
            );
        }else {
            return null;
        }
    }

    @Override
    public boolean eliminarEstado(RoomStatus state) {
        if (repository.existsById(state.getIdStatus())){
            repository.delete(
                    mapper.toEstadosHabitacion(state)
            );
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean eliminarEstadoPorId(int idState) {
        if (repository.existsById(idState)){
            repository.deleteById(idState);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean estadoExistePorId(int idState) {
        return repository.existsById(idState);
    }
}
