package com.hotel.serviciosHotel.adaptador.modelResponse;

import com.hotel.serviciosHotel.dominio.entidades.Service;

public class ExtendServicesRequestModel {
    private int service;
    private int dia;
    private int hora;
    private int minuto;

    public ExtendServicesRequestModel() {
    }

    public ExtendServicesRequestModel(int service, int dia, int hora, int minuto) {
        this.service = service;
        this.dia = dia;
        this.hora = hora;
        this.minuto = minuto;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
}
