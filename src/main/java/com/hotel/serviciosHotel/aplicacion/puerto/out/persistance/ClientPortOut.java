package com.hotel.serviciosHotel.aplicacion.puerto.out.persistance;

import com.hotel.serviciosHotel.dominio.entidades.Client;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.ArrayList;

public interface ClientPortOut {
    boolean clientExist(int id);
    Client saveClient(Client client) throws ItemAlreadyExistException;
    Client updateClient(Client client) throws SearchItemNotFoundException;
    Client getClientById(Integer id) throws SearchItemNotFoundException;
    Client getClientByDocument(String document) throws SearchItemNotFoundException;
    ArrayList<Client> getClients();
    boolean deleteClientById(Integer id) throws SearchItemNotFoundException;
    boolean deleteClient(Client cliente) throws SearchItemNotFoundException;
}
