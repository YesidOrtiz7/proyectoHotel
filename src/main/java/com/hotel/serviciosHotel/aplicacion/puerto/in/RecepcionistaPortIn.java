package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface RecepcionistaPortIn {
    public boolean existenciaRecepcionista(int id);
    public ReceptionistEntity registrarRecepcionista(ReceptionistEntity receptionist);
    public ReceptionistEntity actualizarRecepcionista(ReceptionistEntity receptionist);
    public ReceptionistEntity obtenerRecepcionistaPorId(Integer id) throws SearchItemNotFoundException;
    public ReceptionistEntity obtenerRecepcionistaPorDocumento(String document);
    public List<ReceptionistEntity> obtenerRecepcionistas();
    public boolean eliminarRecepcionistaPorId(Integer id);
    public boolean eliminarRecepcionista(ReceptionistEntity receptionist);
}
