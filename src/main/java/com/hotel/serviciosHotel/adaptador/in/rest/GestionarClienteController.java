package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.ClientePortIn;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class GestionarClienteController {

    private ClientePortIn service;

    @Autowired
    public void setService(ClientePortIn service) {
        this.service = service;
    }

    @GetMapping("/documento/{document}")
    public ResponseEntity<Client> obtenerClientePorDocumento(@PathVariable("document") String documento) {
        Client cli = service.consultarClientePorDocumento(documento);
        if (cli != null){
            return new ResponseEntity<>(cli, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Client> obtenerClientePorId(@PathVariable("id") int id){
        Client cli = service.consultarClientePorId(id);
        if (cli != null){
            return new ResponseEntity<>(cli, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/clientes")
    public ResponseEntity<List<Client>> obtenerClientes(){
        List<Client> clients= service.obtenerClientes();
        if (clients==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(clients,HttpStatus.OK);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Client> actualizarCliente(@RequestBody Client client){
        Client clientResponse=service.actualizarCliente(client);
        if (clientResponse==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(clientResponse,HttpStatus.OK);
        }
    }
    @PostMapping("/registrarCliente")
    public ResponseEntity<Client> registrarCliente(@RequestBody Client client){
        Client clientResponse=service.registrarCliente(client);
        if (clientResponse==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(clientResponse,HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminarCliente(@PathVariable("id") int id){
        return service.eliminarCliente(id)?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
