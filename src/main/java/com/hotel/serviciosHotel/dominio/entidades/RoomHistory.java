package com.hotel.serviciosHotel.dominio.entidades;

import java.time.LocalDateTime;

public class RoomHistory {
    private int idRecord;
    private Service idService;
    private Room idRoom;
    private LocalDateTime sinceDate;
    private LocalDateTime tillDate;

    public RoomHistory() {
    }

    public RoomHistory(int idRecord, Service idService, Room idRoom, LocalDateTime sinceDate, LocalDateTime tillDate) {
        this.idRecord = idRecord;
        this.idService = idService;
        this.idRoom = idRoom;
        this.sinceDate = sinceDate;
        this.tillDate = tillDate;
    }

    public RoomHistory(Room idRoom, LocalDateTime sinceDate, LocalDateTime tillDate) {
        this.idRoom = idRoom;
        this.sinceDate = sinceDate;
        this.tillDate = tillDate;
    }

    public int getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(int idRecord) {
        this.idRecord = idRecord;
    }

    public Service getIdService() {
        return idService;
    }

    public void setIdService(Service idService) {
        this.idService = idService;
    }

    public Room getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Room idRoom) {
        this.idRoom = idRoom;
    }

    public LocalDateTime getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate(LocalDateTime sinceDate) {
        this.sinceDate = sinceDate;
    }

    public LocalDateTime getTillDate() {
        return tillDate;
    }

    public void setTillDate(LocalDateTime tillDate) {
        this.tillDate = tillDate;
    }
}
