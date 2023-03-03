package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
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
@RequestMapping("/recepcionista")
public class GestionarRecepcionistaController {

    private RecepcionistaPortIn service;

    @Autowired
    public void setService(RecepcionistaPortIn service) {
        this.service = service;
    }

    @GetMapping("/documento/{documento}")
    @Operation(summary = "obtener recepcionista por documento",description = "retorna los datos de la recepcionista que posee el documento de identidad especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD_REQUEST")
    })
    public ResponseEntity<ReceptionistEntity> obtenerRecepcionistaPorDocumento(@PathVariable("documento") String documento){
        ReceptionistEntity recep=service.obtenerRecepcionistaPorDocumento(documento);
        if (recep==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(recep,HttpStatus.OK);
        }
    }

    @GetMapping("id/{id}")
    @Operation(summary = "obtener recepcionista por id",description = "retorna los datos de la recepcionista que posee el id especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD_REQUEST")
    })
    public ResponseEntity<ReceptionistEntity> obtenerRecepcionistaPorId(@PathVariable("id")int id) throws SearchItemNotFoundException {
        ReceptionistEntity recep=service.obtenerRecepcionistaPorId(id);
        if (recep==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(recep,HttpStatus.OK);
        }
    }

    @GetMapping("/recepcionistas")
    @Operation(summary = "obtener todas las recepcionistas",description = "retorna todo el listado" +
            " de recepcionistas existentes en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<List<ReceptionistEntity>> obtenerRecepcionistas(){
        List<ReceptionistEntity> receptionistEntities =service.obtenerRecepcionistas();
        if (receptionistEntities ==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(receptionistEntities,HttpStatus.OK);
        }
    }

    @PutMapping("/actualizar")
    @Operation(summary = "actualizar recepcionista",description = "permite actualizar los datos de una recepcinista," +
            " para esto se debe enviar una entidad cuyo id no sea nulo y exista en la base de datos," +
            " si la actualizacion fue exitosa se retornara la entidad recepcionista con los nuevos datos registrados en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD_REQUEST")
    })
    public ResponseEntity<ReceptionistEntity> actualizarRecepcionistas(@RequestBody ReceptionistEntity receptionist){
        ReceptionistEntity response=service.actualizarRecepcionista(receptionist);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PostMapping("/registrar")
    @Operation(summary = "registra nueva recepcionista",description = "permite registrar una nueva recepcionista," +
            " la entidad recepcionista a registrar no debe tener el campo id, si se registro con exito se retornara la entidad" +
            "recepcionista con el id asignado en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "400",description = "BAD_REQUEST")
    })
    public ResponseEntity<ReceptionistEntity> registrarRecepcionista(@RequestBody ReceptionistEntity receptionist){
        ReceptionistEntity response=service.registrarRecepcionista(receptionist);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }
}
