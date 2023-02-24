package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.adaptador.out.db.ClientRepository;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ClientPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ClienteServiceTest {

    private ClientPortOut portOutMock;
    private Client c1=new Client(1,"489984","primerNombre","segundoNombre","primerApellido","segundoApellido","1111");
    private Client c2=new Client(2,"22585","primerNombre","segundoNombre","primerApellido","segundoApellido","2222");
    private List<Client> mockResponse;

    private ClienteService service;

    @BeforeEach
    void setUp() {
        portOutMock= Mockito.mock(ClientRepository.class);

        mockResponse =new ArrayList<>();
        mockResponse.add(c1);
        mockResponse.add(c2);

        service=new ClienteService();
        service.setPortOut(portOutMock);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void obtenerClientes() {
        Mockito.when(portOutMock.getClients()).thenReturn(mockResponse);

        List<Client> responseQuery=service.obtenerClientes();

        Assertions.assertEquals(mockResponse, responseQuery);
    }

    @Test
    void obtenerClientesConListadoNull() {
        Mockito.when(portOutMock.getClients()).thenReturn(null);

        Assertions.assertEquals(null, service.obtenerClientes());
    }

    @Test
    void registrarCliente() {
        Mockito.when(portOutMock.saveClient(c1)).thenReturn(c1);

        Assertions.assertEquals(c1, service.registrarCliente(c1));
    }

    @Test
    void actualizarCliente() {
        Mockito.when(portOutMock.updateClient(c1)).thenReturn(c1);

        Assertions.assertEquals(c1,service.actualizarCliente(c1));
    }

    @Test
    void eliminarCliente() {
        Mockito.when(portOutMock.deleteClientById(1)).thenReturn(true);
        Assertions.assertEquals(true, service.eliminarCliente(1));
    }

    @Test
    void consultarClientePorId() {
        Mockito.when(portOutMock.getClientById(1)).thenReturn(Optional.of(c1));

        Assertions.assertEquals(c1,service.consultarClientePorId(1));
    }

    @Test
    void consultarClientePorIdNull() {
        Mockito.when(portOutMock.getClientById(1)).thenReturn(null);

        Assertions.assertEquals(null,service.consultarClientePorId(1));
    }

    @Test
    void consultarClientePorDocumento() {
        Mockito.when(portOutMock.getClientByDocument("489984")).thenReturn(Optional.of(c1));

        Assertions.assertEquals(c1,service.consultarClientePorDocumento("489984"));
    }

    @Test
    void consultarClientePorDocumentoNull() {
        Mockito.when(portOutMock.getClientByDocument("489984")).thenReturn(null);

        Assertions.assertEquals(null,service.consultarClientePorDocumento("489984"));
    }
}