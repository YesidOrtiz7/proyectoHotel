package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.adaptador.out.db.ReceptionistRepository;
import com.hotel.serviciosHotel.aplicacion.puerto.in.RecepcionistaPortIn;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.ReceptionistPortOut;
import com.hotel.serviciosHotel.dominio.entidades.ReceptionistEntity;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.InvalidCharacterException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class RecepcionistaServiceTest {
    private ReceptionistEntity receptionist;
    private ReceptionistEntity receptionist2;
    private ArrayList<ReceptionistEntity> receptionistList=new ArrayList<>();

    private ReceptionistPortOut repository;
    private RecepcionistaService service=new RecepcionistaService();
    @BeforeEach
    void setUp(){
        repository= Mockito.mock(ReceptionistRepository.class);
        service.setPortOut(repository);

        receptionist=new ReceptionistEntity(
                1,
                "1111",
                "Recepcionista nombre",
                "Recepcionista apellido"
        );
        receptionist2=new ReceptionistEntity(
                2,
                "2222",
                "Recepcionista nombre",
                "Recepcionista apellido"
        );
        receptionistList.add(receptionist);
        receptionistList.add(receptionist2);
    }
    @Test
    void existenciaRecepcionista(){
        try {
            Mockito.when(repository.receptionistExist(1)).thenReturn(true);
            service.existenciaRecepcionista(1);
            Mockito.verify(repository,Mockito.times(1)).receptionistExist(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarRecepcionista(){
         try {
             Mockito.when(repository.receptionistExist(1)).thenReturn(false);
             Mockito.when(repository.saveRecepcionist(receptionist)).thenReturn(receptionist);
             Assertions.assertEquals(receptionist,service.registrarRecepcionista(receptionist));
             Mockito.verify(repository,Mockito.times(1)).saveRecepcionist(receptionist);
         } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarRecepcionista_RecepcionistaExiste(){
        try {
            Mockito.when(repository.receptionistExist(1)).thenReturn(true);
            Mockito.when(repository.saveRecepcionist(receptionist)).thenReturn(receptionist);
            Assertions.assertThrows(ItemAlreadyExistException.class,()->service.registrarRecepcionista(receptionist));
            Mockito.verify(repository,Mockito.times(0)).saveRecepcionist(receptionist);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarRecepcionista_DocumentoInvalido(){
        try {
            Mockito.when(repository.receptionistExist(1)).thenReturn(false);
            receptionist.setDocRecep(receptionist.getDocRecep()+"A");
            Mockito.when(repository.saveRecepcionist(receptionist)).thenReturn(receptionist);
            Assertions.assertThrows(InvalidCharacterException.class,()->service.registrarRecepcionista(receptionist));
            Mockito.verify(repository,Mockito.times(0)).saveRecepcionist(receptionist);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void registrarRecepcionista_RepositoryThrowsItemAlreadyExistException(){
        try {
            Mockito.when(repository.receptionistExist(1)).thenReturn(false);
            Mockito.when(repository.saveRecepcionist(receptionist)).thenThrow(ItemAlreadyExistException.class);
            Assertions.assertThrows(ItemAlreadyExistException.class,()->service.registrarRecepcionista(receptionist));
            Mockito.verify(repository,Mockito.times(1)).saveRecepcionist(receptionist);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarRecepcionista(){
        try {
            Mockito.when(repository.updateRecepcionist(receptionist)).thenReturn(receptionist);
            Mockito.when(repository.receptionistExist(1)).thenReturn(true);
            Assertions.assertEquals(receptionist,service.actualizarRecepcionista(receptionist));
            Mockito.verify(repository,Mockito.times(1)).updateRecepcionist(receptionist);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarRecepcionista_RecepcionistaNoExiste(){
        try {
            Mockito.when(repository.updateRecepcionist(receptionist)).thenReturn(receptionist);
            Mockito.when(repository.receptionistExist(1)).thenReturn(false);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.actualizarRecepcionista(receptionist));
            Mockito.verify(repository,Mockito.times(0)).updateRecepcionist(receptionist);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarRecepcionista_DocumentoInvalido(){
        try {
            receptionist.setDocRecep(receptionist.getDocRecep()+"A");
            Mockito.when(repository.receptionistExist(1)).thenReturn(true);
            Mockito.when(repository.updateRecepcionist(receptionist)).thenReturn(receptionist);
            Assertions.assertThrows(InvalidCharacterException.class,()->service.actualizarRecepcionista(receptionist));
            Mockito.verify(repository,Mockito.times(0)).updateRecepcionist(receptionist);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void actualizarRecepcionista_RepositoryThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repository.updateRecepcionist(receptionist)).thenThrow(SearchItemNotFoundException.class);
            Mockito.when(repository.receptionistExist(1)).thenReturn(true);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.actualizarRecepcionista(receptionist));
            Mockito.verify(repository,Mockito.times(1)).updateRecepcionist(receptionist);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistaPorId(){
        try {
            Mockito.when(repository.getRecepcionistById(1)).thenReturn(receptionist);
            Assertions.assertEquals(receptionist,service.obtenerRecepcionistaPorId(1));
            Mockito.verify(repository,Mockito.times(1)).getRecepcionistById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistaPorId_ResponseNull(){
        try {
            Mockito.when(repository.getRecepcionistById(1)).thenReturn(null);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.obtenerRecepcionistaPorId(1));
            Mockito.verify(repository,Mockito.times(1)).getRecepcionistById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistaPorId_RepositoryThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repository.getRecepcionistById(1)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.obtenerRecepcionistaPorId(1));
            Mockito.verify(repository,Mockito.times(1)).getRecepcionistById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistaPorDocumento(){
        try {
            Mockito.when(repository.getRecepcionistByDocument("1111")).thenReturn(receptionist);
            Assertions.assertEquals(receptionist,service.obtenerRecepcionistaPorDocumento("1111"));
            Mockito.verify(repository,Mockito.times(1)).getRecepcionistByDocument("1111");
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistaPorDocumento_RepositoriThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repository.getRecepcionistByDocument("1111")).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.obtenerRecepcionistaPorDocumento("1111"));
            Mockito.verify(repository,Mockito.times(1)).getRecepcionistByDocument("1111");
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void obtenerRecepcionistas(){
        try {
            Mockito.when(repository.getRecepcionist()).thenReturn(receptionistList);
            service.obtenerRecepcionistas();
            Mockito.verify(repository,Mockito.times(1)).getRecepcionist();
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarRecepcionistaPorId(){
        try {
            Mockito.when(repository.deleteRecepcionistById(1)).thenReturn(true);
            Assertions.assertTrue(service.eliminarRecepcionistaPorId(1));
            Mockito.verify(repository,Mockito.times(1)).deleteRecepcionistById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarRecepcionistaPorId_RepositoriThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repository.deleteRecepcionistById(1)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.eliminarRecepcionistaPorId(1));
            Mockito.verify(repository,Mockito.times(1)).deleteRecepcionistById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarRecepcionista(){
        try {
            Mockito.when(repository.deleteRecepcionist(receptionist2)).thenReturn(true);
            Assertions.assertTrue(service.eliminarRecepcionista(receptionist2));
            Mockito.verify(repository,Mockito.times(1)).deleteRecepcionist(receptionist2);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void eliminarRecepcionista_RepositoriThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repository.deleteRecepcionist(receptionist2)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.eliminarRecepcionista(receptionist2));
            Mockito.verify(repository,Mockito.times(1)).deleteRecepcionist(receptionist2);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
