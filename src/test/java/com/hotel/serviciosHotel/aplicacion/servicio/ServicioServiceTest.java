package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.adaptador.out.db.ServiceRepository;
import com.hotel.serviciosHotel.aplicacion.puerto.in.HabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.TarifaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ServicePortOut;
import com.hotel.serviciosHotel.dominio.entidades.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ServicioServiceTest {

    private ServicePortOut portOutMock;
    private HabitacionPortIn habitacionServiceMock;
    private TarifaPortIn tarifaServiceMock;
    private RecepcionistaPortIn recepcionistaServiceMock;

    private ServicioService service;

    private Service responseMock1=new Service(1,
            new ReceptionistEntity(1,"789","maria","delmar"),
            new Client(1,"456","juan","","perez","","32123"),
            new Room(
                    302,
                    new RoomStatus(3,"ocupada"),
                    new RoomType(1,"doble"),
                    4,
                    2),
            new RateType(1,"normal"),
            new Municipios(1,"municipio1"),
            new Municipios(2,"municipio2"),
            new PaymentType(3,"efectivo"),
            1,
            LocalDateTime.now(),
            LocalDateTime.now(),
            0);
    private Service responseMock2=new Service(1,
            new ReceptionistEntity(1,"789","maria","delmar"),
            new Client(1,"456","juan","","perez","","32123"),
            new Room(
                    302,
                    new RoomStatus(1,"lipia"),
                    new RoomType(1,"doble"),
                    4,
                    2),
            new RateType(1,"normal"),
            new Municipios(1,"municipio1"),
            new Municipios(2,"municipio2"),
            new PaymentType(3,"efectivo"),
            1,
            LocalDateTime.now(),
            LocalDateTime.now(),
            0);
    private ReceptionistEntity receptionistMock=new ReceptionistEntity(1,"789","maria","delmar");
    private Room roomMock =new Room(302,new RoomStatus(3,"ocupada"),
                    new RoomType(3,"doble"),
                    4,2);
    private List<Service> listServiceMock;

    @BeforeEach
    void setUp() {
        portOutMock= Mockito.mock(ServiceRepository.class);
        habitacionServiceMock =Mockito.mock(HabitacionService.class);
        tarifaServiceMock =Mockito.mock(TarifasService.class);
        recepcionistaServiceMock =Mockito.mock(RecepcionistaService.class);

        listServiceMock=new ArrayList<>();
        listServiceMock.add(responseMock1);
        listServiceMock.add(responseMock2);

        service=new ServicioService();
        service.setPortOut(portOutMock);
        service.setHabitacionService(habitacionServiceMock);
        service.setRecepcionistaService(recepcionistaServiceMock);
        service.setTarifaService(tarifaServiceMock);
    }

    @Test
    void consultarServicioPorId() {
        Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMock1);

        Assertions.assertEquals(responseMock1, service.consultarServicioPorId(1));
    }

    @Test
    void consultarServicios() {
        Mockito.when(portOutMock.consultarServicios()).thenReturn(listServiceMock);

        Assertions.assertEquals(listServiceMock, service.consultarServicios());
    }

    @Test
    void registrarServicio() {
        Mockito.when(portOutMock.registrarServicio(responseMock2)).thenReturn(responseMock2);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Assertions.assertEquals(responseMock2,service.registrarServicio(responseMock2));
    }
    @Test
    void registrarServicioRecepcionistaNull() {
        Mockito.when(portOutMock.registrarServicio(responseMock2)).thenReturn(responseMock2);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(null);

        Assertions.assertEquals(null,service.registrarServicio(responseMock2));
    }
    @Test
    void registrarServicioHabOcupada() {
        Mockito.when(portOutMock.registrarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        /*la habitacion que se pasa por parametro tiene una habitacion ocupada*/
        Assertions.assertEquals(null,service.registrarServicio(responseMock1));
    }

    @Test
    void actualizarServicio() {
        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Assertions.assertEquals(responseMock1,service.actualizarServicio(responseMock1));
    }
    @Test
    void actualizarServicioRecepcionistaNull() {
        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(null);

        Assertions.assertEquals(null,service.actualizarServicio(responseMock1));
    }
    @Test
    void actualizarServicioHabDesocupada() {
        Mockito.when(portOutMock.actualizarServicio(responseMock2)).thenReturn(responseMock2);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Assertions.assertEquals(null,service.actualizarServicio(responseMock2));
    }

    @Test
    void actualizarHabitacionServicio() {
        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Mockito.when(habitacionServiceMock.getRoomByNumber(302)).thenReturn(roomMock);

        Assertions.assertEquals(responseMock1,service.actualizarHabitacionServicio(responseMock1,302));
    }
    @Test
    void actualizarHabitacionServicioNull() {
        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Mockito.when(habitacionServiceMock.getRoomByNumber(302)).thenReturn(null);

        Assertions.assertEquals(null,service.actualizarHabitacionServicio(responseMock1,302));
    }
    @Test
    void actualizarHabitacionId0() {
        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Mockito.when(habitacionServiceMock.getRoomByNumber(302)).thenReturn(new Room());

        Assertions.assertEquals(null,service.actualizarHabitacionServicio(responseMock1,302));
    }

    @Test
    void actualizarTarifaServicio() {
        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Mockito.when(tarifaServiceMock.obtenerTarifaPorId(1)).thenReturn(new RateType(1,"normal"));

        Assertions.assertEquals(responseMock1,service.actualizarTarifaServicio(responseMock1,1));
    }
    @Test
    void actualizarTarifaServicioTarifaNull() {
        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Mockito.when(tarifaServiceMock.obtenerTarifaPorId(1)).thenReturn(null);

        Assertions.assertEquals(null,service.actualizarTarifaServicio(responseMock1,1));
    }
    @Test
    void actualizarTarifaServicioIdTarifa0() {
        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Mockito.when(tarifaServiceMock.obtenerTarifaPorId(1)).thenReturn(new RateType());

        Assertions.assertEquals(null,service.actualizarTarifaServicio(responseMock1,1));
    }

    /*@Test
    void actualizarTipoPagoServicio() {
    }*/

    /*@Test
    void obtenerRecepcionista() {
    }

    @Test
    void determinarOcupacionHabitacion() {
    }*/
}