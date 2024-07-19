package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.adaptador.modelResponse.IdAndStateRequest;
import com.hotel.serviciosHotel.aplicacion.puerto.in.EstadoHabitacionPortIn;
import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;
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
@RequestMapping("/estados")
public class GestionarEstadoHabController {
    private EstadoHabitacionPortIn portIn;

    @Autowired
    public void setPortIn(EstadoHabitacionPortIn portIn) {
        this.portIn = portIn;
    }

    @PostMapping("/nuevo")
    @Operation(summary = "registrar nuevo estado habitacion",description = "permite registrar un nuevo estado habitacion, para esto" +
            " la entidad estado habitacion a registrar no debe tener el campo id, si el registro es exitoso " +
            "se retornara la entidad estado habitacion con el id que se le ha asignado en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<RoomStatus> registrarEstadoHab(@RequestBody RoomStatus status){
        System.out.println(status.getStatusName());
        RoomStatus response=portIn.registrarEstadoHabitacion(status);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }

    @GetMapping("/todos")
    @Operation(summary = "obtener todos los estados habitacion",description = "Obtener todo el listado de estados de habitacion registrados en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<List<RoomStatus>> obtenerEstadosHabitacion(){
        List<RoomStatus> response=portIn.obtenerEstadoHabitaciones();
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "obtener estado habitacion por id", description = "obtiene el estado de habitacion que posee el id especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<RoomStatus> obtenerEstadoHabitacionPorId(@PathVariable("id")int id) throws SearchItemNotFoundException {
        RoomStatus response=portIn.obtenerEstadoHabitacionPorId(id);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PutMapping("/actualizar")
    @Operation(summary = "actualizar un estado habitacion",description = "permite actualizar un estado habitacion, " +
            "para esto se debe enviar una entidad estado habitacion cuyo id no sea nulo y exista en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<RoomStatus> actualizarEstadoHab(@RequestBody RoomStatus status){
        RoomStatus response=portIn.actualizarEstadoHabitacion(status);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }
    @PutMapping("/actualizarconfiguracionestados")
    public ResponseEntity actualizarConfiguracionEstados(@RequestBody IdAndStateRequest request){
        try {
            if(request.getQuery()==1){
                RoomStatus response=portIn.obtenerEstadoHabitacionPorId(request.getId());
                response.setVisibleOnSelection(request.isState());
                portIn.actualizarEstadoHabitacion(response);
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else if (request.getQuery()==2) {
                RoomStatus response=portIn.obtenerEstadoHabitacionPorId(request.getId());
                response.setDefaultForServiceShutdown(request.isState());
                portIn.actualizarEstadoHabitacion(response);
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

        }catch (SearchItemNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminarEstadoHab(@PathVariable int id){
        if (portIn.eliminarEstadoHabitacion(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
