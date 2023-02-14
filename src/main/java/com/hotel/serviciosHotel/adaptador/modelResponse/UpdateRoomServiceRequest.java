package com.hotel.serviciosHotel.adaptador.modelResponse;

public class UpdateRoomServiceRequest {
    private int idService;
    private int roomNumber;

    public UpdateRoomServiceRequest() {
    }

    public UpdateRoomServiceRequest(int idService, int roomNumber) {
        this.idService = idService;
        this.roomNumber = roomNumber;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
