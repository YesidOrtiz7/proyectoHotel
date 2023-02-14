package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.adaptador.modelResponse.ExtendServicesRequestModel;
import com.hotel.serviciosHotel.adaptador.modelResponse.UpdateRateServiceRequest;
import com.hotel.serviciosHotel.adaptador.modelResponse.UpdateRoomServiceRequest;
import com.hotel.serviciosHotel.aplicacion.puerto.in.ServicioPortIn;
import com.hotel.serviciosHotel.aplicacion.servicio.ServicioService;
import com.hotel.serviciosHotel.dominio.entidades.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
                    2,
                    1),
            new RateType(1,"normal",0),
            new Municipios(1,"municipio1"),
            new Municipios(2,"municipio2"),
            new PaymentType(3,"efectivo"),
            1,
            LocalDateTime.now(),
            LocalDateTime.now(),
            0);
    private Service serviceMock2=new Service(1,
            new ReceptionistEntity(1,"789","maria","delmar"),
            new Client(1,"456","juan","","perez","","32123"),
            new Room(
                    302,
                    new RoomStatus(1,"lipia"),
                    new RoomType(1,"doble"),
                    4,
                    2,
                    0),
            new RateType(1,"normal",15),
            new Municipios(1,"municipio1"),
            new Municipios(2,"municipio2"),
            new PaymentType(3,"efectivo"),
            1,
            LocalDateTime.now(),
            LocalDateTime.now(),
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
        UpdateRoomServiceRequest request=new UpdateRoomServiceRequest(1,301);

        Mockito.when(portInMock.actualizarHabitacionServicio(1,301)).thenReturn(serviceMock1);
        Assertions.assertEquals(new ResponseEntity<>(serviceMock1,HttpStatus.OK),controller.actualizarHabitacionServicio(request));
    }
    @Test
    void actualizarHabitacionServicioNull() {
        UpdateRoomServiceRequest request=new UpdateRoomServiceRequest(1,301);

        Mockito.when(portInMock.actualizarHabitacionServicio(1,301)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.actualizarHabitacionServicio(request));
    }

    @Test
    void actualizarTarifaServicio() {
        UpdateRateServiceRequest request=new UpdateRateServiceRequest(1,2);

        Mockito.when(portInMock.actualizarTarifaServicio(1,2)).thenReturn(serviceMock2);
        Assertions.assertEquals(new ResponseEntity<>(serviceMock2,HttpStatus.OK),controller.actualizarTarifaServicio(request));
    }

    @Test
    void ampliarServicio() {
        ExtendServicesRequestModel request=new ExtendServicesRequestModel(serviceMock2,1,0,0);

        Mockito.when(portInMock.ampliarServicio(request.getService(),request.getDia(),request.getHora(),request.getMinuto())).thenReturn(serviceMock2);
        Assertions.assertEquals(new ResponseEntity<>(serviceMock2,HttpStatus.OK),controller.ampliarServicio(request));
    }

    @Test
    void cerrarServicioPorId() {
        Mockito.when(portInMock.cerrarServicioPorIdServicio(1)).thenReturn(serviceMock1);
        Assertions.assertEquals(new ResponseEntity<>(serviceMock1,HttpStatus.OK),controller.cerrarServicioPorId(1));
    }

    @Test
    void cerrarServicioPorIdNull() {
        Mockito.when(portInMock.cerrarServicioPorIdServicio(1)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.cerrarServicioPorId(1));
    }
}