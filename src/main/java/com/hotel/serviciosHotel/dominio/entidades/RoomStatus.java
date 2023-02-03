package com.hotel.serviciosHotel.dominio.entidades;

public class RoomStatus {
    private int idStatus;
    private String statusName;

    public RoomStatus(int idStatus, String statusName) {
        this.idStatus = idStatus;
        this.statusName = statusName;
    }

    public RoomStatus() {
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
