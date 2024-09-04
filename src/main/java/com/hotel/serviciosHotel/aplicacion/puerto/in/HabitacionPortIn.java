package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Room;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.ArrayList;

public interface HabitacionPortIn {
    public boolean roomExist(int id);
    public Room createRoom(Room room) throws ItemAlreadyExistException;
    public ArrayList<Room> getRooms();
    public Room getRoomByNumber(Integer number) throws SearchItemNotFoundException;
    public Room getRoomById(Integer id) throws SearchItemNotFoundException;
    public Room updateRoom(Room room) throws SearchItemNotFoundException;
    public Room changeRoomType(int room, int type) throws SearchItemNotFoundException;
    public Room changeRoomStatus(int room, int state) throws SearchItemNotFoundException;

    public boolean deleteRoomById(int idRoom) throws SearchItemNotFoundException;
    public boolean deleteRoom(Room room) throws SearchItemNotFoundException;
}
