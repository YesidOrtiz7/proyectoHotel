package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.room.MapperRoomStatus;
import com.hotel.serviciosHotel.adaptador.out.db.mappers.room.MapperRoomType;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.room.EstadoHabCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.room.HabitacionCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.mappers.room.MapperRoom;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.room.TipoHabCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Habitacion;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.RoomPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Room;
import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;
import com.hotel.serviciosHotel.dominio.entidades.RoomType;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class RoomRepository implements RoomPortOut {
    private HabitacionCrudRepository repository;
    private TipoHabCrudRepository repositoryTipoHab;
    private EstadoHabCrudRepository repositoryEstadoHab;
    private MapperRoom mapper;
    private MapperRoomType mapperRoomType;
    private MapperRoomStatus mapperRoomStatus;

    /*--------------------------------------------------------------------*/
    @Autowired
    public void setRepository(HabitacionCrudRepository repository) {
        this.repository = repository;
    }
    @Autowired
    public void setRepositoryTipoHab(TipoHabCrudRepository repositoryTipoHab) {
        this.repositoryTipoHab = repositoryTipoHab;
    }
    @Autowired
    public void setRepositoryEstadoHab(EstadoHabCrudRepository repositoryEstadoHab) {
        this.repositoryEstadoHab = repositoryEstadoHab;
    }
    @Autowired
    public void setMapper(MapperRoom mapper) {
        this.mapper = mapper;
    }
    @Autowired
    public void setMapperRoomType(MapperRoomType mapperRoomType) {
        this.mapperRoomType = mapperRoomType;
    }
    @Autowired
    public void setMapperRoomStatus(MapperRoomStatus mapperRoomStatus) {
        this.mapperRoomStatus = mapperRoomStatus;
    }
    /*--------------------------------------------------------------------*/

    @Override
    public boolean roomExistById(int id) {
        return repository.existsById(id);
    }

    @Override
    public Room saveRoom(Room room) throws ItemAlreadyExistException {
        if (!repository.existsById(room.getIdRoom())){
            Habitacion hab = mapper.toHabitacion(room);
            return mapper.toRoom(
                    repository.save(hab)
            );
        }else {
            throw new ItemAlreadyExistException("la habitacion con el id "+room.getIdRoom()+
                    " ya existe");
        }
    }

    @Override
    public Room updateRoom(Room room) throws SearchItemNotFoundException {
        if (repository.existsById(room.getIdRoom())){
            Habitacion hab = mapper.toHabitacion(room);
            return mapper.toRoom(
                    repository.save(hab)
            );
        }else {
            throw new SearchItemNotFoundException("la habitacion con el id "+room.getIdRoom()+
                    " no existe");
        }

    }

    @Override
    public Room getRoomByNumber(Integer number) throws SearchItemNotFoundException {
        Optional<Habitacion> habitacion=repository.findBynumHabitacion(number);
        if (habitacion.isPresent()){
            return mapper.toRoom(habitacion.get());
        }
        throw new SearchItemNotFoundException("la habitacion con el numero "+number+
                " no existe");
    }

    @Override
    public Room getRoomById(Integer id) throws SearchItemNotFoundException {
        Optional<Habitacion> habitacion=repository.findById(id);
        if (habitacion.isPresent()){
            return mapper.toRoom(habitacion.get());
        }
        throw new SearchItemNotFoundException("la habitacion con el id "+id+
                " no existe");
    }

    @Override
    public ArrayList<Room> getRooms() {
        Iterable<Habitacion> habitaciones= repository.findAll();
        //System.out.println(habitaciones);
        ArrayList<Room>rooms=new ArrayList<>();
        for (Habitacion hab:habitaciones){
            rooms.add(mapper.toRoom(hab));
        }
        return rooms;
    }

    @Override
    public boolean deleteRoomById(Integer id) throws SearchItemNotFoundException {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        throw new SearchItemNotFoundException("la habitacion con el id "+id+
                " no existe");
    }

    @Override
    public boolean deleteRoom(Room room) throws SearchItemNotFoundException {
        if (repository.existsById(room.getIdRoom())){
            repository.delete(
                    mapper.toHabitacion(room)
            );
            return true;
        }
        throw new SearchItemNotFoundException("La habitacion con el id "+room.getIdRoom()+
                " no existe");
    }

    @Override
    public Room changeRoomType(int idroom, int idtype) throws SearchItemNotFoundException {
        Room roomById=this.getRoomById(idroom);

        if (repositoryTipoHab.existsById(idtype)){
            RoomType type=mapperRoomType.toRoomType(
                    repositoryTipoHab.findByIdTipoHabitacion(idtype)
            );
            roomById.setRoomType(type);
            return this.updateRoom(roomById);
        }
        throw new SearchItemNotFoundException("El estado con el id "+idtype+
                " no existe");
    }

    @Override
    public Room changeStateRoom(int idroom, int idstate) throws SearchItemNotFoundException {
        Room roomById=this.getRoomById(idroom);

        if (repositoryEstadoHab.existsById(idstate)){
            RoomStatus roomStatus=mapperRoomStatus.toRoomStatus(
                    repositoryEstadoHab.findByIdEstado(idstate)
            );
            roomById.setIdRoomStatus(roomStatus);
            return this.updateRoom(roomById);
        }
        throw new SearchItemNotFoundException("el estado con el id "+idstate+
                " no existe");
    }

    @Override
    public boolean roomTypeExistById(int id) {
        return repositoryTipoHab.existsById(id);
    }

    @Override
    public boolean roomStatusExistById(int id) {
        return repositoryEstadoHab.existsById(id);
    }
}
