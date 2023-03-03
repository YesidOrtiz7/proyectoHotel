package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.ClientePortIn;
import com.hotel.serviciosHotel.aplicacion.servicio.ClienteService;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionarClienteControllerTest {

    private ClientePortIn portInMock;
    private Client clientMock1=new Client(1,"111","primerNombre","segundoNombre","primerApellido","segundoApellido","1111");
    private Client clientMock2=new Client(2,"222","primerNombre","segundoNombre","primerApellido","segundoApellido","2222");
    private List<Client> listClientMock=new ArrayList<>();

    private GestionarClienteController controller;

    @BeforeEach
    void setUp() {
        portInMock= Mockito.mock(ClienteService.class);

        listClientMock.add(clientMock1);
        listClientMock.add(clientMock2);

        controller=new GestionarClienteController();
        controller.setService(portInMock);
    }

    @Test
    void obtenerClientePorDocumento() {
        Mockito.when(portInMock.consultarClientePorDocumento("111")).thenReturn(clientMock1);

        Assertions.assertEquals(new ResponseEntity<>(clientMock1, HttpStatus.OK),controller.obtenerClientePorDocumento("111"));
    }
    @Test
    void obtenerClientePorDocumentoClienteNull() {
        Mockito.when(portInMock.consultarClientePorDocumento("111")).thenReturn(null);

        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerClientePorDocumento("111"));
    }

    @Test
    void obtenerClientePorId() throws SearchItemNotFoundException {
        Mockito.when(portInMock.consultarClientePorId(1)).thenReturn(clientMock1);

        Assertions.assertEquals(new ResponseEntity<>(clientMock1, HttpStatus.OK),controller.obtenerClientePorId(1));
    }
    @Test
    void obtenerClientePorIdNull() throws SearchItemNotFoundException {
        Mockito.when(portInMock.consultarClientePorId(1)).thenReturn(null);

        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerClientePorId(1));
    }

    @Test
    void obtenerClientes() {
        Mockito.when(portInMock.obtenerClientes()).thenReturn(listClientMock);

        Assertions.assertEquals(new ResponseEntity<>(listClientMock,HttpStatus.OK),controller.obtenerClientes());
    }
    @Test
    void obtenerClientesNull() {
        Mockito.when(portInMock.obtenerClientes()).thenReturn(null);

        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerClientes());
    }

    @Test
    void actualizarCliente() {
        Mockito.when(portInMock.actualizarCliente(clientMock2)).thenReturn(clientMock2);

        Assertions.assertEquals(new ResponseEntity<>(clientMock2,HttpStatus.OK), controller.actualizarCliente(clientMock2));
    }
    @Test
    void actualizarClienteNull() {
        Mockito.when(portInMock.actualizarCliente(clientMock2)).thenReturn(null);

        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST), controller.actualizarCliente(clientMock2));
    }

    @Test
    void registrarCliente() {
        Mockito.when(portInMock.registrarCliente(clientMock1)).thenReturn(clientMock1);

        Assertions.assertEquals(new ResponseEntity<>(clientMock1,HttpStatus.CREATED),controller.registrarCliente(clientMock1));
    }
    @Test
    void registrarClienteNull() {
        Mockito.when(portInMock.registrarCliente(clientMock1)).thenReturn(null);

        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.registrarCliente(clientMock1));
    }

    @Test
    void eliminarCliente() {
        Mockito.when(portInMock.eliminarCliente(1)).thenReturn(true);

        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.OK),controller.eliminarCliente(1));
    }
    @Test
    void eliminarClienteNull() {
        Mockito.when(portInMock.eliminarCliente(1)).thenReturn(false);

        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.eliminarCliente(1));
    }
}