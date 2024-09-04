package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.HabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.RoomPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Room;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HabitacionService implements HabitacionPortIn {
    private RoomPortOut portOut;
    /*---------------------------------inyeccion de dependencias--------------------------------------*/
    @Autowired
    public void setPortOut(RoomPortOut portOut) {
        this.portOut = portOut;
    }
    /*---------------------------------metodos de la clase--------------------------------------*/

    @Override
    public boolean roomExist(int id) {
        return portOut.roomExistById(id);
    }

    @Override
    public Room createRoom(Room room) throws ItemAlreadyExistException {
        if (portOut.roomExistById(room.getIdRoom())){
            throw new ItemAlreadyExistException("la habitacion con el id "+room.getIdRoom()+
                    " ya existe");
        }
        return portOut.saveRoom(room);
    }

    @Override
    public ArrayList<Room> getRooms() {
        return portOut.getRooms();
    }

    @Override
    public Room getRoomByNumber(Integer number) throws SearchItemNotFoundException {
        return portOut.getRoomByNumber(number);
    }

    @Override
    public Room getRoomById(Integer id) throws SearchItemNotFoundException {
        return portOut.getRoomById(id);
    }

    @Override
    public Room updateRoom(Room room) throws SearchItemNotFoundException {
        if (!portOut.roomExistById(room.getIdRoom())){
            throw new SearchItemNotFoundException("la habitacion con el id "+room.getIdRoom()+" no existe");
        }
        return portOut.updateRoom(room);
    }

    @Override
    public Room changeRoomType(int room, int type) throws SearchItemNotFoundException {
        if (!portOut.roomExistById(room)){
            throw new SearchItemNotFoundException("la habitacion con el id "+room+" no existe");
        }
        if (!portOut.roomTypeExistById(type)){
            throw new SearchItemNotFoundException("el tipo de habitacion con el id "+type+" no existe");
        }
        return portOut.changeRoomType(room,type);
    }

    @Override
    public Room changeRoomStatus(int room, int state) throws SearchItemNotFoundException {
        if (!portOut.roomExistById(room)){
            throw new SearchItemNotFoundException("la habitacion con el id "+room+" no existe");
        }
        if (!portOut.roomStatusExistById(state)){
            throw new SearchItemNotFoundException("el estado de habitacion con el id "+state+" no existe");
        }
        return portOut.changeStateRoom(room,state);
    }

    @Override
    public boolean deleteRoomById(int idRoom) throws SearchItemNotFoundException {
        if (!portOut.roomExistById(idRoom)){
            throw new SearchItemNotFoundException("la habitacion con el id "+idRoom+" no existe");
        }
        return portOut.deleteRoomById(idRoom);
    }

    @Override
    public boolean deleteRoom(Room room) throws SearchItemNotFoundException {
        if (!portOut.roomExistById(room.getIdRoom())){
            throw new SearchItemNotFoundException("la habitacion con el id "+room.getIdRoom()+" no existe");
        }
        return portOut.deleteRoom(room);
    }

}
