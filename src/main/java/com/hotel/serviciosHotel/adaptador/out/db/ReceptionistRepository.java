package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.MapperRecepcionist;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.RecepcionistasCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Recepcionista;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ReceptionistPortOut;
import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
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
    public ReceptionistEntity saveRecepcionist(ReceptionistEntity receptionist) {
        Optional<Recepcionista> query=repository.findByDocRecep(receptionist.getDocRecep());
        if (repository.existsById(receptionist.getIdRecep())&&(!query.isEmpty()||query!=null)){
            return null;
        }else {
            Recepcionista recep=mapper.toRecepcionista(receptionist);
            return mapper.toReceptionist(
                    repository.save(recep)
            );
        }
    }

    @Override
    public ReceptionistEntity updateRecepcionist(ReceptionistEntity receptionist) {
        if (repository.existsById(receptionist.getIdRecep())){
            Recepcionista recep=mapper.toRecepcionista(receptionist);
            return mapper.toReceptionist(repository.save(recep));
        }else {
            return null;
        }
    }

    @Override
    public Optional<ReceptionistEntity> getRecepcionistById(Integer id) {
        Optional<Recepcionista> recepcionista=repository.findById(id);
        if (recepcionista.isEmpty()||recepcionista==null){
            return Optional.empty();
        }else {
            return Optional.of(
                    mapper.toReceptionist(
                            recepcionista.get()
                    )
            );
        }
    }

    @Override
    public Optional<ReceptionistEntity> getRecepcionistByDocument(String document) {
        Optional<Recepcionista> recepcionista=repository.findByDocRecep(document);
        if (recepcionista.isEmpty()||recepcionista==null){
            return Optional.empty();
        }else {
            return Optional.of(
                    mapper.toReceptionist(
                            recepcionista.get()
                    )
            );
        }
    }

    @Override
    public List<ReceptionistEntity> getRecepcionist() {
        Iterable<Recepcionista> recepcionistas=repository.findAll();
        List<ReceptionistEntity> receptionistEntities =new ArrayList<>();
        for (Recepcionista recep:recepcionistas){
            receptionistEntities.add(
                    mapper.toReceptionist(recep)
            );
        }
        return receptionistEntities;
    }

    @Override
    public boolean deleteRecepcionistById(Integer id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean deleteRecepcionist(ReceptionistEntity receptionist) {
        if (repository.existsById(receptionist.getIdRecep())){
            repository.delete(
                    mapper.toRecepcionista(receptionist)
            );
            return true;
        }else{
            return false;
        }
    }
}
