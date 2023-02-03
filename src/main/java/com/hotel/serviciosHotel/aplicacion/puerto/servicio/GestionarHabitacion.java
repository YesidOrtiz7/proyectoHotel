package com.hotel.serviciosHotel.aplicacion.puerto.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.GestionarHabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.RoomPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GestionarHabitacion implements GestionarHabitacionPortIn {
    @Autowired
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
    public Room getRoomByNumber(Integer number) {
        Optional<Room> room=portOut.getRoomByNumber(number);
        return room.isEmpty()||room==null?null:room.get();
    }

    @Override
    public Room updateRoom(Room room) {
        Room response=portOut.updateRoom(room);
        return response.equals(null)?null:response;
    }
}
