package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.ClientePortIn;
import com.hotel.serviciosHotel.aplicacion.servicio.ClienteService;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import com.hotel.serviciosHotel.dominio.entidades.Service;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.InvalidCharacterException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class GestionarClienteControllerTest {
    private Client client;
    private Client client2;
    private ArrayList<Client> clientArrayList;

    private ClientePortIn service;
    private GestionarClienteController controller=new GestionarClienteController();
    @BeforeEach
    void setUp(){
        service= Mockito.mock(ClienteService.class);
        controller.setService(service);
        clientArrayList=new ArrayList<>();
        client=new Client(
                1,
                "12345678",
                "Juan",
                "Carloss",
                "García",
                "Pérez",
                "5551234567"
        );
        client2=new Client(
                2,
                "12345678",
                "Juan",
                "Carloss",
                "García",
                "Pérez",
                "5551234567"
        );
        clientArrayList.add(client);
        clientArrayList.add(client2);
    }
    @Test
    void obtenerClientePorDocumento(){
        try {
            Mockito.when(service.consultarClientePorDocumento("12345678")).thenReturn(client);
            Assertions.assertEquals(new ResponseEntity<>(client, HttpStatus.OK),controller.obtenerClientePorDocumento("12345678"));
            Mockito.verify(service,Mockito.times(1)).consultarClientePorDocumento("12345678");
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerClientePorDocumento_ResponseNull(){
        try {
            Mockito.when(service.consultarClientePorDocumento("12345678")).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerClientePorDocumento("12345678"));
            Mockito.verify(service,Mockito.times(1)).consultarClientePorDocumento("12345678");
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerClientePorDocumento_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.consultarClientePorDocumento("12345678")).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.obtenerClientePorDocumento("12345678"));
            Mockito.verify(service,Mockito.times(1)).consultarClientePorDocumento("12345678");
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerClientePorId(){
        try {
            Mockito.when(service.consultarClientePorId(1)).thenReturn(client);
            Assertions.assertEquals(new ResponseEntity<>(client,HttpStatus.OK),controller.obtenerClientePorId(1));
            Mockito.verify(service,Mockito.times(1)).consultarClientePorId(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerClientePorId_ResponseNull(){
        try {
            Mockito.when(service.consultarClientePorId(1)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerClientePorId(1));
            Mockito.verify(service,Mockito.times(1)).consultarClientePorId(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerClientePorId_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.consultarClientePorId(1)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.obtenerClientePorId(1));
            Mockito.verify(service,Mockito.times(1)).consultarClientePorId(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerClientes(){
        try {
            Mockito.when(service.obtenerClientes()).thenReturn(clientArrayList);
            Assertions.assertEquals(new ResponseEntity<>(clientArrayList,HttpStatus.OK),controller.obtenerClientes());
            Mockito.verify(service,Mockito.times(1)).obtenerClientes();
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerClientes_ResponseNull(){
        try {
            Mockito.when(service.obtenerClientes()).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.obtenerClientes());
            Mockito.verify(service,Mockito.times(1)).obtenerClientes();
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarCliente(){
        try {
            Mockito.when(service.actualizarCliente(client)).thenReturn(client);
            Assertions.assertEquals(new ResponseEntity<>(client,HttpStatus.OK),controller.actualizarCliente(client));
            Mockito.verify(service,Mockito.times(1)).actualizarCliente(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarCliente_ResponseNull(){
        try {
            Mockito.when(service.actualizarCliente(client)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.actualizarCliente(client));
            Mockito.verify(service,Mockito.times(1)).actualizarCliente(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarCliente_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.actualizarCliente(client)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.actualizarCliente(client));
            Mockito.verify(service,Mockito.times(1)).actualizarCliente(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarCliente_ServiceThrowsInvalidCharacterException(){
        try {
            Mockito.when(service.actualizarCliente(client)).thenThrow(InvalidCharacterException.class);
            Assertions.assertThrows(InvalidCharacterException.class,()->controller.actualizarCliente(client));
            Mockito.verify(service,Mockito.times(1)).actualizarCliente(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarCliente(){
        try {
            Mockito.when(service.registrarCliente(client)).thenReturn(client);
            Assertions.assertEquals(new ResponseEntity<>(client,HttpStatus.CREATED),controller.registrarCliente(client));
            Mockito.verify(service,Mockito.times(1)).registrarCliente(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarCliente_ResponseNull(){
        try {
            Mockito.when(service.registrarCliente(client)).thenReturn(null);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.registrarCliente(client));
            Mockito.verify(service,Mockito.times(1)).registrarCliente(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarCliente_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.registrarCliente(client)).thenThrow(ItemAlreadyExistException.class);
            Assertions.assertThrows(ItemAlreadyExistException.class,()->controller.registrarCliente(client));
            Mockito.verify(service,Mockito.times(1)).registrarCliente(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarCliente_ServiceThrowsInvalidCharacterException(){
        try {
            Mockito.when(service.registrarCliente(client)).thenThrow(InvalidCharacterException.class);
            Assertions.assertThrows(InvalidCharacterException.class,()->controller.registrarCliente(client));
            Mockito.verify(service,Mockito.times(1)).registrarCliente(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarCliente(){
        try {
            Mockito.when(service.eliminarCliente(1)).thenReturn(true);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.OK),controller.eliminarCliente(1));
            Mockito.verify(service,Mockito.times(1)).eliminarCliente(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarCliente_ResponseFalse(){
        try {
            Mockito.when(service.eliminarCliente(1)).thenReturn(false);
            Assertions.assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),controller.eliminarCliente(1));
            Mockito.verify(service,Mockito.times(1)).eliminarCliente(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarCliente_ServiceThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(service.eliminarCliente(1)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->controller.eliminarCliente(1));
            Mockito.verify(service,Mockito.times(1)).eliminarCliente(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
