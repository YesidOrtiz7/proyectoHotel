package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.TipoHabitacionPortIn;
import com.hotel.serviciosHotel.dominio.entidades.RoomType;
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
@RequestMapping("/tipoHabitacion")
public class GestionarTipoHabController {
    private TipoHabitacionPortIn portIn;

    @Autowired
    public void setPortIn(TipoHabitacionPortIn portIn) {
        this.portIn = portIn;
    }

    @PostMapping("/nuevo")
    @Operation(summary = "registrar nuevo tipo habitacion",description = "permite registrar un nuevo tipo habitacion, para esto" +
            " la entidad tipo habitacion a registrar no debe tener el campo id, si el registro es exitoso " +
            "se retornara la entidad tipo habitacion con el id que se le ha asignado en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<RoomType> registrarTipoHab(@RequestBody RoomType type){
        RoomType response=portIn.registrarTipoHabitacion(type);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }

    @GetMapping("/todos")
    @Operation(summary = "obtener todos los tipos",description = "Obtener todo el listado de tipos de habitacion registrados en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<List<RoomType>> obtenerTipos(){
        List<RoomType> response=portIn.obtenerTipoHabitaciones();
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "obtener tipo habitacion por id", description = "obtiene el tipo de habitacion que posee el id especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<RoomType> obtenerTipoPorId(@PathVariable("id")int id) throws SearchItemNotFoundException {
        RoomType response=portIn.obtenerTipoHabitacionPorId(id);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PutMapping("/actualizar")
    @Operation(summary = "actualizar un tipo habitacion",description = "permite actualizar un tipo habitacion, " +
            "para esto se debe enviar una entidad tipo habitacion cuyo id no sea nulo y exista en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<RoomType> actualizarTipoHab(@RequestBody RoomType type){
        RoomType response=portIn.actualizarTipoHabitacion(type);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminarTipoHab(@PathVariable("id")int type){
            if(portIn.eliminarTipoHabitacionPorId(type)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
}
