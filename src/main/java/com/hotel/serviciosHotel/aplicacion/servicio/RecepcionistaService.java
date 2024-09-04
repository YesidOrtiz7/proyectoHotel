package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ReceptionistPortOut;
import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.InvalidCharacterException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecepcionistaService implements RecepcionistaPortIn {

    private ReceptionistPortOut portOut;
    /*--------------------------------------------------------------------------*/
    @Autowired
    public void setPortOut(ReceptionistPortOut portOut) {
        this.portOut = portOut;
    }
    /*--------------------------------------------------------------------------*/
    @Override
    public boolean existenciaRecepcionista(int id) {
        return portOut.receptionistExist(id);
    }

    @Override
    public ReceptionistEntity registrarRecepcionista(ReceptionistEntity receptionist) throws ItemAlreadyExistException, InvalidCharacterException {
        if (!receptionist.getDocRecep().matches("^\\d+$")){
            throw new InvalidCharacterException("El documento debe contener solo caracteres numericos");
        }
        if (portOut.receptionistExist(receptionist.getIdRecep())){
            throw new ItemAlreadyExistException("La recepcionista ya existe");
        }
        return portOut.saveRecepcionist(receptionist);
    }

    @Override
    public ReceptionistEntity actualizarRecepcionista(ReceptionistEntity receptionist) throws SearchItemNotFoundException, InvalidCharacterException {
        if (!receptionist.getDocRecep().matches("^\\d+$")){
            throw new InvalidCharacterException("El documento debe contener solo caracteres numericos");
        }
        if (!portOut.receptionistExist(receptionist.getIdRecep())){
            throw new SearchItemNotFoundException("La recepcionista no existe");
        }
        return portOut.updateRecepcionist(receptionist);
    }

    @Override
    public ReceptionistEntity obtenerRecepcionistaPorId(Integer id) throws SearchItemNotFoundException {
        ReceptionistEntity receptionist=portOut.getRecepcionistById(id);
        if (receptionist!=null){
            return receptionist;
        }else {
            throw new SearchItemNotFoundException("la recepcionista identificada con el id "+id+" no existe");
        }
    }

    @Override
    public ReceptionistEntity obtenerRecepcionistaPorDocumento(String document) throws SearchItemNotFoundException {
        return portOut.getRecepcionistByDocument(document);
    }

    @Override
    public List<ReceptionistEntity> obtenerRecepcionistas() {
        return portOut.getRecepcionist();
    }

    @Override
    public boolean eliminarRecepcionistaPorId(Integer id) throws SearchItemNotFoundException {
        return portOut.deleteRecepcionistById(id);
    }

    @Override
    public boolean eliminarRecepcionista(ReceptionistEntity receptionist) throws SearchItemNotFoundException {
        return portOut.deleteRecepcionist(receptionist);
    }
}
