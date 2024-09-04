package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.Room;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.ArrayList;

public interface RoomPortOut {
    boolean roomExistById(int id);
    boolean roomTypeExistById(int id);
    boolean roomStatusExistById(int id);
    Room saveRoom(Room room) throws ItemAlreadyExistException;
    Room updateRoom(Room room) throws SearchItemNotFoundException;
    public Room getRoomByNumber(Integer number) throws SearchItemNotFoundException;
    public Room getRoomById(Integer id) throws SearchItemNotFoundException;
    public ArrayList<Room> getRooms();
    public boolean deleteRoomById(Integer id) throws SearchItemNotFoundException;
    public boolean deleteRoom(Room room) throws SearchItemNotFoundException;
    public Room changeRoomType(int room,int roomType) throws SearchItemNotFoundException;
    public Room changeStateRoom(int room,int state) throws SearchItemNotFoundException;
}
