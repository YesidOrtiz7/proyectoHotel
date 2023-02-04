package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Room;

import java.util.ArrayList;

public interface GestionarHabitacionPortIn {
    public Room createRoom(Room room);
    public ArrayList<Room> getRooms();
    public Room getRoomByNumber(Integer number);
    public Room updateRoom(Room room);
    public Room changeRoomType(int room, int type);
    public Room changeRoomStatus(int room, int state);
}
