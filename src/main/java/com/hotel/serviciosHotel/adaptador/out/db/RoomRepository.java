package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.interfaces.HabitacionCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.mappers.room.MapperRoom;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Habitacion;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.RoomPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class RoomRepository implements RoomPortOut {
    @Autowired
    private HabitacionCrudRepository repository;
    @Autowired
    private MapperRoom mapper;
    @Override
    public Room saveRoom(Room room) {
        if (repository.existsById(room.getIdRoom())||room.getIdRoom()==0){
            Habitacion hab = mapper.toHabitacion(room);
            System.out.println(hab.getIdEstHab().getIdEstado());
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
            System.out.println(room.getIdRoom());
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
    public ArrayList<Room> getRooms() {
        Iterable<Habitacion> habitaciones= repository.findAll();
        System.out.println(habitaciones);
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
}
