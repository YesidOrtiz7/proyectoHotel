package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.adaptador.out.db.ServiceRepository;
import com.hotel.serviciosHotel.aplicacion.puerto.in.*;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ServicePortOut;
import com.hotel.serviciosHotel.dominio.entidades.*;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.GenericException;
//import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
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
    private EstadoHabitacionPortIn estadoHabitacionServiceMock;
    private BusinessConfigurationPortIn configurationPortInMock;
    private RoomHistoryPortIn roomHistoryMock;

    private ServicioService service;
    private Service responseMockHabOcupada =new Service(1,
                new ReceptionistEntity(1,
                "11111",
                "Juana",
                "Ordonez")
        ,new Client(1,
                "12345678",
                "Juan",
                "Carloss",
                "García",
                "Pérez",
                "5551234567")
        ,new Room(102,
                new RoomStatus(2,
                        "ocupada",
                    false,
                    true,
                    false)
                ,new RoomType(
                        1,
                "normal")
                ,
                2000.0,
                1,
                2)
        ,new RateType(1,
                "cliente normal",
                0)
        ,new Municipios(1,"Fusagasuga")
        ,new Municipios(2,"San Bernardo")
        ,new PaymentType(1,"efectivo")
        ,LocalDateTime.now()
        ,LocalDateTime.now().plusDays(1)
        ,true
        ,false
    );
    private Service responseMockServicePaid =new Service(1,
            new ReceptionistEntity(1,
                    "11111",
                    "Juana",
                    "Ordonez")
            ,new Client(1,
            "12345678",
            "Juan",
            "Carloss",
            "García",
            "Pérez",
            "5551234567")
            ,new Room(102,
            new RoomStatus(2,
                    "ocupada",
                    false,
                    true,
                    false)
            ,new RoomType(
            1,
            "normal")
            ,
            2000.0,
            1,
            2)
            ,new RateType(1,
            "cliente normal",
            0)
            ,new Municipios(1,"Fusagasuga")
            ,new Municipios(2,"San Bernardo")
            ,new PaymentType(1,"efectivo")
            ,LocalDateTime.now()
            ,LocalDateTime.now().plusDays(1)
            ,true
            ,true
    );
    private Service responseMockHabLimpia =new Service(1,
            new ReceptionistEntity(1,
                    "11111",
                    "Juana",
                    "Ordonez")
            ,new Client(1,
            "12345678",
            "Juan",
            "Carloss",
            "García",
            "Pérez",
            "5551234567")
            ,new Room(102,
            new RoomStatus(1,
                    "limpia",
                    true,
                    false,
                    false)
            ,new RoomType(
            1,
            "normal"),
            2000.0,
            1,
            2)
            ,new RateType(1,
            "cliente normal",
            0)
            ,new Municipios(1,"Fusagasuga")
            ,new Municipios(2,"San Bernardo")
            ,new PaymentType(1,"efectivo")
            ,LocalDateTime.now()
            ,LocalDateTime.now().plusDays(1)
            ,false
            ,false
    );
    private Room roomClean=new Room(102,
                             new RoomStatus(1,
                    "limpia",
                             true,
                             false,
                             false)
            ,new RoomType(
            1,
                    "normal"),
            2000.0,
                    1,
                    2);
    private Room roomInUse=new Room(102,
            new RoomStatus(2,
                    "ocupada",
                    false,
                    true,
                    false)
            ,new RoomType(
            1,
            "normal"),
            2000.0,
            1,
            2);
    private Room roomDirty =new Room(102,
            new RoomStatus(3,
                    "sucia",
                    true,
                    false,
                    false)
            ,new RoomType(
            1,
            "normal"),
            2000.0,
            1,
            2);
    private BusinessConfiguration configMock=new BusinessConfiguration(1,2,3);
    List<BusinessConfiguration> configurationsListMock=new ArrayList<>();
    private LocalDateTime fechaAhora=LocalDateTime.now();
    private RoomHistory roomHistoryRecord=new RoomHistory(1,responseMockHabOcupada, roomDirty,fechaAhora,fechaAhora.plusDays(1));
    private RoomHistory roomHistoryRecord2=new RoomHistory(2,responseMockHabOcupada, roomDirty,fechaAhora.plusDays(1),fechaAhora.plusDays(2));
    private List<Service> listServiceMock;
    private List<RoomHistory> listRoomHistoryRecords=new ArrayList<>();
    @BeforeEach
    void setUp() {
        portOutMock= Mockito.mock(ServiceRepository.class);
        habitacionServiceMock =Mockito.mock(HabitacionService.class);
        tarifaServiceMock =Mockito.mock(TarifasService.class);
        recepcionistaServiceMock =Mockito.mock(RecepcionistaService.class);
        estadoHabitacionServiceMock=Mockito.mock(EstadoHabitacionService.class);
        configurationPortInMock=Mockito.mock(BusinessConfigurationService.class);
        roomHistoryMock=Mockito.mock(RoomHistoryService.class);

        configurationsListMock.add(configMock);

        listServiceMock=new ArrayList<>();
        listServiceMock.add(responseMockHabOcupada);
        //listServiceMock.add(responseMockHabLimpia);

        service=new ServicioService();
        service.setPortOut(portOutMock);
        service.setHabitacionPortIn(habitacionServiceMock);
        service.setRecepcionistaService(recepcionistaServiceMock);
        service.setTarifaPortIn(tarifaServiceMock);
        service.setEstadoHabitacionPortIn(estadoHabitacionServiceMock);
        service.setConfigurationPortIn(configurationPortInMock);
        service.setRoomHistoryPortIn(roomHistoryMock);
    }

    @Test
    void consultarServicioPorId() {
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabOcupada);
            Assertions.assertEquals(responseMockHabOcupada, service.consultarServicioPorId(1));
        } catch (Exception e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
            //Assertions.assertEquals(SearchItemNotFoundException.class,e.getClass());
        }
    }
    @Test
    void consultarServicioPorId_ServicioNoExiste() {
        try {
            Mockito.when(portOutMock.consultarServicioPorId(0)).thenReturn(responseMockHabOcupada);
            service.consultarServicioPorId(1);
        } catch (Exception e) {
            Assertions.assertEquals(SearchItemNotFoundException.class,e.getClass());
        }
    }
    @Test
    void registrarServicio(){
        try {
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(true);
            Mockito.when(habitacionServiceMock.roomExist(2)).thenReturn(true);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomClean);
            Mockito.when(portOutMock.registrarServicio(responseMockHabLimpia)).thenReturn(responseMockHabOcupada);
            Assertions.assertEquals(responseMockHabOcupada,service.registrarServicio(responseMockHabLimpia));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void registrarServicio_RecepcionistaNoExiste(){
        try {
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(false);
            Mockito.when(habitacionServiceMock.roomExist(2)).thenReturn(true);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomClean);
            Mockito.when(portOutMock.registrarServicio(responseMockHabLimpia)).thenReturn(responseMockHabOcupada);
            //Assertions.assertEquals(responseMockHabOcupada,service.registrarServicio(responseMockHabLimpia));
            Assertions.assertThrows(SearchItemNotFoundException.class, ()-> service.registrarServicio(responseMockHabLimpia));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void registrarServicio_HabitacionNoExiste(){
        try {
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(true);
            Mockito.when(habitacionServiceMock.roomExist(2)).thenReturn(false);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomClean);
            Mockito.when(portOutMock.registrarServicio(responseMockHabLimpia)).thenReturn(responseMockHabOcupada);
            Assertions.assertThrows(SearchItemNotFoundException.class, ()-> service.registrarServicio(responseMockHabLimpia));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void registrarServicio_HabitacionConEstadoInvalido(){
        try {
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(true);
            Mockito.when(habitacionServiceMock.roomExist(2)).thenReturn(true);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            Mockito.when(portOutMock.registrarServicio(responseMockHabLimpia)).thenReturn(responseMockHabOcupada);
            Assertions.assertThrows(GenericException.class, ()-> service.registrarServicio(responseMockHabLimpia));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void actualizarServicioHabitacionOcupada(){//<-
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabOcupada);
            Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(true);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            listRoomHistoryRecords.add(roomHistoryRecord);
            Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);
            Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

            Assertions.assertEquals(responseMockHabOcupada,service.actualizarServicioHabitacionOcupada(responseMockHabOcupada));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void actualizarServicioHabitacionOcupada_PeticionNoLegal(){
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockServicePaid);
            Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(true);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            listRoomHistoryRecords.add(roomHistoryRecord);
            Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);
            Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

            Assertions.assertThrows(GenericException.class,()->service.actualizarServicioHabitacionOcupada(responseMockHabOcupada));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void actualizarServicioHabitacionOcupada_ServicioNoExiste(){
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabOcupada);
            Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(false);
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(true);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            listRoomHistoryRecords.add(roomHistoryRecord);
            Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);
            Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.actualizarServicioHabitacionOcupada(responseMockHabOcupada));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void actualizarServicioHabitacionOcupada_RecepcionistaNoExiste(){
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabOcupada);
            Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(false);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            listRoomHistoryRecords.add(roomHistoryRecord);
            Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);
            Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.actualizarServicioHabitacionOcupada(responseMockHabOcupada));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void actualizarServicioHabitacionOcupada_HabitacionConEstadoInvalido(){
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabOcupada);
            Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(true);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomDirty);
            listRoomHistoryRecords.add(roomHistoryRecord);
            Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);
            Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

            Assertions.assertThrows(GenericException.class,()->service.actualizarServicioHabitacionOcupada(responseMockHabOcupada));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void actualizarServicioParaCerrarServicio(){
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabOcupada);
            Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(true);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            listRoomHistoryRecords.add(roomHistoryRecord);
            Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);
            Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

            responseMockHabOcupada.setItsPaid(true);

            Assertions.assertEquals(responseMockHabOcupada,service.actualizarServicioParaCerrarServicio(responseMockHabOcupada));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void actualizarServicioParaCerrarServicio_PeticionNoLegal(){
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabLimpia);
            Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(true);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            listRoomHistoryRecords.add(roomHistoryRecord);
            Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);
            Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

            responseMockHabOcupada.setItsPaid(true);

            Assertions.assertThrows(GenericException.class,()->service.actualizarServicioParaCerrarServicio(responseMockHabOcupada));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void actualizarServicioParaCerrarServicio_ServicioNoExiste(){
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabOcupada);
            Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(false);
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(true);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            listRoomHistoryRecords.add(roomHistoryRecord);
            Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);
            Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

            responseMockHabOcupada.setItsPaid(true);

            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.actualizarServicioParaCerrarServicio(responseMockHabOcupada));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void actualizarServicioParaCerrarServicio_ServicioNoPagado(){
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabOcupada);
            Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(true);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            listRoomHistoryRecords.add(roomHistoryRecord);
            Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);
            Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

            responseMockHabOcupada.setItsPaid(false);

            Assertions.assertThrows(GenericException.class,()->service.actualizarServicioParaCerrarServicio(responseMockHabOcupada));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void actualizarServicioParaCerrarServicio_RecepcionistaNoExiste(){
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabOcupada);
            Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(false);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            listRoomHistoryRecords.add(roomHistoryRecord);
            Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);
            Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

            responseMockHabOcupada.setItsPaid(true);

            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.actualizarServicioParaCerrarServicio(responseMockHabOcupada));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void actualizarServicioParaCerrarServicio_HabitacionConEstadoInvalido(){
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabOcupada);
            Mockito.when(portOutMock.servicioExiste(responseMockHabOcupada)).thenReturn(true);
            Mockito.when(recepcionistaServiceMock.existenciaRecepcionista(1)).thenReturn(true);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomClean);
            listRoomHistoryRecords.add(roomHistoryRecord);
            Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);
            Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);

            responseMockHabOcupada.setItsPaid(true);

            Assertions.assertThrows(GenericException.class,()->service.actualizarServicioParaCerrarServicio(responseMockHabOcupada));
        }catch (Exception e){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    /*-------------------------------------------------------------------------*/
    @Test
    void actualizarHabitacionServicio(){
        try {
            Mockito.when(portOutMock.consultarServicioPorId(1)).thenReturn(responseMockHabOcupada);
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);
            Mockito.when(portOutMock.actualizarServicio(responseMockHabOcupada)).thenReturn(responseMockHabOcupada);
            listRoomHistoryRecords.add(roomHistoryRecord);
            Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);

            Assertions.assertEquals(responseMockHabOcupada,service.actualizarHabitacionServicio(1,2));
        }catch (Exception e){
            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    /*-------------------------------------------------------------------------*/
    @Test
    void actualizarTarifaServicio(){}
    //cerrarServicioPorIdServicio
    //ampliarServicio
    //pagarServicio
    @Test
    void determinarMinutosEstadia(){
        int minutos=service.determinarMinutosEstadia(LocalDateTime.now(),LocalDateTime.now().plusDays(1).plusHours(1));
        Assertions.assertEquals(1500,minutos);
    }
    @Test
    void cobrar(){
        double valorACobrar=service.cobrar(LocalDateTime.now(),LocalDateTime.now().plusDays(1).plusHours(1),2000,0);
        Assertions.assertEquals(2083,valorACobrar);
        valorACobrar=service.cobrar(LocalDateTime.now(),LocalDateTime.now().plusDays(1).plusHours(4),2000,0);
        Assertions.assertEquals(2333,valorACobrar);
        valorACobrar=service.cobrar(LocalDateTime.now(),LocalDateTime.now().plusDays(1).plusHours(4),2000,10);
        Assertions.assertEquals(2100,valorACobrar);
    }
    @Test
    void configurarDias(){
        LocalDateTime ahora=LocalDateTime.now();
        LocalDateTime resultado=service.configurarDias(ahora,1,1,1);
        Assertions.assertEquals(ahora.plusDays(1).plusHours(1).plusMinutes(1),resultado);
    }
    //determinarOcupacionHabitacion //<-hacer que arroje un error personalizado en caso de que la lista de configuraciones este vacia
    @Test
    void determinarOcupacionHabitacion(){
        try {
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(configurationsListMock);

            Assertions.assertTrue(service.determinarOcupacionHabitacion(2));
        }catch (Exception e){
            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e.getMessage()+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    @Test
    void determinarOcupacionHabitacion_SinConfiguracionesEnLaBaseDeDatos(){
        try {
            Mockito.when(habitacionServiceMock.getRoomById(2)).thenReturn(roomInUse);
            Mockito.when(configurationPortInMock.getConfigurations()).thenReturn(new ArrayList<>());

            Assertions.assertThrows(GenericException.class,()->service.determinarOcupacionHabitacion(2));
        }catch (Exception e){
            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!> "+e+" <!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
    //servicioPagado
    //peticionLegal
    @Test
    void generarCobroConBaseEnHistorial(){
        listRoomHistoryRecords.add(roomHistoryRecord);
        listRoomHistoryRecords.add(roomHistoryRecord2);
        Mockito.when(roomHistoryMock.getHistoryByIdService(1)).thenReturn(listRoomHistoryRecords);
        Assertions.assertEquals(4000,service.generarCobroConBaseEnHistorial(responseMockHabOcupada));
    }
    /*{
        "idService": 1,
        "idRecep": {
            "idRecep": 1,
            "docRecep": "11111",
            "receptionistNames": "Juana",
            "receptionistLastNames": "Ordonez"
        },
        "idClient": {
            "idCliente": 1,
            "documentoCliente": "12345678",
            "primerNombreCliente": "Juan",
            "segundoNombreCliente": "Carloss",
            "primerApellidoCliente": "García",
            "segundoApellidoCliente": "Pérez",
            "celularCliente": "5551234567"
        },
        "idRoom": {
            "roomNumber": 102,
            "idRoomStatus": {
                "idStatus": 3,
                "statusName": "sucia",
                "visibleOnSelection": false,
                "defaultForServiceStart": false,
                "defaultForServiceShutdown": true
            },
            "roomType": {
                "idRoomType": 1,
                "roomTypeDescription": "normal"
            },
            "roomPrice24Hours": 2000.0,
            "bedsNumber": 1,
            "idRoom": 2
        },
        "idRateType": {
            "idTipoTarifa": 1,
            "descripcionTarifa": "cliente normal",
            "porcentajeTarifa": 0
        },
        "cliProcedencia": {
            "idMunicipios": 1,
            "nombreMun": "Fusagasuga"
        },
        "cliDestino": {
            "idMunicipios": 2,
            "nombreMun": "San Bernardo"
        },
        "idTipoPago": {
            "idPago": 1,
            "descripcionPago": "efectivo"
        },
        "payment": 1980.0,
        "fechaEntrada": "2024-07-19T17:36:00",
        "fechaSalida": "2024-07-20T17:36:00",
        "itsPaid": true,
        "state": false
    }*/
}