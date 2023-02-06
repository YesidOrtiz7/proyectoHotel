package com.hotel.serviciosHotel.aplicacion.puerto.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ReceptionistPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Receptionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecepcionistaService implements RecepcionistaPortIn {
    @Autowired
    private ReceptionistPortOut portOut;

    @Override
    public Receptionist registrarRecepcionista(Receptionist receptionist) {
        return portOut.saveRecepcionist(receptionist);
    }

    @Override
    public Receptionist actualizarRecepcionista(Receptionist receptionist) {
        return portOut.updateRecepcionist(receptionist);
    }

    @Override
    public Receptionist obtenerRecepcionistaPorId(Integer id) {
        Optional<Receptionist> receptionist=portOut.getRecepcionistById(id);
        return receptionist.isEmpty()||receptionist==null?null:receptionist.get();
    }

    @Override
    public Receptionist obtenerRecepcionistaPorDocumento(String document) {
        Optional<Receptionist> receptionist=portOut.getRecepcionistByDocument(document);
        return receptionist.isEmpty()||receptionist==null?null:receptionist.get();
    }

    @Override
    public List<Receptionist> obtenerRecepcionistas() {
        List<Receptionist> receptionists=portOut.getRecepcionist();
        if (receptionists.isEmpty()||receptionists==null){
            return null;
        }else {
            return receptionists;
        }
    }

    @Override
    public boolean eliminarRecepcionistaPorId(Integer id) {
        return portOut.deleteRecepcionistById(id);
    }

    @Override
    public boolean eliminarRecepcionista(Receptionist receptionist) {
        return portOut.deleteRecepcionist(receptionist);
    }
}
