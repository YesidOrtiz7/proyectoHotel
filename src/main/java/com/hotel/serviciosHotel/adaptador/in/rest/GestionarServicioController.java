package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.adaptador.modelResponse.ExtendServicesRequestModel;
import com.hotel.serviciosHotel.adaptador.modelResponse.UpdateRateServiceRequest;
import com.hotel.serviciosHotel.adaptador.modelResponse.UpdateRoomServiceRequest;
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

    private ServicioPortIn servicePortIn;

    @Autowired
    public void setServicePortIn(ServicioPortIn servicePortIn) {
        this.servicePortIn = servicePortIn;
    }

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
    @PostMapping("/nuevaHabitacion")
    public ResponseEntity<Service> actualizarHabitacionServicio(@RequestBody UpdateRoomServiceRequest request){
        Service response=servicePortIn.actualizarHabitacionServicio(request.getIdService(),request.getRoomNumber());
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }
    @PostMapping("/cambiarTarifa")
    public ResponseEntity<Service> actualizarTarifaServicio(@RequestBody UpdateRateServiceRequest request){
        Service response=servicePortIn.actualizarTarifaServicio(request.getIdService(),request.getRateId());
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PostMapping("/extenderServicios")
    public ResponseEntity<Service> ampliarServicio(@RequestBody ExtendServicesRequestModel request){
        /*Service response=servicePortIn.registrarServicio(service);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }*/
        Service response =servicePortIn.ampliarServicio(
                request.getService(),
                request.getDia(),
                request.getHora(),
                request.getMinuto()
        );
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }
    @PostMapping("/cerrarServicio/{idService}")
    public ResponseEntity<Service> cerrarServicioPorId(@PathVariable("idService") int id){

        Service response=servicePortIn.cerrarServicioPorIdServicio(id);
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }
}
