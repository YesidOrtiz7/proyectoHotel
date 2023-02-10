package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.adaptador.out.db.ReceptionistRepository;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ReceptionistPortOut;
import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class RecepcionistaServiceTest {

    private RecepcionistaService service;

    private ReceptionistPortOut portOutMock;
    private ReceptionistEntity receptionistMock1,receptionistMock2;
    private List<ReceptionistEntity> listReceptionistMock=new ArrayList<>();

    @BeforeEach
    void setUp() {
        receptionistMock1=new ReceptionistEntity(1,"789","maria","delmar");
        receptionistMock2=new ReceptionistEntity(2,"222","nombre2","apellido2");
        listReceptionistMock.add(receptionistMock1);
        listReceptionistMock.add(receptionistMock2);

        portOutMock= Mockito.mock(ReceptionistRepository.class);

        service=new RecepcionistaService();
        service.setPortOut(portOutMock);
    }

    @Test
    void registrarRecepcionista() {
        Mockito.when(portOutMock.saveRecepcionist(receptionistMock1)).thenReturn(receptionistMock1);

        Assertions.assertEquals(receptionistMock1,service.registrarRecepcionista(receptionistMock1));
    }

    @Test
    void actualizarRecepcionista() {
        Mockito.when(portOutMock.updateRecepcionist(receptionistMock1)).thenReturn(receptionistMock1);

        Assertions.assertEquals(receptionistMock1,service.actualizarRecepcionista(receptionistMock1));
    }

    @Test
    void obtenerRecepcionistaPorId() {
        Mockito.when(portOutMock.getRecepcionistById(2)).thenReturn(Optional.of(receptionistMock2));

        Assertions.assertEquals(receptionistMock2,service.obtenerRecepcionistaPorId(2));
    }
    @Test
    void obtenerRecepcionistaPorIdNull() {
        Mockito.when(portOutMock.getRecepcionistById(2)).thenReturn(null);

        Assertions.assertEquals(null,service.obtenerRecepcionistaPorId(2));
    }
    @Test
    void obtenerRecepcionistaPorIdEmpty() {
        Mockito.when(portOutMock.getRecepcionistById(2)).thenReturn(Optional.empty());

        Assertions.assertEquals(null,service.obtenerRecepcionistaPorId(2));
    }

    @Test
    void obtenerRecepcionistaPorDocumento() {
        Mockito.when(portOutMock.getRecepcionistByDocument("222")).thenReturn(Optional.of(receptionistMock2));

        Assertions.assertEquals(receptionistMock2, service.obtenerRecepcionistaPorDocumento("222"));
    }
    @Test
    void obtenerRecepcionistaPorDocumentoNull() {
        Mockito.when(portOutMock.getRecepcionistByDocument("222")).thenReturn(null);

        Assertions.assertEquals(null, service.obtenerRecepcionistaPorDocumento("222"));
    }
    @Test
    void obtenerRecepcionistaPorDocumentoEmpty() {
        Mockito.when(portOutMock.getRecepcionistByDocument("222")).thenReturn(Optional.empty());

        Assertions.assertEquals(null, service.obtenerRecepcionistaPorDocumento("222"));
    }

    @Test
    void obtenerRecepcionistas() {
        Mockito.when(portOutMock.getRecepcionist()).thenReturn(listReceptionistMock);

        Assertions.assertEquals(listReceptionistMock,service.obtenerRecepcionistas());
    }
    @Test
    void obtenerRecepcionistasNull() {
        Mockito.when(portOutMock.getRecepcionist()).thenReturn(null);

        Assertions.assertEquals(null,service.obtenerRecepcionistas());
    }
    @Test
    void obtenerRecepcionistasEmpty() {
        Mockito.when(portOutMock.getRecepcionist()).thenReturn(new ArrayList<>());

        Assertions.assertEquals(null,service.obtenerRecepcionistas());
    }

    @Test
    void eliminarRecepcionistaPorId() {
        Mockito.when(portOutMock.deleteRecepcionistById(1)).thenReturn(true);

        Assertions.assertEquals(true,service.eliminarRecepcionistaPorId(1));
    }

    @Test
    void eliminarRecepcionista() {
        Mockito.when(portOutMock.deleteRecepcionist(receptionistMock1)).thenReturn(true);

        Assertions.assertEquals(true,service.eliminarRecepcionista(receptionistMock1));
    }
}