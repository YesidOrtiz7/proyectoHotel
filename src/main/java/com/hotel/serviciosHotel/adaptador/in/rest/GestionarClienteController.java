package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.GestionarClienteInterface;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class GestionarClienteController {
    @Autowired
    private GestionarClienteInterface service;

    @GetMapping("/documento/{document}")
    public ResponseEntity<Client> obtenerClientePorDocumento(@PathVariable("document") String documento) {
        Client cli = service.consultarClientePorDocumento(documento);
        if (cli != null){
            return new ResponseEntity<>(service.consultarClientePorDocumento(documento), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<Client> obtenerClientePorId(@PathVariable int id){
        Client cli = service.consultarClientePorId(id);
        if (cli != null){
            return new ResponseEntity<>(service.consultarClientePorId(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<Client> actualizarCliente(@RequestBody Client client){
        return new ResponseEntity<>(service.actualizarCliente(client),HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminarCliente(@PathVariable("id") int id){
        return service.eliminarCliente(id)?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Client>> obtenerClientes(){
        List<Client> clients= service.obtenerClientes();
        if (clients.isEmpty()|| clients==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(clients,HttpStatus.OK);
        }
    }

    @PostMapping("/registrarCliente")
    public ResponseEntity<Client> registrarCliente(@RequestBody Client client){
        return new ResponseEntity<>(service.registrarCliente(client),HttpStatus.CREATED);
    }

}
