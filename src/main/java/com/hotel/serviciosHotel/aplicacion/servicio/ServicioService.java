package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.*;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ServicePortOut;
import com.hotel.serviciosHotel.dominio.entidades.*;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.GenericException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@org.springframework.stereotype.Service
public class ServicioService implements ServicioPortIn {
    /*--------------------------Campos para inyeccion de dependencias-----------------------*/
    private ServicePortOut portOut;
    private HabitacionPortIn habitacionPortIn;
    private EstadoHabitacionPortIn estadoHabitacionPortIn;
    private BusinessConfigurationPortIn configurationPortIn;
    private TarifaPortIn tarifaPortIn;
    private RecepcionistaPortIn recepcionistaService;
    private RoomHistoryPortIn roomHistoryPortIn;
    /*--------------------------Inyeccion de dependencias-----------------------------------*/
    @Autowired
    public void setEstadoHabitacionPortIn(EstadoHabitacionPortIn estadoHabitacionPortIn) {
        this.estadoHabitacionPortIn = estadoHabitacionPortIn;
    }
    @Autowired
    public void setConfigurationPortIn(BusinessConfigurationPortIn configurationPortIn) {
        this.configurationPortIn = configurationPortIn;
    }
    @Autowired
    public void setPortOut(ServicePortOut portOut) {
        this.portOut = portOut;
    }
    @Autowired
    public void setHabitacionPortIn(HabitacionPortIn habitacionPortIn) {
        this.habitacionPortIn = habitacionPortIn;
    }
    @Autowired
    public void setTarifaPortIn(TarifaPortIn tarifaPortIn) {
        this.tarifaPortIn = tarifaPortIn;
    }
    @Autowired
    public void setRecepcionistaService(RecepcionistaPortIn recepcionistaService) {
        this.recepcionistaService = recepcionistaService;
    }
    @Autowired
    public void setRoomHistoryPortIn(RoomHistoryPortIn roomHistoryPortIn) {
        this.roomHistoryPortIn = roomHistoryPortIn;
    }

    /*---------------------------metodos de la clase----------------------------------------*/
    @Override
    public Service consultarServicioPorId(int id) throws SearchItemNotFoundException {
        Service response=portOut.consultarServicioPorId(id);
        if (response!=null){
            return response;
        }else{
            throw new SearchItemNotFoundException("el servicio con el id "+id+"no existe");
        }
    }

    @Override
    public List<Service> consultarServicios() {
        return portOut.consultarServicios();
    }

    @Override
    public Service registrarServicio(Service service) throws SearchItemNotFoundException, ItemAlreadyExistException, GenericException {
        if (!recepcionistaService.existenciaRecepcionista(service.getIdRecep().getIdRecep())){
            throw new SearchItemNotFoundException("la recepcionista que se esta tratando"+
                    " de asociar a este servicio no existe");
        }
        if (!habitacionPortIn.roomExist(service.getIdRoom().getIdRoom())){
            throw new SearchItemNotFoundException("la habitacion que se esta tratando"+
                    " de asociar a este servicio no existe");
        }
        BusinessConfiguration config=configurationPortIn.getConfigurations().get(0);
        Room habitacion=habitacionPortIn.getRoomById(service.getIdRoom().getIdRoom());
        RoomHistory roomHistory=new RoomHistory(
                habitacion,
                service.getFechaEntrada(),
                service.getFechaSalida());
        if (this.determinarOcupacionHabitacion(habitacion.getIdRoom())){
            throw new GenericException("la habitacion que esta tratando de registrar en este servicio tiene un estado"+
                    "que le impide ser asignada");
        }

        service.setPayment(
                this.cobrar(
                        service.getFechaEntrada(),
                        service.getFechaSalida(),
                        service.getIdRoom().getRoomPrice24Hours(),
                        service.getIdRateType().getPorcentajeTarifa()
                )
        );

        habitacion.setIdRoomStatus(
                estadoHabitacionPortIn.obtenerEstadoHabitacionPorId(
                        config.getIdStateDefaultToStartService()
                )
        );
        habitacionPortIn.updateRoom(habitacion);

        service.setState(true);
        Service response=portOut.registrarServicio(service);
        roomHistory.setIdService(response);
        roomHistoryPortIn.saveHistory(roomHistory);

        return response;
    }
    /**este metodo es usado cuando se requiere actualizar la duracion del servicio,
     * este metodo realiza verificaciones al servicio que se va a registrar y actualiza
     * el historial de habitaciones del servicio*/
    @Override
    public Service actualizarServicioHabitacionOcupada(Service service) throws SearchItemNotFoundException, GenericException {
        if (!this.peticionLegal(service)){
            throw new GenericException("Peticion invalida: los datos del pago registrados en la base de datos"+
                    "no concuerdan");
        }
        if (!portOut.servicioExiste(service)){
            throw new SearchItemNotFoundException(
                    "Peticion invalida: el servicio no existe");
        }
        if (!recepcionistaService.existenciaRecepcionista(service.getIdRecep().getIdRecep())){
            throw new SearchItemNotFoundException(
                    "Peticion invalida: la recepcionista que se esta tratando de asociar a este servicio no existe");
        }
        if (!this.determinarOcupacionHabitacion(service.getIdRoom().getIdRoom())){
            throw new GenericException("Habitacion con un estado no valido");
        }
        this.actualizarHistorial(service,service.getFechaSalida());
        double valorACobrar=this.generarCobroConBaseEnHistorial(service);
        service.setPayment(valorACobrar);
        return portOut.actualizarServicio(service);
    }

