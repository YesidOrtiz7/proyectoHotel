package com.hotel.serviciosHotel.adaptador.in.rest;

import com.hotel.serviciosHotel.adaptador.modelResponse.ExtendServicesRequestModel;
import com.hotel.serviciosHotel.adaptador.modelResponse.OnlyId;
import com.hotel.serviciosHotel.adaptador.modelResponse.UpdateRateServiceRequest;
import com.hotel.serviciosHotel.adaptador.modelResponse.UpdateRoomServiceRequest;
import com.hotel.serviciosHotel.aplicacion.puerto.in.ServicioPortIn;
import com.hotel.serviciosHotel.dominio.entidades.Service;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.GenericException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicio")
public class GestionarServicioController {

    private ServicioPortIn servicePortIn;
    //@Qualifier("ServicioService2")
    @Autowired
    public void setServicePortIn(ServicioPortIn servicePortIn) {
        this.servicePortIn = servicePortIn;
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "obtener servico por id", description = "retorna el servicio que posee el id especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD_REQUEST")
    })
    public ResponseEntity obtenerServicioPorId(@PathVariable("id")int id) {
        try {
            Service response=servicePortIn.consultarServicioPorId(id);
            if (response==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }catch (SearchItemNotFoundException e){
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/todos")
    @Operation(summary = "obtener todos los servicios", description = "retorna todos los servicios registrados en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    public ResponseEntity<List<Service>> obtenerTodosLosServicios(){
        List<Service> response=servicePortIn.consultarServicios();
        if (response==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }
    @PostMapping("/nuevo")
    @Operation(summary = "crear nuevo servicio",description = "permite registrar un nuevo servico," +
            " para esto la entidad: \n" +
            "* no debe tener el campo id\n" +
            "* ninguno de sus campos debe ser nulo\n" +
            "* el campo estado toma valores de 1 y 0, en donde 1 es activo y 0 es cerrado\n" +
            "* el campo pago puede ser 0 ya que el precio se establecera al momento de cerrar el servico\n" +
            "* la fecha del campo salida debe ser mayor que la fecha del campo entrada\n"+
            "* los campos fecha entrada y salida pueden ser nulos, en cuyo caso se establecera como fecha de entrada la hora actual, " +
            "como fecha de salida la fecha actual mas un dia")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity crearServicio(@RequestBody Service service) {
        try {
            Service response=servicePortIn.registrarServicio(service);
            if (response==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>(response,HttpStatus.CREATED);
            }
        }catch (SearchItemNotFoundException | GenericException | ItemAlreadyExistException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/nuevaHabitacion")
    @Operation(summary = "actualizar habitacion del servicio",description = "este metodo esta construido para las ocaciones en las " +
            "que se debe reubicar a el cliente en otra habitacion, para esto se debe especificar el id del servicio en el que esta el cliente" +
            "y el numero de la habitacion, en el caso de que la actualizacion sea exitosa, se retornara la entidad servicio con los nuevos datos registrados en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity actualizarHabitacionServicio(@RequestBody UpdateRoomServiceRequest request) {
        try {
            Service response=servicePortIn.actualizarHabitacionServicio(request.getIdService(),request.getRoomNumber());
            if (response==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }catch (SearchItemNotFoundException | ItemAlreadyExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/cambiarTarifa")
    @Operation(summary = "actualizar la tarifa del servicio",description = "actualizar la tarifa del servicio"+
            " requerido, para esto se debe especificar el id del servicio deseado" +
            " y el id de la tarifa a la que se va a cambiar")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity actualizarTarifaServicio(@RequestBody UpdateRateServiceRequest request) {
        try {
            Service response=servicePortIn.actualizarTarifaServicio(request.getIdService(),request.getRateId());
            if (response==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
        }catch (SearchItemNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/extenderServicios")
    @Operation(summary = "ampliar tiempo limite del servicio",description = "este metodo esta construido para los"+
            " casos en los que se debe extender" +
            " el tiempo limite del servicio, para esto se debe enviar la entidad servicio deseada, seguido de el"+
            " tiempo que se va a extender, en el siguiente orden:\n" +
            "* dias a extender." +
            "* horas a extender." +
            "* minutos a extender.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity ampliarServicio(@RequestBody ExtendServicesRequestModel request) {
        try {
            Service service=servicePortIn.consultarServicioPorId(request.getService());
            if (service.getState()){
                Service response =servicePortIn.ampliarServicio(
                        service,
                        request.getDia(),
                        request.getHora(),
                        request.getMinuto()
                );
                if (response==null){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }else {
                    return new ResponseEntity<>(response,HttpStatus.OK);
                }
            }else {
                return new ResponseEntity<>("El servicio al que se le esta tratando de ampliar ya se encuentra cerrado",HttpStatus.BAD_REQUEST);
            }
        }catch (SearchItemNotFoundException | GenericException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/cerrarServicio")
    @Operation(summary = "cerrar servicio por id",description = "este metodo permite cerrar un servicio, realizando el cobro automatico teniendo en" +
            "cuenta la fecha de entrada y salida del servicio, y ademas se cambia el estado del servicio de 1 a 0\n para cerrar el servico solo se debe" +
            " especificar el id del servicio")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "BAD REQUEST")
    })
    public ResponseEntity cerrarServicioPorId(@RequestBody OnlyId id){
        //System.out.println(id.getId());
        try {
            Service response = servicePortIn.cerrarServicioPorIdServicio(id.getId());
            if (response == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (SearchItemNotFoundException | GenericException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/pagarServicio")
    public ResponseEntity pagarServicioPorId(@RequestBody OnlyId id){
        System.out.println(id.getId());
        try {
            Service res = servicePortIn.pagarServicio(id.getId());
            if (res !=null){
                return new ResponseEntity(res,HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity("No ha sido posible pagar",HttpStatus.BAD_REQUEST);
            }
        }catch (SearchItemNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
