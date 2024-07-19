package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.mappers.MapperConfiguration;
import com.hotel.serviciosHotel.adaptador.out.db.persistence.ConfiguracionCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Configuracion;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.BusinessConfigurationPortOut;
import com.hotel.serviciosHotel.dominio.entidades.BusinessConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ConfiguracionRepositoryImpl implements BusinessConfigurationPortOut {
    @Autowired
    private ConfiguracionCrudRepository repository;
    @Autowired
    private MapperConfiguration mapper;
    @Override
    public BusinessConfiguration saveConfiguration(BusinessConfiguration config) {
        if(repository.existsById(config.getId())){
            return null;
        }
        Configuracion response=mapper.toConfiguracion(config);
        return mapper.toBussinesConfiguration(
                repository.save(response)
        );
    }

    @Override
    public BusinessConfiguration updateConfiguration(BusinessConfiguration config) {
        if (repository.existsById(config.getId())){
            Configuracion configuracion=mapper.toConfiguracion(config);
            return mapper.toBussinesConfiguration(repository.save(configuracion));
        }else {
            return null;
        }
    }

    @Override
    public Optional<BusinessConfiguration> getConfigurationById(Integer id) {
        Optional<Configuracion> configuracion=repository.findById(id);
        if (configuracion.isEmpty()||configuracion==null){
            return Optional.empty();
        }else {
            return Optional.of(
                    mapper.toBussinesConfiguration(
                            configuracion.get()
                    )
            );
        }
    }

    @Override
    public List<BusinessConfiguration> getConfigurations() {
        Iterable<Configuracion> config=repository.findAll();
        List<BusinessConfiguration> response=new ArrayList<>();
        config.iterator()
                .forEachRemaining(
                        (conf)->response
                                .add(
                                        mapper.toBussinesConfiguration(conf)
                                )
                );
        return response;
    }

    @Override
    public boolean deleteConfigurationById(Integer id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteConfiguration(BusinessConfiguration config) {
        if (repository.existsById(config.getId())){
            repository.delete(
                    mapper.toConfiguracion(config)
            );
            return true;
        }
        return false;
    }

    @Override
    public boolean configurationByIdExist(Integer id) {
        return repository.existsById(id);
    }
}
