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
        if (recepcionista!=null && !this.determinarOcupacionHabitacion(service) && room.getIdRoomStatus().getIdStatus() !=3){
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
    public Service actualizarServicioHabitacionDesocupada(Service service) {
        if (portOut.servicioExiste(service)){
            ReceptionistEntity recepcionista= this.obtenerRecepcionista(service);
            if (recepcionista!=null && !this.determinarOcupacionHabitacion(service)){
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
    public Service actualizarHabitacionServicio(int service,int numeroHabitacion) {
        Service serv=portOut.consultarServicioPorId(service);

        Room hab=habitacionService.getRoomByNumber(numeroHabitacion);
        if (hab==null||(hab.getIdRoom()==0)){
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

        if (tarifa==null||(tarifa.getIdTipoTarifa()==0)){
            return null;
        }else {
            serv.setIdRateType(tarifa);
            return this.actualizarServicioHabitacionOcupada(serv);
        }
    }

    @Override
    public Service cerrarServicio(Service service) {
        service.setState(0);
        Room room=habitacionService.getRoomById(service.getIdRoom().getIdRoom());
        int descuento=service.getIdRateType().getPorcentajeTarifa()/100;
        descuento=room.getRoomPriceNight()*descuento;

        int precioSinDescuento=room.getRoomPriceNight()-descuento;

        precioSinDescuento*=this.determinarDiasEstadia(
                service.getFechaEntrada(),
                service.getFechaSalida()
        );

        service.setPayment(precioSinDescuento);
        return this.actualizarServicioHabitacionDesocupada(service);
    }

    @Override
    public Service cerrarServicioPorIdServicio(int idService) {
        Service service =this.consultarServicioPorId(idService);
        return this.cerrarServicio(service);
    }

    @Override
    public Service ampliarServicio(Service service, int dia,int hora,int minuto) {
        LocalDateTime entrada=service.getFechaEntrada();
        LocalDateTime salida=service.getFechaSalida();
        if (entrada!=null&&salida!=null&& salida.isAfter(entrada)){
            service.setFechaSalida(
                    LocalDateTime.of(LocalDateTime.now().getYear(),
                            LocalDateTime.now().getMonth(),
                            LocalDateTime.now().getDayOfMonth()+dia,
                            LocalDateTime.now().getHour()+hora,
                            LocalDateTime.now().getMinute()+minuto,
                            LocalDateTime.now().getSecond())
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
        int estado=service.getIdRoom().getIdRoomStatus().getIdStatus();
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
        }else {
            service.setFechaEntrada(LocalDateTime.now());
            service.setFechaSalida(
                    LocalDateTime.of(entrada.getYear(),
                            entrada.getMonth(),
                            entrada.getDayOfMonth()+1,
                            entrada.getHour(),
                            entrada.getMinute(),
                            entrada.getSecond())
            );
            return service;
        }
    }

    public int determinarDiasEstadia(LocalDateTime entrada,LocalDateTime salida){
        return Period.between(entrada.toLocalDate(),salida.toLocalDate()).getDays();
    }
}
