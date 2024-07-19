package com.hotel.serviciosHotel.dominio.entidades;

public class BusinessConfiguration {
    private int id;
    private int idStateDefaultToStartService;
    private int idStateDefaultToCloseService;

    public BusinessConfiguration(int id, int idStateDefaultToStartService, int idStateDefaultToCloseService) {
        this.id = id;
        this.idStateDefaultToStartService=idStateDefaultToStartService;
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

    public int getIdStateDefaultToStartService() {
        return idStateDefaultToStartService;
    }

    public void setIdStateDefaultToStartService(int idStateDefaultToStartService) {
        this.idStateDefaultToStartService = idStateDefaultToStartService;
    }

    public int getIdStateDefaultToCloseService() {
        return idStateDefaultToCloseService;
    }

    public void setIdStateDefaultToCloseService(int idStateDefaultToCloseService) {
        this.idStateDefaultToCloseService = idStateDefaultToCloseService;
    }
}