    @Override
    public Service actualizarServicioParaCerrarServicio(Service service) throws SearchItemNotFoundException, GenericException {
        if (!this.peticionLegal(service)){
            throw new GenericException("Peticion invalida: los datos del pago registrados en la base de datos"+
                    "no concuerdan");
        }
        if (!portOut.servicioExiste(service)){
            throw new SearchItemNotFoundException(
                    "Peticion invalida: el servicio no existe");
        }
        if (!this.servicioPagado(service.getIdService())){
            throw new GenericException("el servicio que se esta tratando de cancelar no esta pagado");
        }
        if (!recepcionistaService.existenciaRecepcionista(service.getIdRecep().getIdRecep())){
            throw new SearchItemNotFoundException(
                    "Peticion invalida: la recepcionista que se esta tratando de asociar a este servicio no existe");
        }
        if (!this.determinarOcupacionHabitacion(service.getIdRoom().getIdRoom())){
            throw new GenericException("Habitacion con un estado no valido");
        }
        service.setState(false);

        /*cambiar el estado a la habitacion*/

        BusinessConfiguration configuration=configurationPortIn.getConfigurations().get(0);
        service.setIdRoom(
                habitacionPortIn.changeRoomStatus(
                        service.getIdRoom().getIdRoom(),
                        configuration.getIdStateDefaultToCloseService()
                )
        );
        return portOut.actualizarServicio(service);
    }

    @Override
    public Service actualizarHabitacionServicio(int service, int idRoom)
            throws SearchItemNotFoundException, ItemAlreadyExistException, GenericException {
        Service servicio=portOut.consultarServicioPorId(service);
        Room habitacionActual=servicio.getIdRoom();
        Room habitacionTransferir=habitacionPortIn.getRoomById(idRoom);

        BusinessConfiguration config=configurationPortIn.getConfigurations().get(0);
        if (habitacionTransferir.getIdRoomStatus().getIdStatus()!=config.getIdStateDefaultToStartService() &&
        habitacionTransferir.getIdRoomStatus().getIdStatus()!=config.getIdStateDefaultToCloseService()){
            //asignar a la habitacino actual el estado predeterminado para el cierre del servicio
            habitacionActual.setIdRoomStatus(
                    estadoHabitacionPortIn.obtenerEstadoHabitacionPorId(
                            config.getIdStateDefaultToCloseService()
                    )
            );
            habitacionPortIn.updateRoom(habitacionActual);

            //asignar a la habitacion a transferir el estado predeterminado para el inicio del servicio
            habitacionTransferir.setIdRoomStatus(
                    estadoHabitacionPortIn.obtenerEstadoHabitacionPorId(
                            config.getIdStateDefaultToStartService()
                    )
            );
            habitacionPortIn.updateRoom(habitacionTransferir);
            this.actualizarHistorialNuevaHabitacion(servicio,habitacionTransferir,servicio.getFechaSalida());

            double cobro=this.generarCobroConBaseEnHistorial(servicio);

            servicio.setPayment(cobro);
            servicio.setIdRoom(habitacionTransferir);
            return portOut.actualizarServicio(servicio);
        }else {
            throw new GenericException("La habitacion a transferir no se encuentra lista para el servicio");
        }
    }

    @Override
    public Service actualizarTarifaServicio(int service, int idTarifa) throws SearchItemNotFoundException {
        Service servicio=portOut.consultarServicioPorId(service);
        RateType tarifaNueva=tarifaPortIn.obtenerTarifaPorId(idTarifa);

        servicio.setIdRateType(tarifaNueva);
        servicio.setPayment(
                this.generarCobroConBaseEnHistorial(servicio)
        );
        return portOut.actualizarServicio(servicio);
    }

    @Override
    public Service cerrarServicioPorIdServicio(int idService) throws SearchItemNotFoundException, GenericException {
        Service service=this.consultarServicioPorId(idService);
        return this.actualizarServicioParaCerrarServicio(service);
    }

    @Override
    public Service ampliarServicio(Service service, int dia, int hora, int minutos) throws SearchItemNotFoundException, GenericException {
        LocalDateTime entrada=service.getFechaEntrada();
        LocalDateTime salida=service.getFechaSalida();
        if (entrada!=null&&salida!=null&& salida.isAfter(entrada)){
            service.setFechaSalida(
                    this.configurarDias(service.getFechaSalida(),dia,hora,minutos)
            );
            return this.actualizarServicioHabitacionOcupada(service);
        }else {
            return null;
        }
    }

