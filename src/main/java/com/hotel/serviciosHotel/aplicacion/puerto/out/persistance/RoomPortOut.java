package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.Room;

import java.util.ArrayList;
import java.util.Optional;

public interface RoomPortOut {
    public Room saveRoom(Room room);
    public Room updateRoom(Room room);
    public Optional<Room> getRoomByNumber(Integer number);
    public ArrayList<Room> getRooms();
    public boolean deleteRoomById(Integer id);
    public boolean deleteRoom(Room room);
    public Room changeRoomType(int room,int roomType);
    public Room changeStateRoom(int room,int state);
}
