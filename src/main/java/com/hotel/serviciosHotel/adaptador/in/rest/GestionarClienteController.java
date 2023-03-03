package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.ClientePortIn;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @GetMapping("/documento/{documento}")
    @Operation(summary = "obtener cliente por documento", description = "obtiene el cliente que posee el documento de identidad especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<Client> obtenerClientePorDocumento(@PathVariable("documento") String documento) {
        Client cli = service.consultarClientePorDocumento(documento);
        if (cli == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(cli, HttpStatus.OK);
        }
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "obtener un cliente por id",description = "obtener cliente especificando su respectivo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<Client> obtenerClientePorId(@PathVariable("id") int id) throws SearchItemNotFoundException {
        Client cli = service.consultarClientePorId(id);
        if (cli == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(cli, HttpStatus.OK);
        }
    }
    @GetMapping("/clientes")
    @Operation(summary = "Obtener todo el listado de clientes registrados en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<List<Client>> obtenerClientes(){
        List<Client> clients= service.obtenerClientes();
        if (clients==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(clients,HttpStatus.OK);
        }
    }

    @PutMapping("/actualizar")
    @Operation(summary = "actualizar un cliente",description = "permite actualizar un cliente, " +
            "para esto se debe enviar una entidad cliente cuyo id no sea nulo y exista en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<Client> actualizarCliente(@RequestBody Client client){
        Client clientResponse=service.actualizarCliente(client);
        if (clientResponse==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(clientResponse,HttpStatus.OK);
        }
    }
    @PostMapping("/registrarCliente")
    @Operation(summary = "registrar cliente",description = "permite registrar un nuevo cliente, para esto" +
            " la entidad cliente a registrar no debe tener el campo id, si el registro es exitoso " +
            "se retornara la entidad cliente con el id que se le ha asignado en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<Client> registrarCliente(@RequestBody Client client){
        Client clientResponse=service.registrarCliente(client);
        if (clientResponse==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(clientResponse,HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "eliminar cliente por id",description = "permite eliminar un cliente especificando su respectivo id," +
            " para esto el respectivo id del cliente debe estar registrado en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity eliminarCliente(@PathVariable("id") int id){
        return service.eliminarCliente(id)?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
