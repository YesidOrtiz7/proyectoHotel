package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.aplicacion.puerto.in.TipoPagoPortIn;
import com.hotel.serviciosHotel.dominio.entidades.PaymentType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<PaymentType> obtenerTipoPagoPorId(@PathVariable("id") int id) {
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
}
