package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.TarifaPortIn;
import com.hotel.serviciosHotel.aplicacion.servicio.TarifasService;
import com.hotel.serviciosHotel.dominio.entidades.RateType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionarTarifasControllerTest {
    private TarifaPortIn portInMock;
    private RateType rateTypeMock1=new RateType(1,"tipo1",0);
    private RateType rateTypeMock2=new RateType(2,"tipo2",20);
    private List<RateType> listRateTypeMock=new ArrayList<>();

    private GestionarTarifasController controller;

    @BeforeEach
    void setUp() {
        portInMock= Mockito.mock(TarifasService.class);

        listRateTypeMock.add(rateTypeMock1);
        listRateTypeMock.add(rateTypeMock2);

        controller=new GestionarTarifasController();
        controller.setService(portInMock);
    }

    @Test
    void obtenerTarifas() {
        Mockito.when(portInMock.obtenerTarifas()).thenReturn(listRateTypeMock);
        Assertions.assertEquals(new ResponseEntity<>(listRateTypeMock, HttpStatus.OK),controller.obtenerTarifas());
    }
    @Test
    void obtenerTarifasNull() {
        Mockito.when(portInMock.obtenerTarifas()).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerTarifas());
    }

    @Test
    void crearNuevaTarifa() {
        Mockito.when(portInMock.registrarTarifa(rateTypeMock1)).thenReturn(rateTypeMock1);
        Assertions.assertEquals(new ResponseEntity<>(rateTypeMock1,HttpStatus.CREATED),controller.crearNuevaTarifa(rateTypeMock1));
    }
    @Test
    void crearNuevaTarifaNull() {
        Mockito.when(portInMock.registrarTarifa(rateTypeMock1)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.crearNuevaTarifa(rateTypeMock1));
    }

    @Test
    void obtenerTarifaPorId() {
        Mockito.when(portInMock.obtenerTarifaPorId(1)).thenReturn(rateTypeMock1);
        Assertions.assertEquals(new ResponseEntity<>(rateTypeMock1,HttpStatus.OK),controller.obtenerTarifaPorId(1));
    }
    @Test
    void obtenerTarifaPorIdNull() {
        Mockito.when(portInMock.obtenerTarifaPorId(1)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerTarifaPorId(1));
    }

    @Test
    void actualizarTarifa() {
        Mockito.when(portInMock.actualizarTarifa(rateTypeMock2)).thenReturn(rateTypeMock2);
        Assertions.assertEquals(new ResponseEntity<>(rateTypeMock2,HttpStatus.OK),controller.actualizarTarifa(rateTypeMock2));
    }
    @Test
    void actualizarTarifaNull() {
        Mockito.when(portInMock.actualizarTarifa(rateTypeMock2)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.actualizarTarifa(rateTypeMock2));
    }

    @Test
    void eliminarTarifa() {
        Mockito.when(portInMock.eliminarTarifa(rateTypeMock2)).thenReturn(true);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.OK),controller.eliminarTarifa(rateTypeMock2));
    }
    @Test
    void eliminarTarifaFalse() {
        Mockito.when(portInMock.eliminarTarifa(rateTypeMock2)).thenReturn(false);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.eliminarTarifa(rateTypeMock2));
    }
}