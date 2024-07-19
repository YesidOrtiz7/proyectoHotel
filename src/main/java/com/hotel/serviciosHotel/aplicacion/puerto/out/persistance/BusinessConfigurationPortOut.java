package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.BusinessConfiguration;

import java.util.List;
import java.util.Optional;

public interface BusinessConfigurationPortOut {
    public BusinessConfiguration saveConfiguration(BusinessConfiguration config);
    public BusinessConfiguration updateConfiguration(BusinessConfiguration config);
    public Optional<BusinessConfiguration> getConfigurationById(Integer id);
    public List<BusinessConfiguration> getConfigurations();
    public boolean deleteConfigurationById(Integer id);
    public boolean deleteConfiguration(BusinessConfiguration config);
    public boolean configurationByIdExist(Integer id);
}
