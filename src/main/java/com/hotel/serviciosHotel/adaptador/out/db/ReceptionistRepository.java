package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.MapperRecepcionist;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.RecepcionistasCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Recepcionista;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ReceptionistPortOut;
import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReceptionistRepository implements ReceptionistPortOut {
    @Autowired
    private RecepcionistasCrudRepository repository;
    @Autowired
    private MapperRecepcionist mapper;

    @Override
    public boolean receptionistExist(int id) {
        return repository.existsById(id);
    }

    @Override
    public ReceptionistEntity saveRecepcionist(ReceptionistEntity receptionist) throws ItemAlreadyExistException {
        //consulta si el documento de la recepcionista no se encuentra ya en la base de datos
        Optional<Recepcionista> query=repository.findByDocRecep(receptionist.getDocRecep());
        if (repository.existsById(receptionist.getIdRecep())|| query.isPresent()){
            /*si el id o el documento ya estan registrados, entonces se arroja la excepcion*/
            throw new ItemAlreadyExistException("La recepcionista ya existe");
        }else {
            Recepcionista recep=mapper.toRecepcionista(receptionist);
            return mapper.toReceptionist(
                    repository.save(recep)
            );
        }
    }

    @Override
    public ReceptionistEntity updateRecepcionist(ReceptionistEntity receptionist) throws SearchItemNotFoundException {
        //si existe la recepcionista se va a actualizar
        if (repository.existsById(receptionist.getIdRecep())){
            Recepcionista recep=mapper.toRecepcionista(receptionist);
            return mapper.toReceptionist(repository.save(recep));
        }else {
            throw new SearchItemNotFoundException("La recepcionista no existe");
        }
    }

    @Override
    public ReceptionistEntity getRecepcionistById(Integer id) throws SearchItemNotFoundException {
        Optional<Recepcionista> recepcionista=repository.findById(id);
        if (recepcionista.isPresent()){
            return mapper.toReceptionist(
                    recepcionista.get()
            );
        }
        throw new SearchItemNotFoundException("El cliente no existe");
    }

    @Override
    public ReceptionistEntity getRecepcionistByDocument(String document) throws SearchItemNotFoundException {
        Optional<Recepcionista> recepcionista=repository.findByDocRecep(document);
        if (recepcionista.isPresent()){
            return mapper.toReceptionist(
                    recepcionista.get()
            );
        }
        throw new SearchItemNotFoundException("La recepcionista no existe");
    }

    @Override
    public ArrayList<ReceptionistEntity> getRecepcionist() {
        Iterable<Recepcionista> recepcionistas=repository.findAll();
        ArrayList<ReceptionistEntity> receptionistEntities =new ArrayList<>();
        recepcionistas.iterator().forEachRemaining((recepcionista->{receptionistEntities.add(
                mapper.toReceptionist(recepcionista)
        );}));
        return receptionistEntities;
    }

    @Override
    public boolean deleteRecepcionistById(Integer id) throws SearchItemNotFoundException{
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else {
            throw new SearchItemNotFoundException("La recepcionista no existe");
        }

    }

    @Override
    public boolean deleteRecepcionist(ReceptionistEntity receptionist) throws SearchItemNotFoundException {
        if (repository.existsById(receptionist.getIdRecep())){
            repository.delete(
                    mapper.toRecepcionista(receptionist)
            );
            return true;
        }else{
            throw new SearchItemNotFoundException("La recepcionista no existe");
        }
    }
}
