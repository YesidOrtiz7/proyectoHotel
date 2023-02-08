package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.aplicacion.puerto.in.ClientePortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ClientPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ClientePortIn {
    @Autowired
    private ClientPortOut portOut;

    @Override
    public List<Client> obtenerClientes() {
        List<Client> clients=portOut.getClients();
        if (clients.isEmpty()||clients==null){
            return null;
        }else {
            return clients;
        }
    }

    @Override
    public Client registrarCliente(Client client) {
        return portOut.saveClient(client);
    }

    @Override
    public Client actualizarCliente(Client client) {
        return portOut.updateClient(client);
    }

    @Override
    public boolean eliminarCliente(Integer id) {
        return portOut.deleteClientById(id);
    }

    @Override
    public Client consultarClientePorId(Integer id) {
        Optional<Client> client=portOut.getClientById(id);
        return client.isEmpty()||client==null?null:client.get();
    }

    @Override
    public Client consultarClientePorDocumento(String documento) {
        Optional<Client> client=portOut.getClientByDocument(documento);
        return client.isEmpty()||client==null?null:client.get();
    }

}
