package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.adaptador.modelResponse.ExtendServicesRequestModel;
import com.hotel.serviciosHotel.adaptador.modelResponse.OnlyId;
import com.hotel.serviciosHotel.adaptador.modelResponse.UpdateRateServiceRequest;
import com.hotel.serviciosHotel.adaptador.modelResponse.UpdateRoomServiceRequest;
import com.hotel.serviciosHotel.aplicacion.puerto.in.ServicioPortIn;
import com.hotel.serviciosHotel.aplicacion.servicio.ServicioService;
import com.hotel.serviciosHotel.dominio.entidades.*;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.GenericException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
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

    private Service serviceMock1=new Service(
        8,
        new ReceptionistEntity(1,"11111","Juana","Ordonez"),
        new Client(
                10,
                "959595",
                "Juan",
                "Carlos",
                "Mendoza",
                "Pinzon",
                "959595"),
        new Room(
                103,
                new RoomStatus(
                3,
                "sucia",
                false,
                false,
                true
                ),
                new RoomType(
                2,
                "doble"
                ),
                3000.0,
                2,
                3
        ),
        new RateType(2,"Transportador",50),
        new Municipios(1,"municipio1"),
        new Municipios(2,"municipio2"),
        new PaymentType(3,"efectivo"),
        3000.0,
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(2),
        false,
        true
    );
    private Service serviceMock2=new Service(
        9,
        new ReceptionistEntity(1,"11111","Juana","Ordonez"),
        new Client(
            9,
            "90123456",
            "Javier",
            "Roberto",
            "Díaz",
            "Serrano",
            "5559012345"
        ),
        new Room(
            101,
            new RoomStatus(
                2,
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
        ),
        new RateType(
            1,
            "cliente normal",
            0
        ),
        new Municipios(
            1,
            "Fusagasuga"
        ),
        new Municipios(
            2,
            "San Bernardo"
        ),
        new PaymentType(
            1,
            "efectivo"
        ),
        4000.0,
        LocalDateTime.now(),
        LocalDateTime.now().plusDays(2),
        false,
        true
    );
    private Room roomClean=new Room(102,
            new RoomStatus(1,
                    "limpia",
                    true,
                    false,
                    false)
            ,new RoomType(
            1,
            "normal"),
            2000.0,
            1,
            2);
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
    void obtenerServicioPorId(){
        try {
            Mockito.when(portInMock.consultarServicioPorId(8)).thenReturn(serviceMock1);
            Assertions.assertEquals(new ResponseEntity<>(serviceMock1,HttpStatus.OK),controller.obtenerServicioPorId(8));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerServicioPorId_NoSeEncuentraElServicio(){
        try {
            SearchItemNotFoundException e=new SearchItemNotFoundException("el servicio con el id "+8+" no existe");
            Mockito.when(portInMock.consultarServicioPorId(8)).thenThrow(e);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.obtenerServicioPorId(8));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerTodosLosServicios(){
        Mockito.when(portInMock.consultarServicios()).thenReturn(listServiceMock);
        Assertions.assertEquals(new ResponseEntity<>(listServiceMock,HttpStatus.OK),controller.obtenerTodosLosServicios());
    }
    @Test
    void obtenerTodosLosServicios_RespuestaNull(){
        Mockito.when(portInMock.consultarServicios()).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),controller.obtenerTodosLosServicios());
    }
    @Test
    void crearServicio(){
        try {
            Mockito.when(portInMock.registrarServicio(serviceMock2)).thenReturn(serviceMock2);
            Assertions.assertEquals(new ResponseEntity<>(serviceMock2,HttpStatus.CREATED),controller.crearServicio(serviceMock2));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void crearServicio_ResponseNull(){
        try {
            Mockito.when(portInMock.registrarServicio(serviceMock2)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.crearServicio(serviceMock2));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarHabitacionServicio(){
        try {
            Mockito.when(portInMock.actualizarHabitacionServicio(9,2)).thenReturn(serviceMock2);
            Assertions.assertEquals(new ResponseEntity<>(serviceMock2,HttpStatus.OK),controller.actualizarHabitacionServicio(new UpdateRoomServiceRequest(9,2)));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarHabitacionServicio_ResponseNull(){
        try {
            Mockito.when(portInMock.actualizarHabitacionServicio(9,2)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.actualizarHabitacionServicio(new UpdateRoomServiceRequest(9,2)));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarTarifaServicio(){
        try {
            Mockito.when(portInMock.actualizarTarifaServicio(9,1)).thenReturn(serviceMock2);
            Assertions.assertEquals(new ResponseEntity<>(serviceMock2,HttpStatus.OK),controller.actualizarTarifaServicio(new UpdateRateServiceRequest(9,1)));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarTarifaServicio_ResponseNull(){
        try {
            Mockito.when(portInMock.actualizarTarifaServicio(9,1)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.actualizarTarifaServicio(new UpdateRateServiceRequest(9,1)));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void ampliarServicio(){
        try {
            serviceMock2.setItsPaid(false);
            serviceMock2.setState(true);
            Mockito.when(portInMock.consultarServicioPorId(9)).thenReturn(serviceMock2);
            Mockito.when(portInMock.ampliarServicio(serviceMock2,1,0,0)).thenReturn(serviceMock2);
            Assertions.assertEquals(new ResponseEntity<>(serviceMock2,HttpStatus.OK),controller.ampliarServicio(new ExtendServicesRequestModel(9,1,0,0)));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void ampliarServicio_ResponseNull(){
        try {
            serviceMock2.setItsPaid(false);
            serviceMock2.setState(true);
            Mockito.when(portInMock.consultarServicioPorId(9)).thenReturn(serviceMock2);
            Mockito.when(portInMock.ampliarServicio(serviceMock2,1,0,0)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.ampliarServicio(new ExtendServicesRequestModel(9,1,0,0)));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void ampliarServicio_ServicioNull(){
        try {
            serviceMock2.setItsPaid(false);
            serviceMock2.setState(true);
            Mockito.when(portInMock.consultarServicioPorId(9)).thenReturn(null);
            Mockito.when(portInMock.ampliarServicio(serviceMock2,1,0,0)).thenReturn(null);
            Assertions.assertThrows(GenericException.class,()->controller.ampliarServicio(new ExtendServicesRequestModel(9,1,0,0)));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void ampliarServicio_ServicioCerrado(){
        try {
            Mockito.when(portInMock.consultarServicioPorId(9)).thenReturn(serviceMock2);
            Mockito.when(portInMock.ampliarServicio(serviceMock2,1,0,0)).thenReturn(serviceMock2);
            Assertions.assertThrows(GenericException.class,()->controller.ampliarServicio(new ExtendServicesRequestModel(9,1,0,0)));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void cerrarServicioPorId(){
        try {
            Mockito.when(portInMock.cerrarServicioPorIdServicio(9)).thenReturn(serviceMock2);
            Assertions.assertEquals(new ResponseEntity<>(serviceMock2,HttpStatus.OK),controller.cerrarServicioPorId(new OnlyId(9)));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void cerrarServicioPorId_ResponseNull(){
        try {
            Mockito.when(portInMock.cerrarServicioPorIdServicio(9)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.cerrarServicioPorId(new OnlyId(9)));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void pagarServicioPorId(){
        try {
            Mockito.when(portInMock.pagarServicio(9)).thenReturn(serviceMock2);
            Assertions.assertEquals(new ResponseEntity<>(serviceMock2,HttpStatus.ACCEPTED),controller.pagarServicioPorId(new OnlyId(9)));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
    @Test
    void pagarServicioPorId_ResponseNull(){
        try {
            Mockito.when(portInMock.pagarServicio(9)).thenReturn(null);
            Assertions.assertThrows(GenericException.class,()->controller.pagarServicioPorId(new OnlyId(9)));
        }catch (Exception e){
            Assertions.fail(e);
        }
    }
}