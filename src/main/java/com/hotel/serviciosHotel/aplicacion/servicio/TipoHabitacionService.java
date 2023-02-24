package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.TipoHabitacionPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.RoomTypePortOut;
import com.hotel.serviciosHotel.dominio.entidades.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoHabitacionService implements TipoHabitacionPortIn {
    private RoomTypePortOut portOut;

    @Autowired
    public void setPortOut(RoomTypePortOut portOut) {
        this.portOut = portOut;
    }

    @Override
    public RoomType registrarTipoHabitacion(RoomType tipo) {
        return portOut.registrarTipo(tipo);
    }

    @Override
    public List<RoomType> obtenerTipoHabitaciones() {
        return portOut.obtenerTipos();
    }

    @Override
    public RoomType obtenerTipoHabitacionPorId(int id) {
        Optional<RoomType> response=portOut.obtenerTipoPorId(id);
        if (response==null){
            return null;
        }else {
            return response.get();
        }
    }

    @Override
    public RoomType actualizarTipoHabitacion(RoomType tipo) {
        return portOut.actualizarTipo(tipo);
    }

    @Override
    public boolean eliminarTipoHabitacion(RoomType tipo) {
        return portOut.eliminarTipo(tipo);
    }

    @Override
    public boolean eliminarTipoHabitacionPorId(int idTipo) {
        return portOut.eliminarTipoPorId(idTipo);
    }

    @Override
    public boolean tipoHabitacionExiste(int idTipo) {
        return portOut.tipoExistePorId(idTipo);
    }
}
