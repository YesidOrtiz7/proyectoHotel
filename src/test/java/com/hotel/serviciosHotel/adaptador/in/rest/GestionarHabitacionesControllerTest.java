package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.HabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.servicio.HabitacionService;
import com.hotel.serviciosHotel.dominio.entidades.Room;
import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;
import com.hotel.serviciosHotel.dominio.entidades.RoomType;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class GestionarHabitacionesControllerTest {
    private GestionarHabitacionesController controller=new GestionarHabitacionesController();
    private Room roomClean;
    private Room roomInUse;
    private ArrayList<Room> roomsList=new ArrayList<>();
    private HabitacionPortIn service;
    @BeforeEach
    void setUp(){
        roomsList.add(roomClean);
        roomsList.add(roomInUse);
        service= Mockito.mock(HabitacionService.class);
        controller.setService(service);
        roomClean=new Room(
                102,
                new RoomStatus(
                        1,
                        "limpia",
                        true,
                        false,
                        false
                ),
                new RoomType(
                        1,
                        "normal"
                ),
                3100.0,
                1,
                2
        );
        roomInUse=new Room(
                101,
                new RoomStatus(2,
                        "ocupada",
                        false,
                        true,
                        false
                ),
                new RoomType(
                        1,
                        "normal"
                ),
                2000.0,
                1,
                1
        );

    }
    @Test
    void obtenerHabitacionPorNumero(){
        try {
            Mockito.when(service.getRoomByNumber(101)).thenReturn(roomInUse);
            Assertions.assertEquals(new ResponseEntity<>(roomInUse,HttpStatus.OK),controller.obtenerHabitacionPorNumero(101));
            Mockito.verify(service,Mockito.times(1)).getRoomByNumber(101);
        } catch (Exception e) {
            Assertions.fail(e);
        }

    }
    @Test
    void obtenerHabitacionPorNumero_ResponseNull(){
        try {
            Mockito.when(service.getRoomByNumber(101)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerHabitacionPorNumero(101));
        } catch (Exception e) {
            Assertions.fail(e);
        }

    }
    @Test
    void obtenerHabitacionPorNumero_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.getRoomByNumber(101)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.obtenerHabitacionPorNumero(101));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerHabitacionPorId(){
        try {
            Mockito.when(service.getRoomById(1)).thenReturn(roomInUse);
            Assertions.assertEquals(new ResponseEntity<>(roomInUse,HttpStatus.OK),controller.obtenerHabitacionPorId(1));
            Mockito.verify(service,Mockito.times(1)).getRoomById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerHabitacionPorId_ResponseNull(){
        try {
            Mockito.when(service.getRoomById(1)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerHabitacionPorId(1));
            Mockito.verify(service,Mockito.times(1)).getRoomById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerHabitacionPorId_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.getRoomById(1)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.obtenerHabitacionPorId(1));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerHabitaciones(){
        try {
            Mockito.when(service.getRooms()).thenReturn(roomsList);
            Assertions.assertEquals(new ResponseEntity<>(roomsList,HttpStatus.OK),controller.obtenerHabitaciones());
            Mockito.verify(service,Mockito.times(1)).getRooms();
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerHabitaciones_ResponseNull(){
        try {
            Mockito.when(service.getRooms()).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerHabitaciones());
            Mockito.verify(service,Mockito.times(1)).getRooms();
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarNuevaHabitacion(){
        try {
            Mockito.when(service.createRoom(roomClean)).thenReturn(roomClean);
            Assertions.assertEquals(new ResponseEntity<>(roomClean,HttpStatus.CREATED),controller.registrarNuevaHabitacion(roomClean));
            Mockito.verify(service,Mockito.times(1)).createRoom(roomClean);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarNuevaHabitacion_ResponseNull(){
        try {
            Mockito.when(service.createRoom(roomClean)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.registrarNuevaHabitacion(roomClean));
            Mockito.verify(service,Mockito.times(1)).createRoom(roomClean);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarNuevaHabitacion_ServiceThrowsItemAlreadyExistException(){
        try {
            Mockito.when(service.createRoom(roomClean)).thenThrow(ItemAlreadyExistException.class);
            Assertions.assertThrows(ItemAlreadyExistException.class,()->controller.registrarNuevaHabitacion(roomClean));
            Mockito.verify(service,Mockito.times(1)).createRoom(roomClean);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarHabitacion(){
        try {
            Mockito.when(service.updateRoom(roomInUse)).thenReturn(roomInUse);
            Assertions.assertEquals(new ResponseEntity<>(roomInUse,HttpStatus.OK),controller.actualizarHabitacion(roomInUse));
            Mockito.verify(service,Mockito.times(1)).updateRoom(roomInUse);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarHabitacion_ResponseNull(){
        try {
            Mockito.when(service.updateRoom(roomInUse)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.actualizarHabitacion(roomInUse));
            Mockito.verify(service,Mockito.times(1)).updateRoom(roomInUse);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarHabitacion_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.updateRoom(roomInUse)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.actualizarHabitacion(roomInUse));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void cambiarTipoHabitacion(){
        try {
            Mockito.when(service.changeRoomType(1,1)).thenReturn(roomInUse);
            Assertions.assertEquals(new ResponseEntity<>(roomInUse,HttpStatus.OK),controller.cambiarTipoHabitacion(1,1));
            Mockito.verify(service,Mockito.times(1)).changeRoomType(1,1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void cambiarTipoHabitacion_ResponseNull(){
        try {
            Mockito.when(service.changeRoomType(1,1)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.cambiarTipoHabitacion(1,1));
            Mockito.verify(service,Mockito.times(1)).changeRoomType(1,1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void cambiarTipoHabitacion_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.changeRoomType(1,1)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.cambiarTipoHabitacion(1,1));
            Mockito.verify(service,Mockito.times(1)).changeRoomType(1,1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void cambiarEstadoHabitacion(){
        try {
            Mockito.when(service.changeRoomStatus(2,1)).thenReturn(roomClean);
            Assertions.assertEquals(new ResponseEntity<>(roomClean,HttpStatus.OK),controller.cambiarEstadoHabitacion(2,1));
            Mockito.verify(service,Mockito.times(1)).changeRoomStatus(2,1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void cambiarEstadoHabitacion_ResponseNull(){
        try {
            Mockito.when(service.changeRoomStatus(2,1)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.cambiarEstadoHabitacion(2,1));
            Mockito.verify(service,Mockito.times(1)).changeRoomStatus(2,1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void cambiarEstadoHabitacion_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.changeRoomStatus(2,1)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.cambiarEstadoHabitacion(2,1));
            Mockito.verify(service,Mockito.times(1)).changeRoomStatus(2,1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarHabitacion(){
        try {
            Mockito.when(service.deleteRoom(roomClean)).thenReturn(true);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.OK),controller.eliminarHabitacion(roomClean));
            Mockito.verify(service,Mockito.times(1)).deleteRoom(roomClean);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarHabitacion_ServiceResponseFalse(){
        try {
            Mockito.when(service.deleteRoom(roomClean)).thenReturn(false);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.eliminarHabitacion(roomClean));
            Mockito.verify(service,Mockito.times(1)).deleteRoom(roomClean);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarHabitacion_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.deleteRoom(roomClean)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.eliminarHabitacion(roomClean));
            Mockito.verify(service,Mockito.times(1)).deleteRoom(roomClean);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarHabitacionPorId(){
        try {
            Mockito.when(service.deleteRoomById(2)).thenReturn(true);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.OK),controller.eliminarHabitacionPorId(2));
            Mockito.verify(service,Mockito.times(1)).deleteRoomById(2);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarHabitacionPorId_ServiceResponseFalse(){
        try {
            Mockito.when(service.deleteRoomById(2)).thenReturn(false);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.eliminarHabitacionPorId(2));
            Mockito.verify(service,Mockito.times(1)).deleteRoomById(2);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarHabitacionPorId_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.deleteRoomById(2)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.eliminarHabitacionPorId(2));
            Mockito.verify(service,Mockito.times(1)).deleteRoomById(2);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}