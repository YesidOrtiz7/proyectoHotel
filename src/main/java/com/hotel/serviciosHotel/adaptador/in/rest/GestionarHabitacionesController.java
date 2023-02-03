package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.GestionarHabitacionPortIn;
import com.hotel.serviciosHotel.dominio.entidades.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/habitaciones")
public class GestionarHabitacionesController {
    @Autowired
    private GestionarHabitacionPortIn service;

    @GetMapping("/numeroHabitacion/{num}")
    public ResponseEntity<Room> obtenerHabitacionPorNumero(@PathVariable("num") int num){
        return new ResponseEntity<>(service.getRoomByNumber(num), HttpStatus.OK);
    }

    @GetMapping("/todas")
    public ResponseEntity<ArrayList<Room>> obtenerHabitaciones(){
        ArrayList<Room> habitaciones=service.getRooms();
        if (habitaciones.isEmpty()||habitaciones == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(service.getRooms(), HttpStatus.OK);
        }
    }
}
