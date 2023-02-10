package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.ServicioPortIn;
import com.hotel.serviciosHotel.aplicacion.servicio.ServicioService;
import com.hotel.serviciosHotel.dominio.entidades.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionarServicioControllerTest {
    private ServicioPortIn portInMock;

    private Service serviceMock1=new Service(1,
            new ReceptionistEntity(1,"789","maria","delmar"),
            new Client(1,"456","juan","","perez","","32123"),
            new Room(
                    302,
                    new RoomStatus(3,"ocupada"),
                    new RoomType(1,"doble"),
                    4,
                    2),
            new RateType(1,"normal"),
            new Municipios(1,"municipio1"),
            new Municipios(2,"municipio2"),
            new PaymentType(3,"efectivo"),
            1,
            new Date(),
            new Date(),
            (byte) 0);
    private Service serviceMock2=new Service(1,
            new ReceptionistEntity(1,"789","maria","delmar"),
            new Client(1,"456","juan","","perez","","32123"),
            new Room(
                    302,
                    new RoomStatus(1,"lipia"),
                    new RoomType(1,"doble"),
                    4,
                    2),
            new RateType(1,"normal"),
            new Municipios(1,"municipio1"),
            new Municipios(2,"municipio2"),
            new PaymentType(3,"efectivo"),
            1,
            new Date(),
            new Date(),
            (byte) 0);
    private List<Service> listServiceMock=new ArrayList<>();

    private GestionarServicioController controller;

    @BeforeEach
    void setUp() {
        portInMock= Mockito.mock(ServicioService.class);

        listServiceMock.add(serviceMock1);
        listServiceMock.add(serviceMock2);

        controller=new GestionarServicioController();
        controller.setServicePortIn(portInMock);
    }
    @Test
    void obtenerServicioPorId() {
        Mockito.when(portInMock.consultarServicioPorId(1)).thenReturn(serviceMock1);
        Assertions.assertEquals(new ResponseEntity<>(serviceMock1, HttpStatus.OK),controller.obtenerServicioPorId(1));
    }
    @Test
    void obtenerServicioPorIdNull() {
        Mockito.when(portInMock.consultarServicioPorId(1)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerServicioPorId(1));
    }

    @Test
    void obtenerTodosLosServicios() {
        Mockito.when(portInMock.consultarServicios()).thenReturn(listServiceMock);
        Assertions.assertEquals(new ResponseEntity<>(listServiceMock,HttpStatus.OK),controller.obtenerTodosLosServicios());
    }
    @Test
    void obtenerTodosLosServiciosNull() {
        Mockito.when(portInMock.consultarServicios()).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerTodosLosServicios());
    }

    @Test
    void crearServicio() {
        Mockito.when(portInMock.registrarServicio(serviceMock2)).thenReturn(serviceMock2);
        Assertions.assertEquals(new ResponseEntity<>(serviceMock2,HttpStatus.CREATED),controller.crearServicio(serviceMock2));
    }
    @Test
    void crearServicioNull() {
        Mockito.when(portInMock.registrarServicio(serviceMock2)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.crearServicio(serviceMock2));
    }

    @Test
    void actualizarHabitacionServicio() {
        Mockito.when(portInMock.actualizarHabitacionServicio(serviceMock1,301)).thenReturn(serviceMock1);
        Assertions.assertEquals(new ResponseEntity<>(serviceMock1,HttpStatus.OK),controller.actualizarHabitacionServicio(serviceMock1,301));
    }
    @Test
    void actualizarHabitacionServicioNull() {
        Mockito.when(portInMock.actualizarHabitacionServicio(serviceMock1,301)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.actualizarHabitacionServicio(serviceMock1,301));
    }

    @Test
    void actualizarTarifaServicio() {
        Mockito.when(portInMock.actualizarTarifaServicio(serviceMock2,2)).thenReturn(serviceMock2);
        Assertions.assertEquals(new ResponseEntity<>(serviceMock2,HttpStatus.OK),controller.actualizarTarifaServicio(serviceMock2,2));
    }
}