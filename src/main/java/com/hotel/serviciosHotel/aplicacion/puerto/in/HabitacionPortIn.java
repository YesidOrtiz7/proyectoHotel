package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Room;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.ArrayList;

public interface HabitacionPortIn {
    public Room createRoom(Room room);
    public ArrayList<Room> getRooms();
    public Room getRoomByNumber(Integer number) throws SearchItemNotFoundException;
    public Room getRoomById(Integer id) throws SearchItemNotFoundException;
    public Room updateRoom(Room room);
    public Room changeRoomType(int room, int type);
    public Room changeRoomStatus(int room, int state);

    public boolean deleteRoomById(int idRoom);
    public boolean deleteRoom(Room room);
}
