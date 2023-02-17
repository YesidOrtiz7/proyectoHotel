package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.adaptador.modelResponse.UpdateRateServiceRequest;
import com.hotel.serviciosHotel.adaptador.modelResponse.UpdateRoomServiceRequest;
import com.hotel.serviciosHotel.adaptador.out.db.ServiceRepository;
import com.hotel.serviciosHotel.aplicacion.puerto.in.HabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.TarifaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ServicePortOut;
import com.hotel.serviciosHotel.dominio.entidades.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
                    10000,
                    4,
                    2),
            new RateType(1,"normal",0),
            new Municipios(1,"municipio1"),
            new Municipios(2,"municipio2"),
            new PaymentType(3,"efectivo"),
            1,
            LocalDateTime.now(),
            LocalDateTime.of(LocalDateTime.now().getYear(),
                    LocalDateTime.now().getMonth(),
                    LocalDateTime.now().getDayOfMonth(),
                    LocalDateTime.now().getHour()+1,
                    LocalDateTime.now().getMinute()),
            1);
    private Service responseMock2=new Service(2,
            new ReceptionistEntity(1,"789","maria","delmar"),
            new Client(1,"456","juan","","perez","","32123"),
            new Room(
                    302,
                    new RoomStatus(2,"sucia"),
                    new RoomType(1,"doble"),
                    10000,
                    4,
                    2),
            new RateType(1,"normal",0),
            new Municipios(1,"municipio1"),
            new Municipios(2,"municipio2"),
            new PaymentType(3,"efectivo"),
            1,
            LocalDateTime.now(),
            LocalDateTime.now(),
            1);
    private Service responseMock3=new Service(2,
            new ReceptionistEntity(1,"789","maria","delmar"),
            new Client(1,"456","juan","","perez","","32123"),
            new Room(
                    302,
                    new RoomStatus(2,"sucia"),
                    new RoomType(1,"doble"),
                    10000,
                    4,
                    1),
            new RateType(1,"normal",0),
            new Municipios(1,"municipio1"),
            new Municipios(2,"municipio2"),
            new PaymentType(3,"efectivo"),
            1,
            LocalDateTime.now(),
            LocalDateTime.now(),
            1);
    private ReceptionistEntity receptionistMock=new ReceptionistEntity(1,"789","maria","delmar");
    private Room roomMock =new Room(302,new RoomStatus(3,"ocupada"),
                    new RoomType(3,"doble"),
                    10000,
                    4,2);
    private Room roomMock2 =new Room(302,new RoomStatus(2,"sucia"),
            new RoomType(3,"doble"),
            10000,
            4,1);
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
        Mockito.when(portOutMock.registrarServicio(responseMock3)).thenReturn(responseMock3);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(
                new Room(
                        302,
                        new RoomStatus(2,"sucia"),
                        new RoomType(1,"doble"),
                        10000,
                        4,
                        1)
        );

        Assertions.assertEquals(responseMock3,service.registrarServicio(responseMock3));
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
    void actualizarServicioHabitacionOcupada() {
        Mockito.when(portOutMock.servicioExiste(responseMock1)).thenReturn(true);

        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Assertions.assertEquals(responseMock1,service.actualizarServicioHabitacionOcupada(responseMock1));
    }

    @Test
    void actualizarServicioHabitacionOcupadaSinServicio() {
        Mockito.when(portOutMock.servicioExiste(responseMock1)).thenReturn(false);

        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Assertions.assertEquals(null,service.actualizarServicioHabitacionOcupada(responseMock1));
    }
    @Test
    void actualizarServicioHabitacionOcupadaSinRecepcionista() {
        Mockito.when(portOutMock.servicioExiste(responseMock1)).thenReturn(true);

        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(null);

        Assertions.assertEquals(null,service.actualizarServicioHabitacionOcupada(responseMock1));
    }

    @Test
    void actualizarServicioRecepcionistaNull() {
        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(null);

        Assertions.assertEquals(null,service.actualizarServicioHabitacionOcupada(responseMock1));
    }
    @Test
    void actualizarServicioHabDesocupada() {
        Mockito.when(portOutMock.actualizarServicio(responseMock2)).thenReturn(responseMock2);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Assertions.assertEquals(null,service.actualizarServicioHabitacionOcupada(responseMock2));
    }

    @Test
    void actualizarHabitacionServicio() {
        UpdateRoomServiceRequest request=new UpdateRoomServiceRequest(1,301);

        Mockito.when(portOutMock.actualizarServicio(responseMock2)).thenReturn(responseMock2);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Mockito.when(habitacionServiceMock.getRoomByNumber(301)).thenReturn(roomMock);
        Mockito.when(portOutMock.consultarServicioPorId(request.getIdService())).thenReturn(responseMock2);

        Mockito.when(portOutMock.servicioExiste(responseMock2)).thenReturn(true);

        Assertions.assertEquals(responseMock2,service.actualizarHabitacionServicio(request.getIdService(),request.getRoomNumber()));
    }
    @Test
    void actualizarHabitacionServicioNull() {
        UpdateRoomServiceRequest request=new UpdateRoomServiceRequest(1,301);

        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Mockito.when(habitacionServiceMock.getRoomByNumber(302)).thenReturn(null);

        Assertions.assertEquals(null,service.actualizarHabitacionServicio(request.getIdService(),request.getRoomNumber()));
    }
    @Test
    void actualizarHabitacionId0() {
        UpdateRoomServiceRequest request=new UpdateRoomServiceRequest(1,301);

        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Mockito.when(habitacionServiceMock.getRoomByNumber(302)).thenReturn(new Room());

        Assertions.assertEquals(null,service.actualizarHabitacionServicio(request.getIdService(),request.getRoomNumber()));
    }

    @Test
    void actualizarTarifaServicio() {
        UpdateRateServiceRequest request=new UpdateRateServiceRequest(1,1);

        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMock1);
        Mockito.when(portOutMock.servicioExiste(responseMock1)).thenReturn(true);

        Mockito.when(tarifaServiceMock.obtenerTarifaPorId(1)).thenReturn(new RateType(1,"normal",0));

        Assertions.assertEquals(responseMock1,service.actualizarTarifaServicio(request.getIdService(),request.getRateId()));
    }
    @Test
    void actualizarTarifaServicioTarifaNull() {
        UpdateRateServiceRequest request=new UpdateRateServiceRequest(1,1);

        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Mockito.when(tarifaServiceMock.obtenerTarifaPorId(1)).thenReturn(null);

        Assertions.assertEquals(null,service.actualizarTarifaServicio(request.getIdService(), request.getRateId()));
    }
    @Test
    void actualizarTarifaServicioIdTarifa0() {
        UpdateRateServiceRequest request=new UpdateRateServiceRequest(1,1);

        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Mockito.when(tarifaServiceMock.obtenerTarifaPorId(1)).thenReturn(new RateType());

        Assertions.assertEquals(null,service.actualizarTarifaServicio(request.getIdService(), request.getRateId()));
    }

    @Test
    void cerrarServicio() {

        Mockito.when(portOutMock.servicioExiste(responseMock1)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(new ReceptionistEntity(1,"789","maria","delmar"));
        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomMock);

        Assertions.assertEquals(responseMock1,service.cerrarServicio(responseMock1));
    }

    @Test
    void cerrarServicioPorIdServicio() {
        Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomMock);
        Mockito.when(portOutMock.servicioExiste(responseMock1)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(new ReceptionistEntity(1,"789","maria","delmar"));
        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(service.consultarServicioPorId(responseMock1.getIdService())).thenReturn(responseMock1);

        Assertions.assertEquals(responseMock1,service.cerrarServicioPorIdServicio(responseMock1.getIdService()));
    }

    @Test
    void ampliarServicio() {
        Mockito.when(portOutMock.servicioExiste(responseMock1)).thenReturn(true);

        Mockito.when(portOutMock.actualizarServicio(responseMock1)).thenReturn(responseMock1);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);

        Assertions.assertEquals(responseMock1,service.ampliarServicio(responseMock1,1,0,0));
    }
}