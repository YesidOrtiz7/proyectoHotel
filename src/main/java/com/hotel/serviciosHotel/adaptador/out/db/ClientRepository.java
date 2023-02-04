package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.persistence.ClientesCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.mappers.MapperClient;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Cliente;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ClientPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository implements ClientPortOut {
    @Autowired
    private ClientesCrudRepository repository;
    @Autowired
    private MapperClient mapper;
    @Override
    public Client saveClient(Client client) {
        if (repository.existsById(client.getIdCliente())){
            return null;
        }else {
            Cliente cli=mapper.toCliente(client);
            return mapper.toClient(
                    repository.save(cli)
            );
        }
    }

    @Override
    public Client updateClient(Client client) {
        if (repository.existsById(client.getIdCliente())){
            Cliente cli=mapper.toCliente(client);
            return mapper.toClient(repository.save(cli));
        }else {
            return null;
        }

    }

    @Override
    public Optional<Client> getClientById(Integer id) {
        Optional<Cliente> cli=repository.findById(id);
        if (cli.isEmpty()){
            return Optional.empty();
        }else{
            return Optional.of(
                    mapper.toClient(
                            cli.get()));
            //return null;/*<-------*/
        }
    }

    @Override
    public Optional<Client> getClientByDocument(String document) {
        Optional<Cliente> cli=repository.findByDocumentoCliente(document);
        if (cli.isEmpty()||cli==null){
            return Optional.empty();
        }else{
            return Optional.of(
                    mapper.toClient(
                            cli.get()));
        }
    }

    @Override
    public List<Client> getClients() {
        Iterable<Cliente> cli= repository.findAll();
        List<Client> clients = new ArrayList<>();
        for (Cliente cliente:cli) {
            clients.add(mapper.toClient(cliente));
        }
        return clients;
    }

    @Override
    public boolean deleteClientById(Integer id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteClient(Client cliente) {
        if (repository.existsById(cliente.getIdCliente())){
            repository.delete(mapper.toCliente(cliente));
            return true;
        }else {
            return false;
        }
    }
}
