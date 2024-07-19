package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.BusinessConfigurationPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.in.EstadoHabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.RoomStatePortOut;
import com.hotel.serviciosHotel.dominio.entidades.BusinessConfiguration;
import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoHabitacionService implements EstadoHabitacionPortIn {
    private RoomStatePortOut portOut;
    private BusinessConfigurationPortIn configurationPortIn;
    @Autowired
    public void setConfigurationPortIn(BusinessConfigurationPortIn configurationPortIn) {
        this.configurationPortIn = configurationPortIn;
    }

    @Autowired
    public void setPortOut(RoomStatePortOut portOut) {
        this.portOut = portOut;
    }

    @Override
    public RoomStatus registrarEstadoHabitacion(RoomStatus estado) {
        return portOut.registrarEstado(estado);
    }

    @Override
    public List<RoomStatus> obtenerEstadoHabitaciones() {
        return portOut.obtenerEstados();
    }

    @Override
    public RoomStatus obtenerEstadoHabitacionPorId(int id) throws SearchItemNotFoundException {
        Optional<RoomStatus> response=portOut.obtenerEstadoPorId(id);
        if (!response.isEmpty()){
            return response.get();
        }else {
            throw new SearchItemNotFoundException("el estado habitacion con el id "+id+" no existe");
        }
    }

    @Override
    public RoomStatus actualizarEstadoHabitacion(RoomStatus estado) throws SearchItemNotFoundException {
        if (estado.isDefaultForServiceShutdown()){
            List<BusinessConfiguration> config=configurationPortIn.getConfigurations();
            BusinessConfiguration c=config.get(0);

            Optional<RoomStatus> estadoAnterior=portOut.obtenerEstadoPorId(
                    c.getIdStateDefaultToCloseService()
            );
            RoomStatus est=estadoAnterior.get();
            est.setDefaultForServiceShutdown(false);
            portOut.actualizarEstado(est);

            c.setIdStateDefaultToCloseService(estado.getIdStatus());
            configurationPortIn.updateConfiguration(c);
        }
        return portOut.actualizarEstado(estado);
    }

    @Override
    public boolean eliminarEstadoHabitacion(RoomStatus status) {
        return portOut.eliminarEstado(status);
    }

    @Override
    public boolean eliminarEstadoHabitacion(int idStatus) {
        return portOut.eliminarEstadoPorId(idStatus);
    }
}
