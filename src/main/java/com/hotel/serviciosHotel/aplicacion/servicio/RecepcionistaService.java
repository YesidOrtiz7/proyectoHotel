package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ReceptionistPortOut;
import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecepcionistaService implements RecepcionistaPortIn {
    @Autowired
    private ReceptionistPortOut portOut;

    @Override
    public ReceptionistEntity registrarRecepcionista(ReceptionistEntity receptionist) {
        return portOut.saveRecepcionist(receptionist);
    }

    @Override
    public ReceptionistEntity actualizarRecepcionista(ReceptionistEntity receptionist) {
        return portOut.updateRecepcionist(receptionist);
    }

    @Override
    public ReceptionistEntity obtenerRecepcionistaPorId(Integer id) {
        Optional<ReceptionistEntity> receptionist=portOut.getRecepcionistById(id);
        return receptionist.isEmpty()||receptionist==null?null:receptionist.get();
    }

    @Override
    public ReceptionistEntity obtenerRecepcionistaPorDocumento(String document) {
        Optional<ReceptionistEntity> receptionist=portOut.getRecepcionistByDocument(document);
        return receptionist.isEmpty()||receptionist==null?null:receptionist.get();
    }

    @Override
    public List<ReceptionistEntity> obtenerRecepcionistas() {
        List<ReceptionistEntity> receptionistEntities =portOut.getRecepcionist();
        if (receptionistEntities.isEmpty()|| receptionistEntities ==null){
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
