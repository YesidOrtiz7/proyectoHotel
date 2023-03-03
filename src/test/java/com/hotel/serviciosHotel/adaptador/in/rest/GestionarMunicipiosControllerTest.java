package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.MunicipioPortIn;
import com.hotel.serviciosHotel.aplicacion.servicio.MunicipioService;
import com.hotel.serviciosHotel.dominio.entidades.Municipios;
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

class GestionarMunicipiosControllerTest {
    private MunicipioPortIn portInMock;
    private Municipios municipioMock1=new Municipios(1,"municipio1");
    private Municipios municipioMock2=new Municipios(2,"municipio2");
    private List<Municipios> municipiosListMock=new ArrayList<>();

    private GestionarMunicipiosController controller;

    @BeforeEach
    void setUp() {
        portInMock= Mockito.mock(MunicipioService.class);

        municipiosListMock.add(municipioMock1);
        municipiosListMock.add(municipioMock2);

        controller=new GestionarMunicipiosController();
        controller.setService(portInMock);
    }

    @Test
    void obtenerMunicipios() {
        Mockito.when(portInMock.obtenerMunicipios()).thenReturn(municipiosListMock);
        Assertions.assertEquals(new ResponseEntity<>(municipiosListMock, HttpStatus.OK),controller.obtenerMunicipios());
    }
    @Test
    void obtenerMunicipiosNull() {
        Mockito.when(portInMock.obtenerMunicipios()).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerMunicipios());
    }

    @Test
    void registrarMunicipio() {
        Mockito.when(portInMock.registrarMunicipio(municipioMock1)).thenReturn(municipioMock1);
        Assertions.assertEquals(new ResponseEntity<>(municipioMock1,HttpStatus.CREATED),controller.registrarMunicipio(municipioMock1));
    }
    @Test
    void registrarMunicipioNull() {
        Mockito.when(portInMock.registrarMunicipio(municipioMock1)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.registrarMunicipio(municipioMock1));
    }

    @Test
    void obtenerMunicipiosPorId() throws SearchItemNotFoundException {
        Mockito.when(portInMock.obtenerMunicipioPorId(1)).thenReturn(municipioMock1);
        Assertions.assertEquals(new ResponseEntity<>(municipioMock1,HttpStatus.OK),controller.obtenerMunicipiosPorId(1));
    }
    @Test
    void obtenerMunicipiosPorIdNull() throws SearchItemNotFoundException {
        Mockito.when(portInMock.obtenerMunicipioPorId(1)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerMunicipiosPorId(1));
    }

    @Test
    void actualizarMunicipio() {
        Mockito.when(portInMock.actualizarMunicipio(municipioMock2)).thenReturn(municipioMock2);
        Assertions.assertEquals(new ResponseEntity<>(municipioMock2,HttpStatus.OK),controller.actualizarMunicipio(municipioMock2));
    }
    @Test
    void actualizarMunicipioNull() {
        Mockito.when(portInMock.actualizarMunicipio(municipioMock2)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.actualizarMunicipio(municipioMock2));
    }

    @Test
    void eliminarMunicipio() {
        Mockito.when(portInMock.eliminarMunicipio(municipioMock2)).thenReturn(true);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.OK),controller.eliminarMunicipio(municipioMock2));
    }
    @Test
    void eliminarMunicipioFalse() {
        Mockito.when(portInMock.eliminarMunicipio(municipioMock2)).thenReturn(false);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.eliminarMunicipio(municipioMock2));
    }
}