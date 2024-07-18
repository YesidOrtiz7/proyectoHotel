package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.HabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.ServicioPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.TarifaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ServicePortOut;
import com.hotel.serviciosHotel.dominio.entidades.Service;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@org.springframework.stereotype.Service("ServicioService2")
public class ServicioService2 implements ServicioPortIn {
    /*--------------------------Campos para inyeccion de dependencias-----------------------*/
    private ServicePortOut portOut;
    private HabitacionPortIn habitacionPortIn;
    private TarifaPortIn tarifaService;
    private RecepcionistaPortIn recepcionistaService;
    /*--------------------------Inyeccion de dependencias-----------------------------------*/
    @Autowired
    public void setPortOut(ServicePortOut portOut) {
        this.portOut = portOut;
    }
    @Autowired
    public void setHabitacionPortIn(HabitacionPortIn habitacionPortIn) {
        this.habitacionPortIn = habitacionPortIn;
    }
    @Autowired
    public void setTarifaService(TarifaPortIn tarifaService) {
        this.tarifaService = tarifaService;
    }
    @Autowired
    public void setRecepcionistaService(RecepcionistaPortIn recepcionistaService) {
        this.recepcionistaService = recepcionistaService;
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
    public Service registrarServicio(Service service) throws SearchItemNotFoundException, ItemAlreadyExistException {
        if (!recepcionistaService.existenciaRecepcionista(service.getIdRecep().getIdRecep())){
            throw new SearchItemNotFoundException("la recepcionista que se esta tratando"+
                    " de asociar a este servicio no existe");
        }
        if (!habitacionPortIn.roomExist(service.getIdRoom().getIdRoom())){
            throw new SearchItemNotFoundException("la habitacion que se esta tratando"+
                    " de asociar a este servicio no existe");
        }
        service.setPayment(
                this.cobrar(
                        service.getFechaEntrada(),
                        service.getFechaSalida(),
                        service.getIdRoom().getRoomPrice24Hours(),
                        service.getIdRateType().getPorcentajeTarifa()
                )
        );
        return portOut.registrarServicio(service);
    }

    @Override
    public Service actualizarServicioHabitacionOcupada(Service service) throws SearchItemNotFoundException {
        if (!portOut.servicioExiste(service)){
            return null;
        }
        if (!recepcionistaService.existenciaRecepcionista(service.getIdRecep().getIdRecep())){
            return null;
        }
        return portOut.actualizarServicio(service);
    }

    @Override
    public Service actualizarServicioParaCerrarServicio(Service service) throws SearchItemNotFoundException {
        return null;
    }

    @Override
    public Service actualizarHabitacionServicio(int service, int numeroHabitacion) throws SearchItemNotFoundException {
        return null;
    }

    @Override
    public Service actualizarTarifaServicio(int service, int idTarifa) throws SearchItemNotFoundException {
        return null;
    }

    @Override
    public Service cerrarServicioPorIdServicio(int idService) throws SearchItemNotFoundException {
        return null;
    }

    @Override
    public Service ampliarServicio(Service service, int dia, int hora, int minutos) throws SearchItemNotFoundException {
        return null;
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

}