    @Override
    public Service pagarServicio(int idService) throws SearchItemNotFoundException {
        Service service = portOut.consultarServicioPorId(idService);
        service.setItsPaid(true);

        return portOut.actualizarServicio(service);
    }

    public int determinarMinutosEstadia(LocalDateTime entrada, LocalDateTime salida){
        int horasEntrada,horasSalida;
        int minutosEntrada,minutosSalida;
        int minutos= Period.between(entrada.toLocalDate(),salida.toLocalDate()).getDays()*1440;
        //return Period.between(entrada.toLocalDate(),salida.toLocalDate()).getDays();

        horasEntrada=entrada.getHour();
        horasSalida=salida.getHour();

        minutosEntrada=entrada.getMinute();
        minutosSalida=salida.getMinute();

        minutos+=(horasSalida-horasEntrada)*60;
        minutos+=minutosSalida-minutosEntrada;


        return minutos;
    }
    public double cobrar(LocalDateTime entrada, LocalDateTime salida, double valorHabitacion, double tarifaDescuento){
        double valor=0;
        double desc=0;

        int minutosEstadia=this.determinarMinutosEstadia(entrada,salida);

        valor=(valorHabitacion/1440)*minutosEstadia;

        desc=(valor*tarifaDescuento)/100;

        valor-=desc;

        return Math.round(valor);
    }
    /**toma la fecha pasada como parametro y le suma los dias, horas o minutos que
     * se le especifiquen*/
    public LocalDateTime configurarDias(LocalDateTime fecha, int dia,int hora,int minuto){
        LocalDateTime salida=fecha
                .plusDays(dia)
                .plusHours(hora)
                .plusMinutes(minuto);

        return salida;
    }
    public boolean determinarOcupacionHabitacion(int idRoom) throws SearchItemNotFoundException, GenericException {
        Room room= habitacionPortIn.getRoomById(
                idRoom
        );
        BusinessConfiguration config;
        try {
            config=configurationPortIn.getConfigurations().get(0);
        }catch (IndexOutOfBoundsException e){
            throw new GenericException("No es posible completar la accion, no hay configuraciones en la base de datos");
        }
        int estado=room.getIdRoomStatus().getIdStatus();
        return estado == config.getIdStateDefaultToStartService();
    }
    public boolean servicioPagado(int idService) throws SearchItemNotFoundException{
        Service servicio=portOut.consultarServicioPorId(idService);
        return servicio.isItsPaid();
    }
    /**este metodo verifica los valores de la peticion realizada y los compara con
     * los datos del servicio registrado en la base de datos, si los datos coinciden
     * retornara true, en caso contrario false*/
    public boolean peticionLegal(Service service) throws SearchItemNotFoundException{
        Service srvc=portOut.consultarServicioPorId(service.getIdService());
        return srvc.isItsPaid()==service.isItsPaid();
    }
    public double generarCobroConBaseEnHistorial(Service service){
        List<RoomHistory> roomsInThisService=roomHistoryPortIn.getHistoryByIdService(service.getIdService());
        double valorACobrar=0;
        for (RoomHistory record: roomsInThisService) {
            valorACobrar+=this.cobrar(
                    record.getSinceDate(),
                    record.getTillDate(),
                    record.getIdRoom().getRoomPrice24Hours(),
                    service.getIdRateType().getPorcentajeTarifa()
            );
        }
        return valorACobrar;
    }
    public void actualizarHistorialNuevaHabitacion(Service idService,
                                                   Room habitacionFutura,
                                                   LocalDateTime fechaSalida)
            throws SearchItemNotFoundException, ItemAlreadyExistException{

        /*seleccionamos el ultimo registro del historial, que seria el de la habitacion actual
        * despues le asignamos como fecha de salida la fecha actual y al mismo tiempo creamos un
        * nuevo registro con la nueva habitacion y asignamos como fecha de entrada la fecha actual
        * y como fecha de salida le asignamos la fecha del parametro*/
        LocalDateTime currentDate=LocalDateTime.now();

        //actualizamos el registro de la habitacion actual
        this.actualizarHistorial(idService,currentDate);


        RoomHistory newRecord=new RoomHistory(habitacionFutura,currentDate,fechaSalida);
        newRecord.setIdService(idService);
        //guardamos el nuevo registro
        roomHistoryPortIn.saveHistory(newRecord);
    }
    /**esta funcion consulta todos los registros del historial de habitaciones
     * y toma el ultimo (el registro actual del servicio) para actualizarlo
     * actualizando la fecha de salida con la fecha pasada por parametro*/
    public void actualizarHistorial(Service idService,
                                    LocalDateTime fechaSalida)
            throws SearchItemNotFoundException{
        List<RoomHistory> roomsInThisService= roomHistoryPortIn.getHistoryByIdService(idService.getIdService());
        RoomHistory currentRecord=roomsInThisService.get(roomsInThisService.size()-1);
        currentRecord.setTillDate(fechaSalida);
        roomHistoryPortIn.updateHistory(currentRecord);
    }
}
