package com.hotel.serviciosHotel.aplicacion.servicio;

import com.hotel.serviciosHotel.adaptador.out.db.RoomRepository;
import com.hotel.serviciosHotel.aplicacion.puerto.out.persistance.RoomPortOut;
import com.hotel.serviciosHotel.dominio.entidades.Room;
import com.hotel.serviciosHotel.dominio.entidades.RoomStatus;
import com.hotel.serviciosHotel.dominio.entidades.RoomType;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.ItemAlreadyExistException;
import com.hotel.serviciosHotel.exceptionHandler.exceptions.SearchItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

class HabitacionServiceTest {
    private Room roomClean;
    private Room roomInUse;
    private final ArrayList<Room> roomsList=new ArrayList<>();

    private RoomPortOut repositoryMock;
    private HabitacionService service=new HabitacionService();
    @BeforeEach
    void setUp() {
        repositoryMock= Mockito.mock(RoomRepository.class);
        service.setPortOut(repositoryMock);
        roomClean=new Room(
                102,
                new RoomStatus(
                        1,
                        "limpia",
                        true,
                        false,
                        false
                ),
                new RoomType(
                        1,
                        "normal"
                ),
                3100.0,
                1,
                2
        );
        roomInUse=new Room(
                101,
                new RoomStatus(2,
                        "ocupada",
                        false,
                        true,
                        false
                ),
                new RoomType(
                        1,
                        "normal"
                ),
                2000.0,
                1,
                1
        );
        roomsList.add(roomClean);
        roomsList.add(roomInUse);
    }
    @Test
    void roomExist(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(true);
            service.roomExist(1);
            Mockito.verify(repositoryMock,Mockito.times(1)).roomExistById(1);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void createRoom(){
        try {
            Mockito.when(repositoryMock.saveRoom(roomClean)).thenReturn(roomClean);
            Assertions.assertEquals(roomClean,service.createRoom(roomClean));
            Mockito.verify(repositoryMock,Mockito.times(1)).saveRoom(roomClean);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void createRoom_ThrowsItemAlreadyExistException(){
        try {
            //Mockito.when(repositoryMock.saveRoom(roomClean)).thenThrow(new ItemAlreadyExistException("la habitacion con el id " + 2 + " ya existe"));
            Mockito.when(repositoryMock.roomExistById(2)).thenReturn(true);
            Assertions.assertThrows(ItemAlreadyExistException.class,()->service.createRoom(roomClean));
            //Mockito.verify(repositoryMock,Mockito.times(1)).saveRoom(roomClean);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void createRoom_RepositoryThrowsItemAlreadyExistException(){
        try {
            Mockito.when(repositoryMock.saveRoom(roomClean)).thenThrow(new ItemAlreadyExistException("la habitacion con el id " + 2 + " ya existe"));
            Assertions.assertThrows(ItemAlreadyExistException.class,()->service.createRoom(roomClean));
            Mockito.verify(repositoryMock,Mockito.times(1)).saveRoom(roomClean);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void getRooms(){
        Mockito.when(repositoryMock.getRooms()).thenReturn(roomsList);
        Assertions.assertEquals(roomsList,service.getRooms());
    }
    @Test
    void getRoomByNumber(){
        try {
            Mockito.when(repositoryMock.getRoomByNumber(101)).thenReturn(roomInUse);
            Assertions.assertEquals(roomInUse,service.getRoomByNumber(101));
            Mockito.verify(repositoryMock,Mockito.times(1)).getRoomByNumber(101);
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void getRoomByNumber_ThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repositoryMock.getRoomByNumber(101)).thenThrow(new SearchItemNotFoundException("la habitacion con el numero "+101+
                    " no existe"));
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.getRoomByNumber(101));
            Mockito.verify(repositoryMock,Mockito.times(1)).getRoomByNumber(101);
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void getRoomById(){
        try {
            Mockito.when(repositoryMock.getRoomById(1)).thenReturn(roomInUse);
            Assertions.assertEquals(roomInUse,service.getRoomById(1));
            Mockito.verify(repositoryMock,Mockito.times(1)).getRoomById(1);
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void getRoomById_ThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repositoryMock.getRoomById(1)).thenThrow(new SearchItemNotFoundException("la habitacion con el numero "+101+
                    " no existe"));
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.getRoomById(1));
            Mockito.verify(repositoryMock,Mockito.times(1)).getRoomById(1);
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void updateRoom(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.updateRoom(roomInUse)).thenReturn(roomInUse);
            Assertions.assertEquals(roomInUse,service.updateRoom(roomInUse));
            Mockito.verify(repositoryMock,Mockito.times(1)).updateRoom(roomInUse);
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void updateRoom_ThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(false);
            Mockito.when(repositoryMock.updateRoom(roomInUse)).thenReturn(roomInUse);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.updateRoom(roomInUse));
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void updateRoom_RepositoryThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.updateRoom(roomInUse)).thenThrow(new SearchItemNotFoundException("la habitacion con el id "+1+
                    " no existe"));
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.updateRoom(roomInUse));
            Mockito.verify(repositoryMock,Mockito.times(1)).updateRoom(roomInUse);
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void changeRoomType(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.roomTypeExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.changeRoomType(1,1)).thenReturn(roomInUse);
            Assertions.assertEquals(roomInUse,service.changeRoomType(1,1));
            Mockito.verify(repositoryMock,Mockito.times(1)).changeRoomType(1,1);
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void changeRoomType_RoomNotExist(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(false);
            Mockito.when(repositoryMock.roomTypeExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.changeRoomType(1,1)).thenReturn(roomInUse);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.changeRoomType(1,1));
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void changeRoomType_RoomTypeNotExist(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.roomTypeExistById(1)).thenReturn(false);
            Mockito.when(repositoryMock.changeRoomType(1,1)).thenReturn(roomInUse);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.changeRoomType(1,1));
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void changeRoomType_RepositoryThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.roomTypeExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.changeRoomType(1,1)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.changeRoomType(1,1));
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void changeRoomStatus(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.roomStatusExistById(2)).thenReturn(true);
            Mockito.when(repositoryMock.changeStateRoom(1,2)).thenReturn(roomInUse);
            Assertions.assertEquals(roomInUse,service.changeRoomStatus(1,2));
            Mockito.verify(repositoryMock,Mockito.times(1)).changeStateRoom(1,2);
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void changeRoomStatus_RoomNotExist(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(false);
            Mockito.when(repositoryMock.roomStatusExistById(2)).thenReturn(true);
            Mockito.when(repositoryMock.changeStateRoom(1,2)).thenReturn(roomInUse);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.changeRoomStatus(1,2));
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void changeRoomStatus_RoomStatusNottExist(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.roomStatusExistById(2)).thenReturn(false);
            Mockito.when(repositoryMock.changeStateRoom(1,2)).thenReturn(roomInUse);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.changeRoomStatus(1,2));
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void changeRoomStatus_RepositoryThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.roomStatusExistById(2)).thenReturn(true);
            Mockito.when(repositoryMock.changeStateRoom(1,2)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.changeRoomStatus(1,2));
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void deleteRoomById(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.deleteRoomById(1)).thenReturn(true);
            service.deleteRoomById(1);
            Mockito.verify(repositoryMock,Mockito.times(1)).deleteRoomById(1);
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void deleteRoomById_RoomNotExist(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(false);
            Mockito.when(repositoryMock.deleteRoomById(1)).thenReturn(true);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.deleteRoomById(1));
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void deleteRoomById_RepositoryThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repositoryMock.roomExistById(1)).thenReturn(true);
            Mockito.when(repositoryMock.deleteRoomById(1)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.deleteRoomById(1));
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void deleteRoom(){
        try {
            Mockito.when(repositoryMock.roomExistById(2)).thenReturn(true);
            Mockito.when(repositoryMock.deleteRoom(roomClean)).thenReturn(true);
            service.deleteRoom(roomClean);
            Mockito.verify(repositoryMock,Mockito.times(1)).deleteRoom(roomClean);
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void deleteRoom_RoomNotExist(){
        try {
            Mockito.when(repositoryMock.roomExistById(2)).thenReturn(false);
            Mockito.when(repositoryMock.deleteRoom(roomClean)).thenReturn(true);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.deleteRoom(roomClean));
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
    @Test
    void deleteRoom_RepositoryThrowsSearchItemNotFoundException(){
        try {
            Mockito.when(repositoryMock.roomExistById(2)).thenReturn(false);
            Mockito.when(repositoryMock.deleteRoom(roomClean)).thenThrow(SearchItemNotFoundException.class);
            Assertions.assertThrows(SearchItemNotFoundException.class,()->service.deleteRoom(roomClean));
        }catch (Exception e) {
            Assertions.fail(e);
        }
    }
}