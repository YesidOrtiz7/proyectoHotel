package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.TarifaPortIn;
import com.hotel.serviciosHotel.dominio.entidades.RateType;
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
@RequestMapping("/tarifas")
public class GestionarTarifasController {

    private TarifaPortIn service;

    @Autowired
    public void setService(TarifaPortIn service) {
        this.service = service;
    }

    @GetMapping("/todas")
    @Operation(summary = "obtener todas las tarifas",description = "retorna la lista de todas las tarifas existentes en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<List<RateType>> obtenerTarifas(){
        List<RateType> response=service.obtenerTarifas();
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PostMapping("/nueva")
    @Operation(summary = "registrar nueva tarifa",description = "registra una nueva tarifa, para esto la entidad no debe tener el campo id," +
            " si el registro es exitoso, retornara la entidad tipo tarifa con el id asignado por la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<RateType> crearNuevaTarifa(@RequestBody RateType rate){
        RateType response=service.registrarTarifa(rate);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "obtener tarifa por id",description = "retorna la entidad tarifa que posee el id especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD_REQUEST")
    })
    public ResponseEntity<RateType> obtenerTarifaPorId(@PathVariable("id") int id) throws SearchItemNotFoundException {
        RateType response=service.obtenerTarifaPorId(id);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PutMapping("/actualizar")
    @Operation(summary = "actualizar tarifa", description = "permite actualizar una tarifa" +
            "para esto se debe enviar una entidad tipo tarifa cuyo id no sea nulo y exista en la base de datos, " +
            "si la actualizacion fue exitosa se retornara la entidad tarifa con los nuevos datos registrados en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<RateType> actualizarTarifa(@RequestBody RateType rate){
        RateType response=service.actualizarTarifa(rate);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @DeleteMapping("/eliminar")
    @Operation(summary = "eliminar tarifa",description = "elimina la entidad tarifa de la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity eliminarTarifa(@RequestBody RateType rateType){
        if (service.eliminarTarifa(rateType)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
