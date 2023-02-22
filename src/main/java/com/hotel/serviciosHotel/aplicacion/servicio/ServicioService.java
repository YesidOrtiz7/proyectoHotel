package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.HabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.ServicioPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.TarifaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ServicePortOut;
import com.hotel.serviciosHotel.dominio.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@org.springframework.stereotype.Service
public class ServicioService implements ServicioPortIn {

    private ServicePortOut portOut;

    private HabitacionPortIn habitacionService;

    private TarifaPortIn tarifaService;

    private RecepcionistaPortIn recepcionistaService;

    /*------------------------------inyeccion de dependencias------------------------------*/
    @Autowired
    public void setPortOut(ServicePortOut portOut) {
        this.portOut = portOut;
    }

    @Autowired
    public void setHabitacionService(HabitacionPortIn habitacionService) {
        this.habitacionService = habitacionService;
    }

    @Autowired
    public void setTarifaService(TarifaPortIn tarifaService) {
        this.tarifaService = tarifaService;
    }

    @Autowired
    public void setRecepcionistaService(RecepcionistaPortIn recepcionistaService) {
        this.recepcionistaService = recepcionistaService;
    }
    /*-------------------------------------------------------------------------------*/

    @Override
    public Service consultarServicioPorId(int id) {
        return portOut.consultarServicioPorId(id);
    }

    @Override
    public List<Service> consultarServicios() {
        return portOut.consultarServicios();
    }

    @Override
    public Service registrarServicio(Service service) {
        ReceptionistEntity recepcionista=this.obtenerRecepcionista(service);
        Room room= habitacionService.getRoomById(
                service.getIdRoom().getIdRoom()
        );
        if (recepcionista!=null && !this.determinarOcupacionHabitacion(service)){
            service.setIdRecep(recepcionista);

            /*cambiar el estado a la habitacion*/
            service.setIdRoom(
                habitacionService.changeRoomStatus(
                    service.getIdRoom().getRoomNumber(),3
                )
            );

            return portOut.registrarServicio(
                    this.establecerEstadia(service)
            );
        }else {
            return null;
        }

    }

    @Override
    public Service actualizarServicioHabitacionOcupada(Service service) {
        if (portOut.servicioExiste(service)){
            ReceptionistEntity recepcionista= this.obtenerRecepcionista(service);
            if (recepcionista!=null && this.determinarOcupacionHabitacion(service)){
                service.setIdRecep(recepcionista);
                return portOut.actualizarServicio(service);
            }else {
                return null;
            }
        }else {
            return null;
        }

    }
    @Override
    public Service actualizarServicioParaCerrarServicio(Service service) {
        if (portOut.servicioExiste(service)){
            ReceptionistEntity recepcionista= this.obtenerRecepcionista(service);
            if (recepcionista!=null && this.determinarOcupacionHabitacion(service)){
                service.setIdRecep(recepcionista);

                /*cambiar habitacion a sucia*/
                service.setIdRoom(
                    habitacionService.changeRoomStatus(service.getIdRoom().getRoomNumber(),2)
                );

                return portOut.actualizarServicio(service);
            }else {
                return null;
            }
        }else {
            return null;
        }

    }

    @Override
    public Service actualizarHabitacionServicio(int service,int numeroHabitacion) {
        Service serv=portOut.consultarServicioPorId(service);

        Room hab=habitacionService.getRoomByNumber(numeroHabitacion);
        if ((hab==null || hab.getIdRoom()==0) || serv==null){
            return null;
        }else {
            //cambiar la habitacion actual a sucia
            habitacionService.changeRoomStatus(serv.getIdRoom().getIdRoom(),2);

            serv.setIdRoom(hab);

            //cambiar la otra habitacion a ocupada
            habitacionService.changeRoomStatus(numeroHabitacion,3);
            return this.actualizarServicioHabitacionOcupada(serv);
        }
    }

