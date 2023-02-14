package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.adaptador.out.db.RoomRepository;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.RoomPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Room;
import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;
import com.hotel.serviciosHotel.dominio.entidades.RoomType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HabitacionServiceTest {
    private RoomPortOut portOutMock;

    private Room roomMock1, roomMock2;
    private ArrayList<Room> listRoomMock;
    private HabitacionService service;

    @BeforeEach
    void setUp() {
        portOutMock= Mockito.mock(RoomRepository.class);

        roomMock1=new Room(301,
                new RoomStatus(1,"limpia"),
                new RoomType(1,"doble"),
                10000,
                2,1);

        roomMock2=new Room(302,
                new RoomStatus(1,"limpia"),
                new RoomType(1,"doble"),
                10000,
                2,2);

        listRoomMock=new ArrayList<>();
        listRoomMock.add(roomMock1);
        listRoomMock.add(roomMock2);

        service=new HabitacionService();
        service.setPortOut(portOutMock);
    }

    @Test
    void createRoom() {
        Mockito.when(portOutMock.saveRoom(roomMock1)).thenReturn(roomMock1);

        Assertions.assertEquals(roomMock1, service.createRoom(roomMock1));
    }

    @Test
    void getRooms() {
        Mockito.when(portOutMock.getRooms()).thenReturn(listRoomMock);

        Assertions.assertEquals(listRoomMock, service.getRooms());
    }

    @Test
    void getRoomByNumber() {
        Mockito.when(portOutMock.getRoomByNumber(301)).thenReturn(Optional.of(roomMock1));

        Assertions.assertEquals(roomMock1, service.getRoomByNumber(301));
    }
    @Test
    void getRoomByNumberNull() {
        Mockito.when(portOutMock.getRoomByNumber(301)).thenReturn(null);

        Assertions.assertEquals(null, service.getRoomByNumber(301));
    }
    @Test
    void getRoomByNumberEmpty() {
        Mockito.when(portOutMock.getRoomByNumber(301)).thenReturn(Optional.empty());

        Assertions.assertEquals(null, service.getRoomByNumber(301));
    }

    @Test
    void getRoomById() {
        Mockito.when(portOutMock.getRoomById(1)).thenReturn(Optional.of(roomMock1));

        Assertions.assertEquals(roomMock1, service.getRoomById(1));
    }
    @Test
    void getRoomByIdNull() {
        Mockito.when(portOutMock.getRoomById(1)).thenReturn(null);

        Assertions.assertEquals(null, service.getRoomById(1));
    }
    @Test
    void getRoomByIdEmpty() {
        Mockito.when(portOutMock.getRoomById(1)).thenReturn(Optional.empty());

        Assertions.assertEquals(null, service.getRoomById(1));
    }

    @Test
    void updateRoom() {
        Mockito.when(portOutMock.updateRoom(roomMock1)).thenReturn(roomMock1);
        Assertions.assertEquals(roomMock1, service.updateRoom(roomMock1));
    }
    @Test
    void updateRoomNull() {
        Mockito.when(portOutMock.updateRoom(roomMock1)).thenReturn(null);
        Assertions.assertEquals(null, service.updateRoom(roomMock1));
    }

    @Test
    void changeRoomType() {
        Mockito.when(portOutMock.changeRoomType(301,2)).thenReturn(roomMock1);
        Assertions.assertEquals(roomMock1, service.changeRoomType(301,2));
    }

    @Test
    void changeRoomStatus() {
        Mockito.when(portOutMock.changeStateRoom(301,2)).thenReturn(roomMock1);
        Assertions.assertEquals(roomMock1, service.changeRoomStatus(301,2));
    }

    @Test
    void deleteRoomById() {
        Mockito.when(portOutMock.deleteRoomById(1)).thenReturn(true);
        Assertions.assertEquals(true,service.deleteRoomById(1));
    }

    @Test
    void deleteRoom() {
        Mockito.when(portOutMock.deleteRoom(roomMock2)).thenReturn(true);
        Assertions.assertEquals(true,service.deleteRoom(roomMock2));
    }
}