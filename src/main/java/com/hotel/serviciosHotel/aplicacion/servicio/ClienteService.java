package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.ClientePortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ClientPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.InvalidCharacterException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ClientePortIn {
    private ClientPortOut portOut;
    /*--------------------------------------------------------------------------*/
    @Autowired
    public void setPortOut(ClientPortOut portOut) {
        this.portOut = portOut;
    }
    /*--------------------------------------------------------------------------*/
    @Override
    public ArrayList<Client> obtenerClientes() {
        return portOut.getClients();
    }

    @Override
    public Client registrarCliente(Client client) throws ItemAlreadyExistException, InvalidCharacterException {
        if (!client.getDocumentoCliente().matches("^\\d+$")){
            throw new InvalidCharacterException("El documento debe contener solo caracteres numericos");
        }
        if (!client.getCelularCliente().matches("^\\d+$")){
            throw new InvalidCharacterException("El numero de telefono debe contener solo caracteres numericos");
        }
        if (portOut.clientExist(client.getIdCliente())){
            throw new ItemAlreadyExistException("El cliente ya existe");
        }
        return portOut.saveClient(client);
    }

    @Override
    public Client actualizarCliente(Client client) throws SearchItemNotFoundException, InvalidCharacterException {
        if (!client.getDocumentoCliente().matches("^\\d+$")){
            throw new InvalidCharacterException("El documento debe contener solo caracteres numericos");
        }
        if (!client.getCelularCliente().matches("^\\d+$")){
            throw new InvalidCharacterException("El numero de telefono debe contener solo caracteres numericos");
        }
        if (!portOut.clientExist(client.getIdCliente())){
            throw new SearchItemNotFoundException("El cliente con el id "+
                    client.getIdCliente()+" no existe");
        }
        return portOut.updateClient(client);
    }

    @Override
    public boolean eliminarCliente(Integer id) throws SearchItemNotFoundException {
        if (!portOut.clientExist(id)){
            throw new SearchItemNotFoundException("El cliente con el id "+id+
                    " no existe");
        }
        return portOut.deleteClientById(id);
    }

    @Override
    public Client consultarClientePorId(Integer id) throws SearchItemNotFoundException {
        if (!portOut.clientExist(id)){
            throw new SearchItemNotFoundException("El cliente con el id "+id+
                    " no existe");
        }
        return portOut.getClientById(id);
    }

    @Override
    public Client consultarClientePorDocumento(String documento) throws SearchItemNotFoundException{
        return portOut.getClientByDocument(documento);
    }

}
