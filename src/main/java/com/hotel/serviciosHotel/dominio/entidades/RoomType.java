package com.hotel.serviciosHotel.dominio.entidades;

public class RoomType {
    private int idRoomType;
    private String roomTypeDescription;

    public RoomType() {
    }

    public RoomType(int idRoomType, String roomTypeDescription) {
        this.idRoomType = idRoomType;
        this.roomTypeDescription = roomTypeDescription;
    }

    public int getIdRoomType() {
        return idRoomType;
    }

    public void setIdRoomType(int idRoomType) {
        this.idRoomType = idRoomType;
    }

    public String getRoomTypeDescription() {
        return roomTypeDescription;
    }

    public void setRoomTypeDescription(String roomTypeDescription) {
        this.roomTypeDescription = roomTypeDescription;
    }
}
