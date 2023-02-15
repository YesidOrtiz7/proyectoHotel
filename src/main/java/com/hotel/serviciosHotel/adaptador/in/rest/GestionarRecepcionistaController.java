package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
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
    public ResponseEntity<ReceptionistEntity> obtenerRecepcionistaPorDocumento(@PathVariable("documento") String documento){
        ReceptionistEntity recep=service.obtenerRecepcionistaPorDocumento(documento);
        if (recep==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(recep,HttpStatus.OK);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ReceptionistEntity> obtenerRecepcionistaPorId(@PathVariable("id")int id){
        ReceptionistEntity recep=service.obtenerRecepcionistaPorId(id);
        if (recep==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(recep,HttpStatus.OK);
        }
    }

    @GetMapping("/recepcionistas")
    public ResponseEntity<List<ReceptionistEntity>> obtenerRecepcionistas(){
        List<ReceptionistEntity> receptionistEntities =service.obtenerRecepcionistas();
        if (receptionistEntities ==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(receptionistEntities,HttpStatus.OK);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ReceptionistEntity> actualizarRecepcionistas(@RequestBody ReceptionistEntity receptionist){
        ReceptionistEntity response=service.actualizarRecepcionista(receptionist);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<ReceptionistEntity> registrarRecepcionista(@RequestBody ReceptionistEntity receptionist){
        ReceptionistEntity response=service.registrarRecepcionista(receptionist);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }
}
