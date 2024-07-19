package com.hotel.serviciosHotel.dominio.entidades;

public class BusinessConfiguration {
    private int id;
    private int idStateDefaultToCloseService;

    public BusinessConfiguration(int id, int idStateDefaultToCloseService) {
        this.id = id;
        this.idStateDefaultToCloseService = idStateDefaultToCloseService;
    }

    public BusinessConfiguration() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStateDefaultToCloseService() {
        return idStateDefaultToCloseService;
    }

    public void setIdStateDefaultToCloseService(int idStateDefaultToCloseService) {
        this.idStateDefaultToCloseService = idStateDefaultToCloseService;
    }
}
