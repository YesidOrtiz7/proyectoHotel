package com.hotel.serviciosHotel.adaptador.in.rest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.servicio.RecepcionistaService;
import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
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

class GestionarRecepcionistaControllerTest {
    private RecepcionistaPortIn portInMock;
    private ReceptionistEntity receptionistMock1=new ReceptionistEntity(1,"789","maria","delmar");
    private ReceptionistEntity receptionistMock2=new ReceptionistEntity(2,"222","nombre","apellido");
    private List<ReceptionistEntity> listReceptionistMock=new ArrayList<>();

    private GestionarRecepcionistaController controller;

    @BeforeEach
    void setUp() {
        portInMock= Mockito.mock(RecepcionistaService.class);

        listReceptionistMock.add(receptionistMock1);
        listReceptionistMock.add(receptionistMock2);

        controller=new GestionarRecepcionistaController();
        controller.setService(portInMock);
    }

    @Test
    void obtenerRecepcionistaPorDocumento() {
        Mockito.when(portInMock.obtenerRecepcionistaPorDocumento("222")).thenReturn(receptionistMock2);
        Assertions.assertEquals(new ResponseEntity<>(receptionistMock2, HttpStatus.OK),controller.obtenerRecepcionistaPorDocumento("222"));
    }
    @Test
    void obtenerRecepcionistaPorDocumentoNull() {
        Mockito.when(portInMock.obtenerRecepcionistaPorDocumento("222")).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerRecepcionistaPorDocumento("222"));
    }

    @Test
    void obtenerRecepcionistaPorId() throws SearchItemNotFoundException {
        Mockito.when(portInMock.obtenerRecepcionistaPorId(2)).thenReturn(receptionistMock2);
        Assertions.assertEquals(new ResponseEntity<>(receptionistMock2,HttpStatus.OK),controller.obtenerRecepcionistaPorId(2));
    }
    @Test
    void obtenerRecepcionistaPorIdNull() throws SearchItemNotFoundException {
        Mockito.when(portInMock.obtenerRecepcionistaPorId(2)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerRecepcionistaPorId(2));
    }

    @Test
    void obtenerRecepcionistas() {
        Mockito.when(portInMock.obtenerRecepcionistas()).thenReturn(listReceptionistMock);
        Assertions.assertEquals(new ResponseEntity<>(listReceptionistMock,HttpStatus.OK),controller.obtenerRecepcionistas());
    }
    @Test
    void obtenerRecepcionistasNull() {
        Mockito.when(portInMock.obtenerRecepcionistas()).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerRecepcionistas());
    }

    @Test
    void actualizarRecepcionistas() {
        Mockito.when(portInMock.actualizarRecepcionista(receptionistMock1)).thenReturn(receptionistMock1);
        Assertions.assertEquals(new ResponseEntity<>(receptionistMock1,HttpStatus.OK),controller.actualizarRecepcionistas(receptionistMock1));
    }
    @Test
    void actualizarRecepcionistasNull() {
        Mockito.when(portInMock.actualizarRecepcionista(receptionistMock1)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.actualizarRecepcionistas(receptionistMock1));
    }

    @Test
    void registrarRecepcionista() {
        Mockito.when(portInMock.registrarRecepcionista(receptionistMock1)).thenReturn(receptionistMock1);
        Assertions.assertEquals(new ResponseEntity<>(receptionistMock1,HttpStatus.CREATED),controller.registrarRecepcionista(receptionistMock1));
    }
    @Test
    void registrarRecepcionistaNull() {
        Mockito.when(portInMock.registrarRecepcionista(receptionistMock1)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.registrarRecepcionista(receptionistMock1));
    }
}