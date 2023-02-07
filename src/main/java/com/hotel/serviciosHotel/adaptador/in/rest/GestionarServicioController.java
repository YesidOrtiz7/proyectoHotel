package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.ServicioPortIn;
import com.hotel.serviciosHotel.dominio.entidades.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicio")
public class GestionarServicioController {
    @Autowired
    private ServicioPortIn servicePortIn;

    @GetMapping("/id/{id}")
    public ResponseEntity<Service> obtenerServicioPorId(@PathVariable("id")int id){
        Service response=servicePortIn.consultarServicioPorId(id);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }
    @GetMapping("/todos")
    public ResponseEntity<List<Service>> obtenerTodosLosServicios(){
        List<Service> response=servicePortIn.consultarServicios();
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }
    @PostMapping("/nuevo")
    public ResponseEntity<Service> crearServicio(@RequestBody Service service){
        Service response=servicePortIn.registrarServicio(service);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }
    @PostMapping("/nuevaHabitacion/{numHab}")
    public ResponseEntity<Service> actualizarHabitacionServicio(@RequestBody Service service,@PathVariable("numHab")int numeroHabitacion){
        Service response=servicePortIn.actualizarHabitacionServicio(service,numeroHabitacion);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }
    @PostMapping("/nuevaTarifa/{idTarifa}")
    public ResponseEntity<Service> actualizarTarifaServicio(@RequestBody Service service,@PathVariable("idTarifa")int idTarifa){
        Service response=servicePortIn.actualizarTarifaServicio(service,idTarifa);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }

}
