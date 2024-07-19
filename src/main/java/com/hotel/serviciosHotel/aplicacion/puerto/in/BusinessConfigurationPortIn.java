package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.BusinessConfiguration;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.GenericException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface BusinessConfigurationPortIn {
    public List<BusinessConfiguration> getConfigurations();
    public BusinessConfiguration saveConfiguration(BusinessConfiguration config) throws GenericException;
    public BusinessConfiguration updateConfiguration(BusinessConfiguration config);
    public BusinessConfiguration getConfigurationById(int id) throws SearchItemNotFoundException;
}
