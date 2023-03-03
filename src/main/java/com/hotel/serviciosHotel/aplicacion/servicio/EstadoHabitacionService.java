package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.EstadoHabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.RoomStatePortOut;
import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoHabitacionService implements EstadoHabitacionPortIn {
    private RoomStatePortOut portOut;

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
        if (response!=null){
            return response.get();
        }else {
            throw new SearchItemNotFoundException("el estado habitacion con el id "+id+" no existe");
        }
    }

    @Override
    public RoomStatus actualizarEstadoHabitacion(RoomStatus estado) {
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
