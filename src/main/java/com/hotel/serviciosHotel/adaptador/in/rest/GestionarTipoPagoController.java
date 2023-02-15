package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.TipoPagoPortIn;
import com.hotel.serviciosHotel.dominio.entidades.Client;
import com.hotel.serviciosHotel.dominio.entidades.PaymentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipoPago")
public class GestionarTipoPagoController {
    private TipoPagoPortIn portIn;

    public void setPortIn(TipoPagoPortIn portIn) {
        this.portIn = portIn;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentType> obtenerTipoPagoPorId(@PathVariable("id") int id) {
        PaymentType response=portIn.obtenerTipoPagoPorId(id);
        if (response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/todos")
    public ResponseEntity<List<PaymentType>> obtenerTipoPagos() {
        List<PaymentType> response=portIn.obtenerTipoPagos();
        if (response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
