package com.hotel.serviciosHotel.adaptador.out.db;

import com.hotel.serviciosHotel.adaptador.out.db.persistence.ClientesCrudRepository;
import com.hotel.serviciosHotel.adaptador.out.db.mappers.MapperClient;
import com.hotel.serviciosHotel.adaptador.out.db.persistenceModels.Cliente;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ClientPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class ClientRepository implements ClientPortOut {
    private ClientesCrudRepository repository;
    private MapperClient mapper;
    /*--------------------------------------------------------------------------*/
    @Autowired
    public void setRepository(ClientesCrudRepository repository) {
        this.repository = repository;
    }
    @Autowired
    public void setMapper(MapperClient mapper) {
        this.mapper = mapper;
    }
    /*--------------------------------------------------------------------------*/
    @Override
    public Client saveClient(Client client) throws ItemAlreadyExistException {
        //consultar si el documento del cliente no se encuentra ya en la base de datos
        Optional<Cliente> query=repository.findByDocumentoCliente(client.getDocumentoCliente());
        if (repository.existsById(client.getIdCliente())||(query.isPresent())){
            /*si el id o el documento del cliente ya existe se arrojara la excepcion*/
            throw new ItemAlreadyExistException("El cliente ya existe");
        }else {
            Cliente cli=mapper.toCliente(client);
            return mapper.toClient(
                    repository.save(cli)
            );
        }
    }

    @Override
    public Client updateClient(Client client) throws SearchItemNotFoundException {
        /*si existe el cliente se actualizara*/
        if (repository.existsById(client.getIdCliente())){
            Cliente cli=mapper.toCliente(client);
            return mapper.toClient(repository.save(cli));
        }else {
            throw new SearchItemNotFoundException("El cliente no existe");
        }

    }

    @Override
    public Client getClientById(Integer id) throws SearchItemNotFoundException {
        Optional<Cliente> cli=repository.findById(id);
        if (cli.isPresent()){
            return mapper.toClient(cli.get());
        }
        throw new SearchItemNotFoundException("El cliente no existe");
    }

    @Override
    public Client getClientByDocument(String document) throws SearchItemNotFoundException {
        Optional<Cliente> cli=repository.findByDocumentoCliente(document);
        if (cli.isPresent()){
            return mapper.toClient(cli.get());
        }
        throw new SearchItemNotFoundException("El cliente  no existe");
    }

    @Override
    public ArrayList<Client> getClients() {
        Iterable<Cliente> cli= repository.findAll();
        ArrayList<Client> clients = new ArrayList<>();
        cli.iterator().forEachRemaining((client) ->{clients.add(mapper.toClient(client));});
        return clients;
    }

    @Override
    public boolean deleteClientById(Integer id) throws SearchItemNotFoundException {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else {
            throw new SearchItemNotFoundException("El cliente no existe");
        }
    }

    @Override
    public boolean deleteClient(Client cliente) throws SearchItemNotFoundException {
        if (repository.existsById(cliente.getIdCliente())){
            repository.delete(mapper.toCliente(cliente));
            return true;
        }else {
            throw new SearchItemNotFoundException("El cliente no existe");
        }
    }
    @Override
    public boolean clientExist(int id){
        return repository.existsById(id);
    }
}
