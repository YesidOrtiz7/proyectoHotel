package com.hotel.serviciosHotel.adaptador.out.db.persistenceModels;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tblrecepcionista")
public class Recepcionista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecep;
    private String docRecep;
    private String nombresRecep;
    private String apellidosRecep;

    @OneToMany(mappedBy = "idRecep",cascade = CascadeType.ALL)
    private List<Servicio> servicios;

    public Recepcionista(String docRecep, String nombresRecep, String apellidosRecep) {
        this.docRecep = docRecep;
        this.nombresRecep = nombresRecep;
        this.apellidosRecep = apellidosRecep;
    }

    public Recepcionista() {
    }

    public int getIdRecep() {
        return idRecep;
    }

    public void setIdRecep(int idRecep) {
        this.idRecep = idRecep;
    }

    public String getDocRecep() {
        return docRecep;
    }

    public void setDocRecep(String docRecep) {
        this.docRecep = docRecep;
    }

    public String getNombresRecep() {
        return nombresRecep;
    }

    public void setNombresRecep(String nombresRecep) {
        this.nombresRecep = nombresRecep;
    }

    public String getApellidosRecep() {
        return apellidosRecep;
    }

    public void setApellidosRecep(String apellidosRecep) {
        this.apellidosRecep = apellidosRecep;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}
