package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.BusinessConfigurationPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.BusinessConfigurationPortOut;
import com.hotel.serviciosHotel.dominio.entidades.BusinessConfiguration;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.GenericException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessConfigurationService implements BusinessConfigurationPortIn {
    private BusinessConfigurationPortOut portOut;
    @Autowired
    public void setPortOut(BusinessConfigurationPortOut portOut) {
        this.portOut = portOut;
    }
/*-------------------------------------------------------------------------------*/
    @Override
    public List<BusinessConfiguration> getConfigurations() {
        return portOut.getConfigurations();
    }

    @Override
    public BusinessConfiguration saveConfiguration(BusinessConfiguration config) throws GenericException {
        List<BusinessConfiguration> configurations=portOut.getConfigurations();
        if (configurations.size()<=1){
            return portOut.saveConfiguration(config);
        }else {
            throw new GenericException("no es posible registrar esta configuracion debido a que ya existe"+
                    " una configuracion en la base de datos");
        }
    }

    @Override
    public BusinessConfiguration updateConfiguration(BusinessConfiguration config) throws SearchItemNotFoundException {
        if (portOut.configurationByIdExist(config.getId())){

            return portOut.updateConfiguration(config);
        }else {
            throw new SearchItemNotFoundException("la configuracion que esta tratando de actualizar no existe");
        }
    }

    @Override
    public BusinessConfiguration getConfigurationById(int id) throws SearchItemNotFoundException {
        Optional<BusinessConfiguration> config=portOut.getConfigurationById(id);
        if (config.isPresent()){
            return config.get();
        }else {
            throw new SearchItemNotFoundException("la configuracion con el id "+id+" no existe");
        }
    }
}
