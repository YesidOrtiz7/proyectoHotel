package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ReceptionistPortOut;
import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecepcionistaService implements RecepcionistaPortIn {

    private ReceptionistPortOut portOut;

    @Autowired
    public void setPortOut(ReceptionistPortOut portOut) {
        this.portOut = portOut;
    }

    @Override
    public ReceptionistEntity registrarRecepcionista(ReceptionistEntity receptionist) {
        return portOut.saveRecepcionist(receptionist);
    }

    @Override
    public ReceptionistEntity actualizarRecepcionista(ReceptionistEntity receptionist) {
        return portOut.updateRecepcionist(receptionist);
    }

    @Override
    public ReceptionistEntity obtenerRecepcionistaPorId(Integer id) throws SearchItemNotFoundException {
        Optional<ReceptionistEntity> receptionist=portOut.getRecepcionistById(id);
        if (receptionist!=null){
            return receptionist.get();
        }else {
            throw new SearchItemNotFoundException("la recepcionista identificada con el id "+id+" no existe");
        }
    }

    @Override
    public ReceptionistEntity obtenerRecepcionistaPorDocumento(String document) {
        Optional<ReceptionistEntity> receptionist=portOut.getRecepcionistByDocument(document);
        return receptionist==null?null:receptionist.get();
    }

    @Override
    public List<ReceptionistEntity> obtenerRecepcionistas() {
        List<ReceptionistEntity> receptionistEntities =portOut.getRecepcionist();
        if (receptionistEntities ==null){
            return null;
        }else {
            return receptionistEntities;
        }
    }

    @Override
    public boolean eliminarRecepcionistaPorId(Integer id) {
        return portOut.deleteRecepcionistById(id);
    }

    @Override
    public boolean eliminarRecepcionista(ReceptionistEntity receptionist) {
        return portOut.deleteRecepcionist(receptionist);
    }
}
