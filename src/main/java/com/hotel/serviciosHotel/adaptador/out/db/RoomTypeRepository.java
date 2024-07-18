package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.room.MapperRoomType;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.room.TipoHabCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.TipoHabitacion;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.RoomTypePortOut;
import com.hotel.serviciosHotel.dominio.entidades.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomTypeRepository implements RoomTypePortOut {
    @Autowired
    private TipoHabCrudRepository repository;
    @Autowired
    private MapperRoomType mapper;


    @Override
    public RoomType registrarTipo(RoomType type) {
        if (type.getRoomTypeDescription()!=null && !type.getRoomTypeDescription().isBlank()){
            return mapper.toRoomType(
                    repository.save(
                            mapper.toTipoHabitacion(type)
                    )
            );
        }else {
            return null;
        }
    }

    @Override
    public List<RoomType> obtenerTipos() {
        Iterable<TipoHabitacion> query=repository.findAll();
        List<RoomType> response=new ArrayList<>();
        for (TipoHabitacion tipo:query){
            response.add(
                    mapper.toRoomType(tipo)
            );
        }
        return response;
    }

    @Override
    public Optional<RoomType> obtenerTipoPorId(int id) {
        Optional<TipoHabitacion> query=repository.findById(id);
        if (query==null || query.isEmpty()){
            return Optional.empty();
        }else {
            return Optional.of(
                    mapper.toRoomType(query.get())
            );
        }
    }

    @Override
    public RoomType actualizarTipo(RoomType type) {
        if (repository.existsById(type.getIdRoomType())){
            return mapper.toRoomType(
                    repository.save(
                            mapper.toTipoHabitacion(type)
                    )
            );
        }else {
            return null;
        }
    }

    @Override
    public boolean eliminarTipo(RoomType type) {
        if (repository.existsById(type.getIdRoomType())){
            repository.delete(
                    mapper.toTipoHabitacion(type)
            );
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean eliminarTipoPorId(int idType) {
        if (repository.existsById(idType)){
            repository.deleteById(idType);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean tipoExistePorId(int idType) {
        return repository.existsById(idType);
    }
}
