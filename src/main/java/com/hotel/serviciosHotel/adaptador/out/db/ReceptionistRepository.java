package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.MapperRecepcionist;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.RecepcionistasCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Recepcionista;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ReceptionistPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Receptionist;
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
    public Receptionist saveRecepcionist(Receptionist receptionist) {
        Optional<Recepcionista> query=repository.findByDocRecep(receptionist.getDocRecep());
        if (repository.existsById(receptionist.getIdRecep())&&(!query.isEmpty()||query==null)){
            return null;
        }else {
            Recepcionista recep=mapper.toRecepcionista(receptionist);
            return mapper.toReceptionist(
                    repository.save(recep)
            );
        }
    }

    @Override
    public Receptionist updateRecepcionist(Receptionist receptionist) {
        if (repository.existsById(receptionist.getIdRecep())){
            Recepcionista recep=mapper.toRecepcionista(receptionist);
            return mapper.toReceptionist(repository.save(recep));
        }else {
            return null;
        }
    }

    @Override
    public Optional<Receptionist> getRecepcionistById(Integer id) {
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
    public Optional<Receptionist> getRecepcionistByDocument(String document) {
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
    public List<Receptionist> getRecepcionist() {
        Iterable<Recepcionista> recepcionistas=repository.findAll();
        List<Receptionist> receptionists=new ArrayList<>();
        for (Recepcionista recep:recepcionistas){
            receptionists.add(
                    mapper.toReceptionist(recep)
            );
        }
        return receptionists;
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
    public boolean deleteRecepcionist(Receptionist receptionist) {
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
