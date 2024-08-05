package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.RoomHistoryPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.HistorialHabitacionesPortOut;
import com.hotel.serviciosHotel.dominio.entidades.RoomHistory;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomHistoryService implements RoomHistoryPortIn {
    private HistorialHabitacionesPortOut portOut;
    /*-----------------------------------------------------------*/
    @Autowired
    public void setPortOut(HistorialHabitacionesPortOut portOut) {
        this.portOut = portOut;
    }
    /*-----------------------------------------------------------*/
    @Override
    public List<RoomHistory> getAllHistory() {
        return portOut.consultarHistorial();
    }

    @Override
    public List<RoomHistory> getHistoryByIdService(int id) {
        return portOut.consultarHistorialPorIdService(id);
    }

    @Override
    public RoomHistory saveHistory(RoomHistory historial) throws ItemAlreadyExistException {
        return portOut.registrarHistorial(historial);
    }

    @Override
    public RoomHistory updateHistory(RoomHistory historial) throws SearchItemNotFoundException {
        return portOut.actualizarHistorial(historial);
    }

    @Override
    public boolean historyExist(int idHistorial) {
        return portOut.existeHistorial(idHistorial);
    }
}
