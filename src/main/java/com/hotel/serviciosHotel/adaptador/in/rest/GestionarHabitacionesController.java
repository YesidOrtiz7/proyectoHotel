package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.HabitacionPortIn;
import com.hotel.serviciosHotel.dominio.entidades.Room;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping("/habitaciones")
public class GestionarHabitacionesController {

    private HabitacionPortIn service;

    @Autowired
    public void setService(HabitacionPortIn service) {
        this.service = service;
    }

    @GetMapping("/numeroHabitacion/{num}")
    @Operation(summary = "obtener habitacion por numero",description = "retorna la habitacion que posee el numero especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "404",description = "NOT FOUND")
    })
    public ResponseEntity<Room> obtenerHabitacionPorNumero(@PathVariable("num") int num) throws SearchItemNotFoundException {
        Room response=service.getRoomByNumber(num);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @GetMapping("/idHabitacion/{id}")
    @Operation(summary = "obtener habitacion por id",description = "retorma la habitacion que posee el id especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "404",description = "NOT FOUND")
    })
    public ResponseEntity<Room> obtenerHabitacionPorId(@PathVariable("id") int id) throws SearchItemNotFoundException {
        Room response=service.getRoomById(id);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/todas")
    @Operation(summary = "obtener todas las habitaciones", description = "obtiene todo el listado de habitaciones existentes en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<ArrayList<Room>> obtenerHabitaciones(){
        ArrayList<Room> habitaciones=service.getRooms();
        if (habitaciones == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(habitaciones, HttpStatus.OK);
        }
    }

    @PostMapping("/nuevaHabitacion")
    @Operation(summary = "registrar nueva habitacion", description = "registrar una nueva habitacion, para esto la entidad" +
            " habitacion a registrar no debe tener el campo id, si se registro con exito se retornara la entidad habitacion con el id" +
            " asignado en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<Room> registrarNuevaHabitacion(@RequestBody Room room) throws ItemAlreadyExistException {
        Room response=service.createRoom(room);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }
    @PutMapping("/actualizar")
    @Operation(summary = "actualizar habitacion",description = "permite actualizar una habitacion, " +
            "para esto se debe enviar una entidad habitacion cuyo id no sea nulo y exista en la base de datos, si" +
            "la actualizacion fue exitosa entonces se retornara la entidad con los nuevos datos registrados en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<Room> actualizarHabitacion(@RequestBody Room room)
            throws SearchItemNotFoundException{
        Room response=service.updateRoom(room);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

    }
    @PutMapping("/cambiarTipoHabitacion/{hab}/{tipo}")
    @Operation(summary = "cambiar tipo habitacion", description = "permite actualizar el tipo habitacion de una habitacion registrada en la base de datos" +
            " para esto se debe pasar por parametro (en la url) el id del tipo de habitacion al que se va a cambiar (debe existir en la base de datos) y el numero de la habitacion deseada," +
            " si la actualizacion fue exitosa se retornara la entidad habitacion con los nuevos datos registrados en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<Room> cambiarTipoHabitacion(@PathVariable("hab") int habitacion,@PathVariable("tipo") int tipo)
            throws SearchItemNotFoundException{
        Room response=service.changeRoomType(habitacion, tipo);
        if (response==null){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

    }
    @PutMapping("/cambiarEstado/{hab}/{estado}")
    @Operation(summary = "cambiar estado habitacion", description = "permite actualizar el estado de una habitacion de una habitacion registrada en la base de datos" +
            " para esto se debe pasar por parametro (en la url) el id del estado de habitacion al que se va a cambiar (debe existir en la base de datos) y el numero de la habitacion deseada," +
            " si la actualizacion fue exitosa se retornara la entidad habitacion con los nuevos datos registrados en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<Room> cambiarEstadoHabitacion(@PathVariable("hab") int habitacion,@PathVariable("estado") int estado) throws SearchItemNotFoundException{
        Room response=service.changeRoomStatus(habitacion, estado);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

    }
    @DeleteMapping("/eliminarHabitacion")
    @Operation(summary = "eliminar habitacion",description = "elimina la entidad habitacion de la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity eliminarHabitacion(@RequestBody Room room) throws SearchItemNotFoundException{
        if (service.deleteRoom(room)) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/eliminarHabitacionPorId/{id}")
    @Operation(summary = "eliminar habitacion por id",description = "elimina la entidad habitacion que posee el id especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity eliminarHabitacionPorId(@PathVariable("id") int id) throws SearchItemNotFoundException{
        if (service.deleteRoomById(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
