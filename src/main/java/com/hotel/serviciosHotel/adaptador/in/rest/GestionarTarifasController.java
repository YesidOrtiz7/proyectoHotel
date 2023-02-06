package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.TarifaPortIn;
import com.hotel.serviciosHotel.dominio.entidades.RateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarifas")
public class GestionarTarifasController {
    @Autowired
    private TarifaPortIn service;

    @GetMapping("/todas")
    public ResponseEntity<List<RateType>> obtenerTarifas(){
        List<RateType> response=service.obtenerTarifas();
        if (response.isEmpty()||response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PostMapping("/nueva")
    public ResponseEntity<RateType> crearNuevaTarifa(@RequestBody RateType rate){
        RateType response=service.registrarTarifa(rate);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RateType> obtenerTarifaPorId(@PathVariable("id") int id){
        RateType response=service.obtenerTarifaPorId(id);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<RateType> actualizarTarifa(@RequestBody RateType rate){
        RateType response=service.actualizarTarifa(rate);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarTarifa(@RequestBody RateType rateType){
        if (service.eliminarTarifa(rateType)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
