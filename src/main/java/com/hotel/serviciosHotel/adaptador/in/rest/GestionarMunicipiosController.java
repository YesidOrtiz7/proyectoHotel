package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.MunicipioPortIn;
import com.hotel.serviciosHotel.dominio.entidades.Municipios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipio")
public class GestionarMunicipiosController {
    @Autowired
    private MunicipioPortIn service;

    @GetMapping("/municipios")
    public ResponseEntity<List<Municipios>> obtenerMunicipios(){
        List<Municipios> response=service.obtenerMunicipios();
        if (response.isEmpty()||response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Municipios> registrarMunicipio(@RequestBody Municipios municipios){
        Municipios response=service.registrarMunicipio(municipios);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Municipios> obtenerMunicipiosPorId(@PathVariable("id")int id){
        Municipios response=service.obtenerMunicipioPorId(id);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<Municipios> actualizarMunicipio(@RequestBody Municipios municipios){
        Municipios response=service.actualizarMunicipio(municipios);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
    }

    public ResponseEntity eliminarMunicipio(@RequestBody Municipios municipio){
        if (service.eliminarMunicipio(municipio)){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}