package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.Client;

import java.util.List;
import java.util.Optional;

public interface ClientPortOut {
    public Client saveClient(Client client);
    public Client updateClient(Client client);
    public Optional<Client> getClientById(Integer id);
    public Optional<Client> getClientByDocument(String document);
    public List<Client> getClients();
    public boolean deleteClientById(Integer id);
    public boolean deleteClient(Client cliente);
}
