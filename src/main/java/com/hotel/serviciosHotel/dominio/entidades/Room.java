package com.hotel.serviciosHotel.dominio.entidades;

public class Room {

    private int roomNumber;
    private RoomStatus idRoomStatus;
    private RoomType roomType;
    private int bedsNumber;
    private int idRoom;

    public Room(int roomNumber, RoomStatus idRoomStatus, RoomType roomType, int bedsNumber, int idRoom) {
        this.roomNumber = roomNumber;
        this.idRoomStatus = idRoomStatus;
        this.roomType = roomType;
        this.bedsNumber = bedsNumber;
        this.idRoom = idRoom;
    }

    public Room() {
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomStatus getIdRoomStatus() {
        return idRoomStatus;
    }

    public void setIdRoomStatus(RoomStatus idRoomStatus) {
        this.idRoomStatus = idRoomStatus;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getBedsNumber() {
        return bedsNumber;
    }

    public void setBedsNumber(int bedsNumber) {
        this.bedsNumber = bedsNumber;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
}
