package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Client;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.InvalidCharacterException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface ClientePortIn {
    List<Client>obtenerClientes();
    Client registrarCliente(Client client) throws ItemAlreadyExistException, InvalidCharacterException;
    Client actualizarCliente(Client client) throws SearchItemNotFoundException, InvalidCharacterException;
    boolean eliminarCliente(Integer id) throws SearchItemNotFoundException;
    Client consultarClientePorId(Integer id) throws SearchItemNotFoundException;
    Client consultarClientePorDocumento(String documento) throws SearchItemNotFoundException;
}