    @Override
    public Service actualizarTarifaServicio(int service,int idTarifa) {
        Service serv=portOut.consultarServicioPorId(service);
        RateType tarifa=tarifaService.obtenerTarifaPorId(idTarifa);

        if ((tarifa==null || tarifa.getIdTipoTarifa()==0) || serv==null){
            return null;
        }else {
            serv.setIdRateType(tarifa);
            return this.actualizarServicioHabitacionOcupada(serv);
        }
    }

    @Override
    public Service cerrarServicioPorIdServicio(int idService) {
        Service service =this.consultarServicioPorId(idService);
        service.setState(0);
        Room room=habitacionService.getRoomById(service.getIdRoom().getIdRoom());
        double valor=0;

        valor=this.cobrar(service.getFechaEntrada()
                ,service.getFechaSalida(),
                room.getRoomPrice24Hours(),
                service.getIdRateType().getIdTipoTarifa()
        );

        service.setPayment(valor);
        return this.actualizarServicioParaCerrarServicio(service);
    }

    @Override
    public Service ampliarServicio(Service service, int dia,int hora,int minuto) {
        LocalDateTime entrada=service.getFechaEntrada();
        LocalDateTime salida=service.getFechaSalida();
        if (entrada!=null&&salida!=null&& salida.isAfter(entrada)){
            service.setFechaSalida(
                    this.configurarDias(service.getFechaSalida(),dia,hora,minuto)
            );
            return this.actualizarServicioHabitacionOcupada(service);
        }else {
            return null;
        }
    }

    public ReceptionistEntity obtenerRecepcionista(Service service){
        return recepcionistaService.obtenerRecepcionistaPorId(
                service.getIdRecep().getIdRecep()
        );
    }

    public boolean determinarOcupacionHabitacion(Service service){
        Room room= habitacionService.getRoomById(
                service.getIdRoom().getIdRoom()
        );
        int estado=room.getIdRoomStatus().getIdStatus();
        if (estado==3){
            return true;
        }else {
            return false;
        }
    }

    /** if the date of checkout of the service is null, it set one day later
     * than date of checkin*/
    public Service establecerEstadia(Service service){
        LocalDateTime entrada=service.getFechaEntrada();
        LocalDateTime salida=service.getFechaSalida();

        service.setState(1);

        if (entrada!=null&&salida!=null&& salida.isAfter(entrada)){
            return service;
        }else if (entrada!=null && salida==null){
            service.setFechaEntrada(entrada);
            service.setFechaSalida(
                    LocalDateTime.now().plusDays(1)
            );
            return service;
        }else {
            service.setFechaEntrada(LocalDateTime.now());
            service.setFechaSalida(
                    LocalDateTime.now().plusDays(1)
            );
            return service;
        }
    }

    public int determinarMinutosEstadia(LocalDateTime entrada, LocalDateTime salida){
        int horasEntrada,horasSalida;
        int minutosEntrada,minutosSalida;
        int minutos=Period.between(entrada.toLocalDate(),salida.toLocalDate()).getDays()*1440;
        //return Period.between(entrada.toLocalDate(),salida.toLocalDate()).getDays();

        horasEntrada=entrada.getHour();
        horasSalida=salida.getHour();

        minutosEntrada=entrada.getMinute();
        minutosSalida=salida.getMinute();

        minutos+=(horasSalida-horasEntrada)*60;
        minutos+=minutosSalida-minutosEntrada;


        return minutos;
    }

    public LocalDateTime configurarDias(LocalDateTime fecha, int dia,int hora,int minuto){
        LocalDateTime salida=fecha
                .plusDays(dia)
                .plusHours(hora)
                .plusMinutes(minuto);

        return salida;
    }

    public double cobrar(LocalDateTime entrada, LocalDateTime salida, double tarifa, double descuento){
        double valor=0;
        double desc=0;

        int minutosEstadia=this.determinarMinutosEstadia(entrada,salida);

        valor=(tarifa/1440)*minutosEstadia;

        desc=(valor*descuento)/100;

        valor-=desc;

        return Math.round(valor);
    }
}
