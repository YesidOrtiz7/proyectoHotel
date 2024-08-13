package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.HabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.servicio.HabitacionService;
import com.hotel.serviciosHotel.dominio.entidades.Room;
import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;
import com.hotel.serviciosHotel.dominio.entidades.RoomType;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionarHabitacionesControllerTest {
    private GestionarHabitacionesController controller;
    private HabitacionPortIn portInMock;
    private Room roomMock1,roomMock2;
    private List<Room> listRoomMock=new ArrayList<>();
    @BeforeEach
    void setUp() {
        portInMock= Mockito.mock(HabitacionService.class);

        roomMock1=new Room(
                302,
                new RoomStatus(2,
                        "ocupada",
                        false,
                        true,
                        false),
                new RoomType(1,"doble"),
                100000,
                4,
                1
                );
        roomMock2=new Room(
                301,
                new RoomStatus(1,
                        "limpia",
                        false,
                        true,
                        false),
                new RoomType(1,"doble"),
                1000,
                4,
                2);
        listRoomMock.add(roomMock1);
        listRoomMock.add(roomMock2);

        controller=new GestionarHabitacionesController();
        controller.setService(portInMock);
    }

    @Test
    void obtenerHabitacionPorNumero() throws SearchItemNotFoundException {
        Mockito.when(portInMock.getRoomByNumber(301)).thenReturn(roomMock1);

        Assertions.assertEquals(new ResponseEntity<>(roomMock1,HttpStatus.OK), controller.obtenerHabitacionPorNumero(301));
    }

    @Test
    void obtenerHabitacionPorId() throws SearchItemNotFoundException {
        Mockito.when(portInMock.getRoomById(1)).thenReturn(roomMock1);

        Assertions.assertEquals(new ResponseEntity<>(roomMock1,HttpStatus.OK), controller.obtenerHabitacionPorId(1));
    }

    @Test
    void obtenerHabitaciones() {
        Mockito.when(portInMock.getRooms()).thenReturn((ArrayList<Room>) listRoomMock);

        Assertions.assertEquals(new ResponseEntity<>(listRoomMock,HttpStatus.OK), controller.obtenerHabitaciones());
    }
    @Test
    void obtenerHabitacionesNull() {
        Mockito.when(portInMock.getRooms()).thenReturn(null);

        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), controller.obtenerHabitaciones());
    }

    @Test
    void registrarNuevaHabitacion() {
        Mockito.when(portInMock.createRoom(roomMock2)).thenReturn(roomMock2);

        Assertions.assertEquals(new ResponseEntity<>(roomMock2,HttpStatus.CREATED),controller.registrarNuevaHabitacion(roomMock2));
    }
    @Test
    void registrarNuevaHabitacionNull() {
        Mockito.when(portInMock.createRoom(roomMock2)).thenReturn(null);

        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.registrarNuevaHabitacion(roomMock2));
    }

    @Test
    void actualizarHabitacion() {
        Mockito.when(portInMock.updateRoom(roomMock2)).thenReturn(roomMock2);

        Assertions.assertEquals(new ResponseEntity<>(roomMock2,HttpStatus.OK),controller.actualizarHabitacion(roomMock2));
    }
    @Test
    void actualizarHabitacionNull() {
        Mockito.when(portInMock.updateRoom(roomMock2)).thenReturn(null);

        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.actualizarHabitacion(roomMock2));
    }

    @Test
    void cambiarTipoHabitacion() {
        Mockito.when(portInMock.changeRoomType(1,3)).thenReturn(roomMock1);

        Assertions.assertEquals(new ResponseEntity<>(roomMock1,HttpStatus.OK),controller.cambiarTipoHabitacion(3,1));
    }
    @Test
    void cambiarTipoHabitacionNull() {
        Mockito.when(portInMock.changeRoomType(1,3)).thenReturn(null);

        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.cambiarTipoHabitacion(3,1));
    }

    @Test
    void cambiarEstadoHabitacion() {
        Mockito.when(portInMock.changeRoomStatus(301,1)).thenReturn(roomMock1);

        Assertions.assertEquals(new ResponseEntity<>(roomMock1,HttpStatus.OK),controller.cambiarEstadoHabitacion(1,301));
    }
    @Test
    void cambiarEstadoHabitacionNull() {
        Mockito.when(portInMock.changeRoomStatus(301,1)).thenReturn(null);

        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.cambiarEstadoHabitacion(1,301));
    }

    @Test
    void eliminarHabitacion() {
        Mockito.when(portInMock.deleteRoom(roomMock2)).thenReturn(true);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.OK),controller.eliminarHabitacion(roomMock2));
    }
    @Test
    void eliminarHabitacionFalse() {
        Mockito.when(portInMock.deleteRoom(roomMock2)).thenReturn(false);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.eliminarHabitacion(roomMock2));
    }

    @Test
    void eliminarHabitacionPorId() {
        Mockito.when(portInMock.deleteRoomById(1)).thenReturn(true);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.OK),controller.eliminarHabitacionPorId(1));
    }
    @Test
    void eliminarHabitacionPorIdFalse() {
        Mockito.when(portInMock.deleteRoomById(1)).thenReturn(false);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.eliminarHabitacionPorId(1));
    }
}