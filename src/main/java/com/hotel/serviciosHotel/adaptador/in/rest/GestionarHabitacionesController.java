package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.HabitacionPortIn;
import com.hotel.serviciosHotel.dominio.entidades.Room;
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
    public ResponseEntity<Room> obtenerHabitacionPorNumero(@PathVariable("num") int num){
        return new ResponseEntity<>(service.getRoomByNumber(num), HttpStatus.OK);
    }

    @GetMapping("/idHabitacion/{num}")
    public ResponseEntity<Room> obtenerHabitacionPorId(@PathVariable("num") int num){
        return new ResponseEntity<>(service.getRoomById(num), HttpStatus.OK);
    }

    @GetMapping("/todas")
    public ResponseEntity<ArrayList<Room>> obtenerHabitaciones(){
        ArrayList<Room> habitaciones=service.getRooms();
        if (habitaciones == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(service.getRooms(), HttpStatus.OK);
        }
    }

    @PostMapping("/nuevaHabitacion")
    public ResponseEntity<Room> registrarNuevaHabitacion(@RequestBody Room room){
        Room response=service.createRoom(room);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Room> actualizarHabitacion(@RequestBody Room room){
        Room response=service.updateRoom(room);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

    }
    @PutMapping("/cambiarTipoHabitacion/{type}/{room}")
    public ResponseEntity<Room> cambiarTipoHabitacion(@PathVariable("type") int state,@PathVariable("room") int room){
        Room response=service.changeRoomType(room, state);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

    }
    @PutMapping("/cambiarEstado/{state}/{room}")
    public ResponseEntity<Room> cambiarEstadoHabitacion(@PathVariable("state") int state,@PathVariable("room") int room){
        Room response=service.changeRoomStatus(room, state);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

    }
    @DeleteMapping("/eliminarHabitacion")
    public ResponseEntity eliminarHabitacion(@RequestBody Room room){
        if (service.deleteRoom(room)) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/eliminarHabitacionPorId/{id}")
    public ResponseEntity eliminarHabitacionPorId(@PathVariable("id") int id){
        if (service.deleteRoomById(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
