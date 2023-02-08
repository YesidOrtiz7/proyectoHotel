package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.HabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.ServicioPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.TarifaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ServicePortOut;
import com.hotel.serviciosHotel.dominio.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServicioService implements ServicioPortIn {
    @Autowired
    private ServicePortOut portOut;
    @Autowired
    private HabitacionPortIn habitacionService;
    @Autowired
    private TarifaPortIn tarifaService;
    @Autowired
    private RecepcionistaPortIn recepcionistaService;
    @Override
    public Service consultarServicioPorId(int id) {
        Service s=portOut.consultarServicioPorId(id);
        return this.restringirRecepcionista(s);
    }

    @Override
    public List<Service> consultarServicios() {
        List<Service> s=portOut.consultarServicios();
        List<Service> response=new ArrayList<>();
        for (Service service:s){
            response.add(
                    this.restringirRecepcionista(service)
            );
        }
        return response;
    }

    @Override
    public Service registrarServicio(Service service) {
        Receptionist recepcionista=this.obtenerRecepcionista(service);
        if (recepcionista!=null && !this.determinarOcupacionHabitacion(service)){
            service.setIdRecep(recepcionista);
            habitacionService.changeRoomStatus(
                    service.getIdRoom().getRoomNumber(),3
            );
            return portOut.registrarServicio(service);
        }else {
            return null;
        }

    }

    @Override
    public Service actualizarServicio(Service service) {
        Receptionist recepcionista= this.obtenerRecepcionista(service);
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
        if (hab!=null||(hab.getIdRoom()!=0)){
            service.setIdRoom(hab);
            return this.actualizarServicio(service);
        }else {
            return null;
        }
    }

    @Override
    public Service actualizarTarifaServicio(Service service,int idTarifa) {
        RateType tarifa=tarifaService.obtenerTarifaPorId(idTarifa);
        if (tarifa!=null||(tarifa.getIdTipoTarifa()!=0)){
            service.setIdTipoTarifa(tarifa);
            return this.actualizarServicio(service);
        }else {
            return null;
        }
    }

    @Override
    public Service actualizarTipoPagoServicio(Service service,int idTipoPago) {
        return null;/*<----------*/
    }

    public Service restringirRecepcionista(Service service){
        Receptionist receptionist=service.getIdRecep();
        Receptionist recep=new Receptionist();

        recep.setIdRecep(receptionist.getIdRecep());
        recep.setDocRecep(receptionist.getDocRecep());
        recep.setReceptionistNames(receptionist.getReceptionistNames());
        recep.setReceptionistLastNames(receptionist.getReceptionistLastNames());

        service.setIdRecep(recep);
        return service;
    }
    public Receptionist obtenerRecepcionista(Service service){
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
}
