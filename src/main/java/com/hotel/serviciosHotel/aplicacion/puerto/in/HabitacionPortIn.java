package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Room;

import java.util.ArrayList;

public interface HabitacionPortIn {
    public Room createRoom(Room room);
    public ArrayList<Room> getRooms();
    public Room getRoomByNumber(Integer number);
    public Room getRoomById(Integer id);
    public Room updateRoom(Room room);
    public Room changeRoomType(int room, int type);
    public Room changeRoomStatus(int room, int state);

    public boolean deleteRoomById(int idRoom);
    public boolean deleteRoom(Room room);
}
