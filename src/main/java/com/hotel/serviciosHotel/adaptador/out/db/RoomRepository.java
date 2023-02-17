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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class RoomRepository implements RoomPortOut {
    @Autowired
    private HabitacionCrudRepository repository;
    @Autowired
    private TipoHabCrudRepository repositoryTipoHab;
    @Autowired
    private EstadoHabCrudRepository repositoryEstadoHab;
    @Autowired
    private MapperRoom mapper;

    @Autowired
    private MapperRoomType mapperRoomType;

    @Autowired
    private MapperRoomStatus mapperRoomStatus;

    @Override
    public Room saveRoom(Room room) {
        if (repository.existsById(room.getIdRoom())||room.getIdRoom()==0){
            Habitacion hab = mapper.toHabitacion(room);
            //System.out.println(hab.toString());
            return mapper.toRoom(
                    repository.save(hab)
            );
        }else {
            return null;
        }
    }

    @Override
    public Room updateRoom(Room room) {
        if (repository.existsById(room.getIdRoom())){
            //System.out.println(room.getIdRoom());
            Habitacion hab = mapper.toHabitacion(room);
            return mapper.toRoom(
                    repository.save(hab)
            );
        }else {
            return null;
        }

    }

    @Override
    public Optional<Room> getRoomByNumber(Integer number) {
        Optional<Habitacion> habitacion=repository.findBynumHabitacion(number);
        if (habitacion.isEmpty()){
            return Optional.empty();
        }else {
            return Optional.of(
                    mapper.toRoom(habitacion.get())
            );
        }
    }

    @Override
    public Optional<Room> getRoomById(Integer id) {
        Optional<Habitacion> habitacion=repository.findById(id);
        if (habitacion.isEmpty()){
            return Optional.empty();
        }else {
            return Optional.of(
                    mapper.toRoom(habitacion.get())
            );
        }
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
    public boolean deleteRoomById(Integer id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteRoom(Room room) {
        if (repository.existsById(room.getIdRoom())){
            repository.delete(
                    mapper.toHabitacion(room)
            );
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Room changeRoomType(int idroom, int idState) {
        Optional<Room> roomByNumber=this.getRoomByNumber(idroom);
        Room room=(roomByNumber.isEmpty()||roomByNumber==null)?null:roomByNumber.get();

        if (repositoryTipoHab.existsById(idState)&&room!=null){
            RoomType type=mapperRoomType.toRoomType(
                    repositoryTipoHab.findByIdTipoHabitacion(idState)
            );
            room.setRoomType(type);
            Room roomResponse=this.updateRoom(room);
            return roomResponse;
        }else {
            return null;
        }
    }

    @Override
    public Room changeStateRoom(int idroom, int state) {
        Optional<Room> roomByNumber=this.getRoomByNumber(idroom);
        Room room=(roomByNumber==null||roomByNumber.isEmpty())?null:roomByNumber.get();
        if (repositoryEstadoHab.existsById(state)&&room!=null){
            RoomStatus roomStatus=mapperRoomStatus.toRoomStatus(
                    repositoryEstadoHab.findByIdEstado(state)
            );
            room.setIdRoomStatus(roomStatus);
            Room roomResponse=this.updateRoom(room);
            return roomResponse;
        }else {
            return null;
        }
    }
}
