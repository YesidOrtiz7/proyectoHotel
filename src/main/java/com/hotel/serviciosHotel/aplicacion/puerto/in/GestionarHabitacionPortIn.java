package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Room;

import java.util.ArrayList;
import java.util.List;

public interface GestionarHabitacionPortIn {
    public Room createRoom(Room room);
    public ArrayList<Room> getRooms();
    public Room getRoomByNumber(Integer number);
    public Room updateRoom(Room room);
}
