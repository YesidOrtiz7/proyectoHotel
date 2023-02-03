package com.hotel.serviciosHotel.aplicacion.puerto.in;

import com.hotel.serviciosHotel.dominio.entidades.Client;

import java.util.List;

public interface GestionarClienteInterface {
    public List<Client>obtenerClientes();
    public Client registrarCliente(Client client);
    public Client actualizarCliente(Client client);
    public boolean eliminarCliente(Integer id);
    public Client consultarClientePorId(Integer id);
    public Client consultarClientePorDocumento(String documento);
}
