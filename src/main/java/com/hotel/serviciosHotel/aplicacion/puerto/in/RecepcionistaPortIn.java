package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;

import java.util.List;

public interface RecepcionistaPortIn {
    public ReceptionistEntity registrarRecepcionista(ReceptionistEntity receptionist);
    public ReceptionistEntity actualizarRecepcionista(ReceptionistEntity receptionist);
    public ReceptionistEntity obtenerRecepcionistaPorId(Integer id);
    public ReceptionistEntity obtenerRecepcionistaPorDocumento(String document);
    public List<ReceptionistEntity> obtenerRecepcionistas();
    public boolean eliminarRecepcionistaPorId(Integer id);
    public boolean eliminarRecepcionista(ReceptionistEntity receptionist);
}
