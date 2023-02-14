package com.hotel.serviciosHotel.adaptador.modelResponse;

public class UpdateRateServiceRequest {
    private int idService;
    private int rateId;

    public UpdateRateServiceRequest() {
    }

    public UpdateRateServiceRequest(int idService, int rateId) {
        this.idService = idService;
        this.rateId = rateId;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }
}
