package com.hotel.serviciosHotel.dominio.entidades;


public class Municipios {
    private int idMunicipios;
    private String nombreMun;

    public Municipios() {
    }

    public Municipios(int idMunicipios, String nombreMun) {
        this.idMunicipios = idMunicipios;
        this.nombreMun = nombreMun;
    }

    public int getIdMunicipios() {
        return idMunicipios;
    }

    public void setIdMunicipios(int idMunicipios) {
        this.idMunicipios = idMunicipios;
    }

    public String getNombreMun() {
        return nombreMun;
    }

    public void setNombreMun(String nombreMun) {
        this.nombreMun = nombreMun;
    }
}
