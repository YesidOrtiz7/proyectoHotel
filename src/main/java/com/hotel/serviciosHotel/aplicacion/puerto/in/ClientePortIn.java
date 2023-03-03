package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Client;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;

import java.util.List;

public interface ClientePortIn {
    public List<Client>obtenerClientes();
    public Client registrarCliente(Client client);
    public Client actualizarCliente(Client client);
    public boolean eliminarCliente(Integer id);
    public Client consultarClientePorId(Integer id) throws SearchItemNotFoundException;
    public Client consultarClientePorDocumento(String documento);
}
