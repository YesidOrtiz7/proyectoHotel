package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.dominio.entidades.Receptionist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recepcionista")
public class GestionarRecepcionistaController {
    @Autowired
    private RecepcionistaPortIn service;

    @GetMapping("/documento/{documento}")
    public ResponseEntity<Receptionist> obtenerRecepcionistaPorDocumento(@PathVariable("documento") String documento){
        Receptionist recep=service.obtenerRecepcionistaPorDocumento(documento);
        if (recep==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(recep,HttpStatus.OK);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Receptionist> obtenerRecepcionistaPorId(@PathVariable("id")int id){
        Receptionist recep=service.obtenerRecepcionistaPorId(id);
        if (recep==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(recep,HttpStatus.OK);
        }
    }

    @GetMapping("/recepcionistas")
    public ResponseEntity<List<Receptionist>> obtenerRecepcionistas(){
        List<Receptionist> receptionists=service.obtenerRecepcionistas();
        if (receptionists.isEmpty()||receptionists==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(receptionists,HttpStatus.OK);
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<Receptionist> actualizarRecepcionistas(@RequestBody Receptionist receptionist){
        Receptionist response=service.actualizarRecepcionista(receptionist);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<Receptionist> registrarRecepcionista(@RequestBody Receptionist receptionist){
        Receptionist response=service.registrarRecepcionista(receptionist);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }
}
