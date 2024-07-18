package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.TipoPagoPortIn;
import com.hotel.serviciosHotel.dominio.entidades.PaymentType;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipoPago")
public class GestionarTipoPagoController {
    private TipoPagoPortIn portIn;

    @Autowired
    public void setPortIn(TipoPagoPortIn portIn) {
        this.portIn = portIn;
    }

    @GetMapping("/{id}")
    @Operation(summary = "obtener tipo pago por id",description = "retorna la entidad tipo pago que posee el id especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD_REQUEST")
    })
    public ResponseEntity<PaymentType> obtenerTipoPagoPorId(@PathVariable("id") int id) throws SearchItemNotFoundException {
        PaymentType response=portIn.obtenerTipoPagoPorId(id);
        if (response == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @GetMapping("/todos")
    @Operation(summary = "obtener todas los tipos de pago",description = "retorna la lista de todos los tipos de pago existentes en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity<List<PaymentType>> obtenerTipoPagos() {
        List<PaymentType> response=portIn.obtenerTipoPagos();
        if (response == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/nuevo")
    public ResponseEntity<PaymentType> nuevoTipoPago(@RequestBody PaymentType type){
        PaymentType response=portIn.guardarTipoPago(type);
        if (response!=null){
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarTipoPago(@RequestBody PaymentType type){
        if (portIn.eliminarTipoPago(type)){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
