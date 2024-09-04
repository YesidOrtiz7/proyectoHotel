package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.adaptador.out.db.ClientRepository;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ClientPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.InvalidCharacterException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class ClienteServiceTest {
    private Client client;
    private Client client2;
    private ArrayList<Client> clientArrayList;

    private ClientPortOut repository;
    private ClienteService service=new ClienteService();
    @BeforeEach
    void setUp(){
        repository= Mockito.mock(ClientRepository.class);
        service.setPortOut(repository);
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
    void obtenerClientes(){
        Mockito.when(repository.getClients()).thenReturn(clientArrayList);
        service.obtenerClientes();
        Mockito.verify(repository,Mockito.times(1)).getClients();
    }
    @Test
    void registrarCliente(){
        try {
            Mockito.when(repository.clientExist(1)).thenReturn(false);
            Mockito.when(repository.saveClient(client)).thenReturn(client);
            Assertions.assertEquals(client,service.registrarCliente(client));
            Mockito.verify(repository,Mockito.times(1)).saveClient(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarCliente_ClientAlreadyExist(){
        try {
            Mockito.when(repository.clientExist(1)).thenReturn(true);
            Mockito.when(repository.saveClient(client)).thenReturn(client);
            Assertions.assertThrows(ItemAlreadyExistException.class,()->service.registrarCliente(client));
            Mockito.verify(repository,Mockito.times(0)).saveClient(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarCliente_RepositoryThrowsItemAlreadyExistException(){
        try {
            Mockito.when(repository.clientExist(1)).thenReturn(false);
            Mockito.when(repository.saveClient(client)).thenThrow(ItemAlreadyExistException.class);
            Assertions.assertThrows(ItemAlreadyExistException.class,()->service.registrarCliente(client));
            Mockito.verify(repository,Mockito.times(1)).saveClient(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarCliente_DocumentoInvalido(){
        try {
            client.setDocumentoCliente(client.getDocumentoCliente()+"A");
            Mockito.when(repository.clientExist(1)).thenReturn(false);
            Mockito.when(repository.saveClient(client)).thenReturn(client);
            Assertions.assertThrows(InvalidCharacterException.class,()->service.registrarCliente(client));
            Mockito.verify(repository,Mockito.times(0)).saveClient(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarCliente_CelularInvalido(){
        try {
            client.setCelularCliente(client.getCelularCliente()+"A");
            Mockito.when(repository.clientExist(1)).thenReturn(false);
            Mockito.when(repository.saveClient(client)).thenReturn(client);
            Assertions.assertThrows(InvalidCharacterException.class,()->service.registrarCliente(client));
            Mockito.verify(repository,Mockito.times(0)).saveClient(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarCliente(){
        try {
            Mockito.when(repository.clientExist(2)).thenReturn(true);
            Mockito.when(repository.updateClient(client2)).thenReturn(client2);
            Assertions.assertEquals(client2,service.actualizarCliente(client2));
            Mockito.verify(repository,Mockito.times(1)).updateClient(client2);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarCliente_ClientDontExist(){
        try {
            Mockito.when(repository.clientExist(2)).thenReturn(false);
            Mockito.when(repository.updateClient(client2)).thenReturn(client2);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.actualizarCliente(client2));
            Mockito.verify(repository,Mockito.times(0)).updateClient(client2);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarCliente_RepositoryThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repository.clientExist(2)).thenReturn(true);
            Mockito.when(repository.updateClient(client2)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.actualizarCliente(client2));
            Mockito.verify(repository,Mockito.times(1)).updateClient(client2);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarCliente_DocumentoInvalido(){
        try {
            client.setDocumentoCliente(client.getDocumentoCliente()+"A");
            Mockito.when(repository.clientExist(1)).thenReturn(false);
            Mockito.when(repository.updateClient(client)).thenReturn(client);
            Assertions.assertThrows(InvalidCharacterException.class,()->service.actualizarCliente(client));
            Mockito.verify(repository,Mockito.times(0)).updateClient(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarCliente_CelularInvalido(){
        try {
            client.setCelularCliente(client.getCelularCliente()+"A");
            Mockito.when(repository.clientExist(1)).thenReturn(false);
            Mockito.when(repository.updateClient(client)).thenReturn(client);
            Assertions.assertThrows(InvalidCharacterException.class,()->service.actualizarCliente(client));
            Mockito.verify(repository,Mockito.times(0)).updateClient(client);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarCliente(){
        try {
            Mockito.when(repository.clientExist(1)).thenReturn(true);
            Mockito.when(repository.deleteClientById(1)).thenReturn(true);
            Assertions.assertTrue(service.eliminarCliente(1));
            Mockito.verify(repository,Mockito.times(1)).deleteClientById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarCliente_ClientDontExist(){
        try {
            Mockito.when(repository.clientExist(1)).thenReturn(false);
            Mockito.when(repository.deleteClientById(1)).thenReturn(true);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.eliminarCliente(1));
            Mockito.verify(repository,Mockito.times(0)).deleteClientById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarCliente_RepositoryThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repository.clientExist(1)).thenReturn(true);
            Mockito.when(repository.deleteClientById(1)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.eliminarCliente(1));
            Mockito.verify(repository,Mockito.times(1)).deleteClientById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void consultarClientePorId(){
        try {
            Mockito.when(repository.clientExist(1)).thenReturn(true);
            Mockito.when(repository.getClientById(1)).thenReturn(client);
            Assertions.assertEquals(client,service.consultarClientePorId(1));
            Mockito.verify(repository,Mockito.times(1)).getClientById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void consultarClientePorId_ClientDontExist(){
        try {
            Mockito.when(repository.clientExist(1)).thenReturn(false);
            Mockito.when(repository.getClientById(1)).thenReturn(client);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.consultarClientePorId(1));
            Mockito.verify(repository,Mockito.times(0)).getClientById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void consultarClientePorId_RepositoryThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repository.clientExist(1)).thenReturn(true);
            Mockito.when(repository.getClientById(1)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.consultarClientePorId(1));
            Mockito.verify(repository,Mockito.times(1)).getClientById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void consultarClientePorDocumento(){
        try {
            Mockito.when(repository.getClientByDocument("12345678")).thenReturn(client);
            Assertions.assertEquals(client,service.consultarClientePorDocumento("12345678"));
            Mockito.verify(repository,Mockito.times(1)).getClientByDocument("12345678");
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void consultarClientePorDocumento_RepositoryThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repository.getClientByDocument("12345678")).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.consultarClientePorDocumento("12345678"));
            Mockito.verify(repository,Mockito.times(1)).getClientByDocument("12345678");
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
