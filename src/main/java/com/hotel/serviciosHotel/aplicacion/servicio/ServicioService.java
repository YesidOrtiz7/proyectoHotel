package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.HabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.ServicioPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.TarifaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ServicePortOut;
import com.hotel.serviciosHotel.dominio.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
public class ServicioService implements ServicioPortIn {

    private ServicePortOut portOut;

    private HabitacionPortIn habitacionService;

    private TarifaPortIn tarifaService;

    private RecepcionistaPortIn recepcionistaService;

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
        if (recepcionista!=null && !this.determinarOcupacionHabitacion(service)){
            service.setIdRecep(recepcionista);
            habitacionService.changeRoomStatus(
                    service.getIdRoom().getRoomNumber(),3
            );
            //return portOut.registrarServicio(service);
            return portOut.registrarServicio(
                    this.establecerEstadia(service)
            );
        }else {
            return null;
        }

    }

    @Override
    public Service actualizarServicio(Service service) {
        ReceptionistEntity recepcionista= this.obtenerRecepcionista(service);
        if (recepcionista!=null && this.determinarOcupacionHabitacion(service)){
            service.setIdRecep(recepcionista);
            return portOut.actualizarServicio(service);
        }else {
            return null;
        }
    }

    @Override
    public Service actualizarHabitacionServicio(Service service,int numeroHabitacion) {
        Room hab=habitacionService.getRoomByNumber(numeroHabitacion);
        if (hab==null||(hab.getIdRoom()==0)){
            return null;
        }else {
            service.setIdRoom(hab);
            return this.actualizarServicio(service);
        }
    }

    @Override
    public Service actualizarTarifaServicio(Service service,int idTarifa) {
        RateType tarifa=tarifaService.obtenerTarifaPorId(idTarifa);
        if (tarifa==null||(tarifa.getIdTipoTarifa()==0)){
            return null;
        }else {
            service.setIdTipoTarifa(tarifa);
            return this.actualizarServicio(service);
        }
    }

    @Override
    public Service cerrarServicio(Service service) {
        service.setState(1);
        return this.actualizarServicio(service);
    }

    @Override
    public Service ampliarServicio(Service service) {
        return null;
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
}
