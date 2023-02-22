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

    private Service responseMockHabOcupada =new Service(1,
            new ReceptionistEntity(1,"789","maria","delmar"),
            new Client(1,"456","juan","","perez","","32123"),
            new Room(
                    301,
                    new RoomStatus(3,"ocupada"),
                    new RoomType(1,"doble"),
                    10000,
                    4,
                    1),
            new RateType(1,"normal",10),
            new Municipios(1,"municipio1"),
            new Municipios(2,"municipio2"),
            new PaymentType(3,"efectivo"),
            0,
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(1).plusMinutes(30),
            1);
    private Service responseMockHabLimpia =new Service(1,
            new ReceptionistEntity(1,"789","maria","delmar"),
            new Client(1,"456","juan","","perez","","32123"),
            new Room(
                    301,
                    new RoomStatus(1,"limpia"),
                    new RoomType(1,"doble"),
                    10000,
                    4,
                    1),
            new RateType(1,"normal",10),
            new Municipios(1,"municipio1"),
            new Municipios(2,"municipio2"),
            new PaymentType(3,"efectivo"),
            0,
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(1),
            1);
    private ReceptionistEntity receptionistMock=new ReceptionistEntity(
            1,
            "789",
            "maria",
            "delmar");
    private Room habOcupadaMock =new Room(
            301,
            new RoomStatus(3,"ocupada"),
            new RoomType(1,"doble"),
            10000,
            4,
            1);
    private Room habDesocupadaMock =new Room(
            301,
            new RoomStatus(1,"limpia"),
            new RoomType(1,"doble"),
            10000,
            4,
            1);
    private List<Service> listServiceMock;

    @BeforeEach
    void setUp() {
        portOutMock= Mockito.mock(ServiceRepository.class);
        habitacionServiceMock =Mockito.mock(HabitacionService.class);
        tarifaServiceMock =Mockito.mock(TarifasService.class);
        recepcionistaServiceMock =Mockito.mock(RecepcionistaService.class);

        listServiceMock=new ArrayList<>();
        listServiceMock.add(responseMockHabOcupada);
        listServiceMock.add(responseMockHabLimpia);

        service=new ServicioService();
        service.setPortOut(portOutMock);
        service.setHabitacionService(habitacionServiceMock);
        service.setRecepcionistaService(recepcionistaServiceMock);
        service.setTarifaService(tarifaServiceMock);
    }

    @Test
    void consultarServicioPorId() {
        Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabOcupada);

        Assertions.assertEquals(responseMockHabOcupada, service.consultarServicioPorId(1));
    }

    @Test
    void consultarServicios() {
        Mockito.when(portOutMock.consultarServicios()).thenReturn(listServiceMock);

        Assertions.assertEquals(listServiceMock, service.consultarServicios());
    }

    @Test
    void registrarServicio() {
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habDesocupadaMock);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.registrarServicio(responseMockHabLimpia)).thenReturn(responseMockHabLimpia);

        Assertions.assertEquals(responseMockHabLimpia,service.registrarServicio(responseMockHabLimpia));
    }
    @Test
    void registrarServicio_RecepcionistaNull() {
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habDesocupadaMock);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(null);
        Mockito.when(portOutMock.registrarServicio(responseMockHabLimpia)).thenReturn(responseMockHabLimpia);

        Assertions.assertEquals(null,service.registrarServicio(responseMockHabLimpia));
    }
    @Test
    void registrarServicioHabOcupada() {
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.registrarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

        Assertions.assertEquals(null,service.registrarServicio(responseMockHabOcupada));
    }

    @Test
    void actualizarServicioHabitacionOcupada() {
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);

        Assertions.assertEquals(responseMockHabOcupada,service.actualizarServicioHabitacionOcupada(responseMockHabOcupada));
    }

    @Test
    void actualizarServicioHabitacionOcupada_SinServicio() {
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(false);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);

        Assertions.assertEquals(null,service.actualizarServicioHabitacionOcupada(responseMockHabOcupada));
    }
    @Test
    void actualizarServicioHabitacionOcupada_SinRecepcionista() {
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(null);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);

        Assertions.assertEquals(null,service.actualizarServicioHabitacionOcupada(responseMockHabOcupada));
    }

    @Test
    void actualizarServicioParaCerrarServicio() {
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);
        Mockito.when(habitacionServiceMock.changeRoomStatus(habOcupadaMock.getRoomNumber(),2)).thenReturn(habOcupadaMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

        Assertions.assertEquals(responseMockHabOcupada,service.actualizarServicioParaCerrarServicio(responseMockHabOcupada));
    }

    @Test
    void actualizarServicioParaCerrarServicio_SinServicio() {
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(false);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);
        Mockito.when(habitacionServiceMock.changeRoomStatus(habOcupadaMock.getRoomNumber(),2)).thenReturn(habOcupadaMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

        Assertions.assertEquals(null,service.actualizarServicioParaCerrarServicio(responseMockHabOcupada));
    }

    @Test
    void actualizarServicioParaCerrarServicio_RecepcionistaNull() {
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(null);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);
        Mockito.when(habitacionServiceMock.changeRoomStatus(habOcupadaMock.getRoomNumber(),2)).thenReturn(habOcupadaMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

        Assertions.assertEquals(null,service.actualizarServicioParaCerrarServicio(responseMockHabOcupada));
    }

    @Test
    void actualizarServicioParaCerrarServicio_HabitacionDesocupada() {
        Mockito.when(portOutMock.servicioExiste(responseMockHabLimpia)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habDesocupadaMock);
        Mockito.when(habitacionServiceMock.changeRoomStatus(habDesocupadaMock.getRoomNumber(),2)).thenReturn(habDesocupadaMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabLimpia)).thenReturn(responseMockHabLimpia);

        Assertions.assertEquals(null,service.actualizarServicioParaCerrarServicio(responseMockHabLimpia));
    }

    @Test
    void actualizarHabitacionServicio() {
        UpdateRoomServiceRequest request=new UpdateRoomServiceRequest(1,301);

        /*para el metodo actualizarHabitacionServicio*/
        Mockito.when(portOutMock.consultarServicioPorId(request.getIdService())).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomByNumber(request.getRoomNumber())).thenReturn(habOcupadaMock);

        /*para el metodo actualizarServicioHabitacionOcupada*/
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);

        Assertions.assertEquals(responseMockHabOcupada,service.actualizarHabitacionServicio(request.getIdService(),request.getRoomNumber()));
    }
    @Test
    void actualizarHabitacionServicio_ServicioNull() {
        UpdateRoomServiceRequest request=new UpdateRoomServiceRequest(1,301);

        /*para el metodo actualizarHabitacionServicio*/
        Mockito.when(portOutMock.consultarServicioPorId(request.getIdService())).thenReturn(null);
        Mockito.when(habitacionServiceMock.getRoomByNumber(request.getRoomNumber())).thenReturn(habOcupadaMock);

        /*para el metodo actualizarServicioHabitacionOcupada*/
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);

        Assertions.assertEquals(null,service.actualizarHabitacionServicio(request.getIdService(),request.getRoomNumber()));
    }

    @Test
    void actualizarHabitacionServicio_HabitacionNull() {
        UpdateRoomServiceRequest request=new UpdateRoomServiceRequest(1,301);

        /*para el metodo actualizarHabitacionServicio*/
        Mockito.when(portOutMock.consultarServicioPorId(request.getIdService())).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomByNumber(request.getRoomNumber())).thenReturn(null);

        /*para el metodo actualizarServicioHabitacionOcupada*/
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);

        Assertions.assertEquals(null,service.actualizarHabitacionServicio(request.getIdService(),request.getRoomNumber()));
    }
    @Test
    void actualizarHabitacionServicio_IdRoom0() {
        UpdateRoomServiceRequest request=new UpdateRoomServiceRequest(1,301);

        /*para el metodo actualizarHabitacionServicio*/
        Mockito.when(portOutMock.consultarServicioPorId(request.getIdService())).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomByNumber(request.getRoomNumber())).thenReturn(new Room());

        /*para el metodo actualizarServicioHabitacionOcupada*/
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);

        Assertions.assertEquals(null,service.actualizarHabitacionServicio(request.getIdService(),request.getRoomNumber()));
    }

    @Test
    void actualizarTarifaServicio() {
        UpdateRateServiceRequest request=new UpdateRateServiceRequest(1,1);

        /*para el mteodo actualizarTarifaServicio*/
        Mockito.when(portOutMock.consultarServicioPorId(request.getIdService())).thenReturn(responseMockHabOcupada);
        Mockito.when(tarifaServiceMock.obtenerTarifaPorId(request.getRateId())).thenReturn(
                new RateType(1,"tarifa normal",0)
        );

        /*para el metodo actualizarServicioHabitacionOcupada*/
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);

        Assertions.assertEquals(responseMockHabOcupada,service.actualizarTarifaServicio(request.getIdService(), request.getRateId()));
    }
    @Test
    void actualizarTarifaServicio_TarifaNull() {
        UpdateRateServiceRequest request=new UpdateRateServiceRequest(1,1);

        /*para el mteodo actualizarTarifaServicio*/
        Mockito.when(portOutMock.consultarServicioPorId(request.getIdService())).thenReturn(responseMockHabOcupada);
        Mockito.when(tarifaServiceMock.obtenerTarifaPorId(request.getRateId())).thenReturn(
                null
        );

        /*para el metodo actualizarServicioHabitacionOcupada*/
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);

        Assertions.assertEquals(null,service.actualizarTarifaServicio(request.getIdService(), request.getRateId()));
    }
    @Test
    void actualizarTarifaServicio_IdTarifa0() {
        UpdateRateServiceRequest request=new UpdateRateServiceRequest(1,1);

        /*para el mteodo actualizarTarifaServicio*/
        Mockito.when(portOutMock.consultarServicioPorId(request.getIdService())).thenReturn(responseMockHabOcupada);
        Mockito.when(tarifaServiceMock.obtenerTarifaPorId(request.getRateId())).thenReturn(
                new RateType()
        );

        /*para el metodo actualizarServicioHabitacionOcupada*/
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);

        Assertions.assertEquals(null,service.actualizarTarifaServicio(request.getIdService(), request.getRateId()));
    }

    @Test
    void actualizarTarifaServicio_ServicioNull() {
        UpdateRateServiceRequest request=new UpdateRateServiceRequest(1,1);

        /*para el mteodo actualizarTarifaServicio*/
        Mockito.when(portOutMock.consultarServicioPorId(request.getIdService())).thenReturn(null);
        Mockito.when(tarifaServiceMock.obtenerTarifaPorId(request.getRateId())).thenReturn(
                new RateType(1,"tarifa normal",0)
        );

        /*para el metodo actualizarServicioHabitacionOcupada*/
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);

        Assertions.assertEquals(null,service.actualizarTarifaServicio(request.getIdService(), request.getRateId()));
    }

    @Test
    void cerrarServicioPorIdServicio() {
        Mockito.when(habitacionServiceMock.getRoomById(responseMockHabOcupada.getIdRoom().getIdRoom())).thenReturn(habOcupadaMock);


        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(portOutMock.consultarServicioPorId(responseMockHabOcupada.getIdService())).thenReturn(responseMockHabOcupada);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);
        Mockito.when(habitacionServiceMock.changeRoomStatus(habOcupadaMock.getRoomNumber(),2)).thenReturn(habOcupadaMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

        Assertions.assertEquals(responseMockHabOcupada,service.cerrarServicioPorIdServicio(responseMockHabOcupada.getIdService()));
        Assertions.assertEquals(
                10106,
                service.cerrarServicioPorIdServicio(responseMockHabOcupada.getIdService()).getPayment()
        );
    }

    @Test
    void ampliarServicio() {
        /*para el metodo actualizarServicioHabitacionOcupada*/
        Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
        Mockito.when(recepcionistaServiceMock.obtenerRecepcionistaPorId(1)).thenReturn(receptionistMock);
        Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
        Mockito.when(habitacionServiceMock.getRoomById(1)).thenReturn(habOcupadaMock);

        Assertions.assertEquals(responseMockHabOcupada,service.ampliarServicio(responseMockHabOcupada,1,0,0));
    }

    @Test
    void configurarDias() {
        LocalDateTime fecha,expected,test;

        fecha=LocalDateTime.now();
        expected=LocalDateTime.of(
                fecha.getYear(),
                fecha.getMonth(),
                fecha.getDayOfMonth(),
                fecha.getHour(),
                fecha.getMinute());
        test=LocalDateTime.of(
                fecha.getYear(),
                fecha.getMonth(),
                fecha.getDayOfMonth(),
                fecha.getHour(),
                fecha.getMinute());

        Assertions.assertEquals(
                expected.plusDays(5).plusMinutes(30)
                ,service.configurarDias(test,5,0,30)
        );
    }

    @Test
    void cobrar() {
        LocalDateTime fecha=LocalDateTime.now();

        Assertions.assertEquals(
                2250,
                service.cobrar(fecha,fecha.plusHours(6),10000,10)
        );
    }
}