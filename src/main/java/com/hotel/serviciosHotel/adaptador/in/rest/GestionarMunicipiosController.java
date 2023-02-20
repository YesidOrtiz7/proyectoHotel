package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.MunicipioPortIn;
import com.hotel.serviciosHotel.dominio.entidades.Municipios;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipio")
public class GestionarMunicipiosController {

    private MunicipioPortIn service;

    @Autowired
    public void setService(MunicipioPortIn service) {
        this.service = service;
    }

    @GetMapping("/municipios")
    @Operation(summary = "obtener municipios",description = "obtiene todo el listado de " +
            "municipios registrados en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD_REQUEST")
    })
    public ResponseEntity<List<Municipios>> obtenerMunicipios(){
        List<Municipios> response=service.obtenerMunicipios();
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/nuevo")
    @Operation(summary = "registrar nuevo municipio",description = "permite registrar un nuevo municipio " +
            "la entidad municipio a registrar no debe tener el campo id, si se registro con exito se retornara la entidad " +
            "municipio con el id asignado en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "400",description = "BAD_REQUEST")
    })
    public ResponseEntity<Municipios> registrarMunicipio(@RequestBody Municipios municipios){
        Municipios response=service.registrarMunicipio(municipios);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "obtener municipio por id",description = "retorna el municipio que posee el id especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD_REQUEST")
    })
    public ResponseEntity<Municipios> obtenerMunicipiosPorId(@PathVariable("id")int id){
        Municipios response=service.obtenerMunicipioPorId(id);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PutMapping("/actualizar")
    @Operation(summary = "actualizar municipio", description = "permite actualizar un municipio " +
            "para esto se debe enviar una entidad municipio cuyo id no sea nulo y exista en la base de datos," +
            " si la actualizacion fue exitosa se retornara la entidad municipio con los nuevos datos registrados en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD_REQUEST")
    })
    public ResponseEntity<Municipios> actualizarMunicipio(@RequestBody Municipios municipios){
        Municipios response=service.actualizarMunicipio(municipios);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @DeleteMapping("/eliminar")
    @Operation(summary = "eliminar municipio",description = "elimina la entidad municipio de la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD_REQUEST")
    })
    public ResponseEntity eliminarMunicipio(@RequestBody Municipios municipio){
        if (service.eliminarMunicipio(municipio)){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
