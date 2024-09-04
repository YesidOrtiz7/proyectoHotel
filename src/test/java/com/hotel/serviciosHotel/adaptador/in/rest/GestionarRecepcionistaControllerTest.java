package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.servicio.RecepcionistaService;
import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.InvalidCharacterException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class GestionarRecepcionistaControllerTest {
    private RecepcionistaPortIn service;
    private GestionarRecepcionistaController controller=new GestionarRecepcionistaController();

    private ReceptionistEntity receptionist;
    private ReceptionistEntity receptionist2;
    private ArrayList<ReceptionistEntity> receptionistArrayList=new ArrayList<>();
    @BeforeEach
    void setUp(){
        service= Mockito.mock(RecepcionistaService.class);
        controller.setService(service);
        receptionist=new ReceptionistEntity(
                1,
                "1111",
                "Recepcionista nombre",
                "Recepcionista apellido"
        );
        receptionist2=new ReceptionistEntity(
                2,
                "2222",
                "Recepcionista nombre",
                "Recepcionista apellido"
        );
        receptionistArrayList.add(receptionist);
        receptionistArrayList.add(receptionist2);
    }
    @Test
    void obtenerRecepcionistaPorDocumento(){
        try {
            Mockito.when(service.obtenerRecepcionistaPorDocumento("1111")).thenReturn(receptionist);
            Assertions.assertEquals(new ResponseEntity<>(receptionist, HttpStatus.OK),controller.obtenerRecepcionistaPorDocumento("1111"));
            Mockito.verify(service,Mockito.times(1)).obtenerRecepcionistaPorDocumento("1111");
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistaPorDocumento_ResponseNull(){
        try {
            Mockito.when(service.obtenerRecepcionistaPorDocumento("1111")).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerRecepcionistaPorDocumento("1111"));
            Mockito.verify(service,Mockito.times(1)).obtenerRecepcionistaPorDocumento("1111");
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistaPorDocumento_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.obtenerRecepcionistaPorDocumento("1111")).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.obtenerRecepcionistaPorDocumento("1111"));
            Mockito.verify(service,Mockito.times(1)).obtenerRecepcionistaPorDocumento("1111");
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistaPorId(){
        try {
            Mockito.when(service.obtenerRecepcionistaPorId(1)).thenReturn(receptionist);
            Assertions.assertEquals(new ResponseEntity<>(receptionist, HttpStatus.OK),controller.obtenerRecepcionistaPorId(1));
            Mockito.verify(service,Mockito.times(1)).obtenerRecepcionistaPorId(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistaPorId_ResponseNull(){
        try {
            Mockito.when(service.obtenerRecepcionistaPorId(1)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerRecepcionistaPorId(1));
            Mockito.verify(service,Mockito.times(1)).obtenerRecepcionistaPorId(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistaPorId_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.obtenerRecepcionistaPorId(1)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.obtenerRecepcionistaPorId(1));
            Mockito.verify(service,Mockito.times(1)).obtenerRecepcionistaPorId(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistas(){
        try {
            Mockito.when(service.obtenerRecepcionistas()).thenReturn(receptionistArrayList);
            Assertions.assertEquals(new ResponseEntity<>(receptionistArrayList, HttpStatus.OK),controller.obtenerRecepcionistas());
            Mockito.verify(service,Mockito.times(1)).obtenerRecepcionistas();
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistas_ResponseNull(){
        try {
            Mockito.when(service.obtenerRecepcionistas()).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerRecepcionistas());
            Mockito.verify(service,Mockito.times(1)).obtenerRecepcionistas();
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarRecepcionistas(){
        try {
            Mockito.when(service.actualizarRecepcionista(receptionist2)).thenReturn(receptionist2);
            Assertions.assertEquals(new ResponseEntity<>(receptionist2,HttpStatus.OK),controller.actualizarRecepcionistas(receptionist2));
            Mockito.verify(service,Mockito.times(1)).actualizarRecepcionista(receptionist2);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarRecepcionistas_ResponseNull(){
        try {
            Mockito.when(service.actualizarRecepcionista(receptionist2)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.actualizarRecepcionistas(receptionist2));
            Mockito.verify(service,Mockito.times(1)).actualizarRecepcionista(receptionist2);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarRecepcionistas_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.actualizarRecepcionista(receptionist2)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.actualizarRecepcionistas(receptionist2));
            Mockito.verify(service,Mockito.times(1)).actualizarRecepcionista(receptionist2);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarRecepcionistas_ServiceThrowsInvalidCharacterException(){
        try {
            Mockito.when(service.actualizarRecepcionista(receptionist2)).thenThrow(InvalidCharacterException.class);
            Assertions.assertThrows(InvalidCharacterException.class,()->controller.actualizarRecepcionistas(receptionist2));
            Mockito.verify(service,Mockito.times(1)).actualizarRecepcionista(receptionist2);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
