package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.HabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.RoomPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Room;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class HabitacionService implements HabitacionPortIn {
    private RoomPortOut portOut;
    @Override
    public Room createRoom(Room room) {
        return portOut.saveRoom(room);
    }

    @Override
    public ArrayList<Room> getRooms() {
        return portOut.getRooms();
    }

    @Override
    public Room getRoomByNumber(Integer number) throws SearchItemNotFoundException {
        Optional<Room> room=portOut.getRoomByNumber(number);
        if (room!=null){
            return room.get();
        }else {
            throw new SearchItemNotFoundException("la habitacion con el numero "+number+" no existe");
        }
    }

    @Override
    public Room getRoomById(Integer id) throws SearchItemNotFoundException {
        Optional<Room> room=portOut.getRoomById(id);
        if (room!=null){
            return room.get();
        }else {
            throw new SearchItemNotFoundException("la habitacion con el id "+id+" no existe");
        }
    }

    @Override
    public Room updateRoom(Room room) {
        Room response=portOut.updateRoom(room);
        return response==null?null:response;
    }

    @Override
    public Room changeRoomType(int room, int type) {
        return portOut.changeRoomType(room,type);
    }

    @Override
    public Room changeRoomStatus(int room, int state) {
        return portOut.changeStateRoom(room,state);
    }

    @Override
    public boolean deleteRoomById(int idRoom) {
        return portOut.deleteRoomById(idRoom);
    }

    @Override
    public boolean deleteRoom(Room room) {
        return portOut.deleteRoom(room);
    }

    @Autowired
    public void setPortOut(RoomPortOut portOut) {
        this.portOut = portOut;
    }
}
