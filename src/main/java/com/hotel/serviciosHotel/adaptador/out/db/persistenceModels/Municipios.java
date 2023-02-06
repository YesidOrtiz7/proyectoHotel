package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

@Entity
@Table(name = "tblmunicipios")
public class Municipios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMunicipios;
    private String nombreMun;

    public Municipios() {
    }

    public Municipios(String nombreMun) {
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
